package aed.ext;
import aed.Transaccion;

public class nodoTx {

    private Transaccion[] transacciones;
    private Handle primerTxHandle;

    maxHeapTxConHandles heap;
    int montoTotal;
    int cantidadTx;
    int montoSinCreacion;
    int cantidadSinCreacion;

    public nodoTx(Transaccion[] transacciones) {
        
        this.transacciones = transacciones;

        heap = new maxHeapTxConHandles();
        montoTotal = 0;
        cantidadTx = 0;
        montoSinCreacion = 0;
        cantidadSinCreacion = 0;
        
        for (Transaccion t : transacciones) {           // Veo cada Tx (O(n_b))
            heap.insertar(t);                           // Inserto uno a uno en el Heap (O(1))
            montoTotal += t.monto();                    // Sumo los montos al monto total (O(1))
            cantidadTx++;                               // Sumo 1 a la cantidad total de Tx (O(1))

            if (t.id_comprador() != 0) {                // Si el ID del comprador no es el de creacion
                montoSinCreacion += t.monto();          // Sumo monto al total (sin creacion) (O(1))
                cantidadSinCreacion++;                  // +1 Tx (sin creacion) (O(1))
            }
        }
        primerTxHandle = heap.devolverPrimerHandle();   // Guardo el primer Handle del heap (O(1))
    }

    
    // Devuelvo el monto total de todas las Tx
    public int montoTotal() {
        return montoTotal;  // O(1)
    }

    // Devuelvo la cantidad total de Tx en el bloque
    public int totalTx() {
        return cantidadTx;  // O(1)
    }

    // Devuelvo el monto total de todas las Tx sin la de Creacion (la primera)
    public int montoTotalSinCreacion() {
        return montoSinCreacion;    // O(1)
    }
    
    // Devuelvo la cantidad total de Tx en el bloque sin la de Creacion (la primera)
    public int totalTxSinCreacion() {
        return cantidadSinCreacion; // O(1)
    }
    
    // Devuelve el Heap de las Tx
    public maxHeapTxConHandles obtenerHeap() {
        return heap;    // O(1)
    }

    // Devuelve las Tx
    public Transaccion[] obtenerTransacciones(){
        return transacciones;   // O(1)
    }

    public void restarMontoTotal(int m) {
        montoTotal -= m;    // O(1)
    }

    public void restarMontoTotalSinCreacion(int m) {
        montoSinCreacion -= m;  // O(1)
    }

    public void restarCantidadTx() {
        cantidadTx-- ;  // O(1)
    }

    public void restarCantidadTxSinCreacion() {
        cantidadSinCreacion--; // O(1)
    }

    public void eliminarPrimero() {
        if (heap.tamano() > 0) {
            Transaccion txAEliminar = heap.devolverPrimero();
            eliminarDeArray(txAEliminar);

            primerTxHandle = heap.devolverPrimerHandle();
            heap.eliminar(primerTxHandle);                      
        }
    }

    private void eliminarDeArray(Transaccion t) {
        int longitud = transacciones.length;
        int idx = -1;
        for (int i = 0; i < longitud; i++) {
            if (transacciones[i].equals(t)) {
                idx = i;
                i = longitud;   // Fuerzo a terminar el for porque ya encontré lo que buscaba
            }
        }

        if (idx != -1) {
            Transaccion[] nuevo = new Transaccion[longitud - 1];
            for (int i = 0, j = 0; i < longitud; i++) {
                if (i != idx) {
                    nuevo[j++] = transacciones[i];
                }
            }
            transacciones = nuevo;
        }
    }
}