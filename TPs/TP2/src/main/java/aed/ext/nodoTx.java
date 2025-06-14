package aed.ext;
import aed.Transaccion;

public class nodoTx {

    private Transaccion[] transacciones;
    private Handle primerTxHandle;

    maxHeapTxConHandles heap;
    int montoTotal;
    int cantidadTx;

    private int montoSinCreacion;
    private int cantidadSinCreacion;

    public nodoTx(Transaccion[] transacciones) {
        
        this.transacciones = transacciones;

        heap = new maxHeapTxConHandles();
        montoTotal = 0;
        cantidadTx = 0;
        montoSinCreacion = 0;
        cantidadSinCreacion = 0;
        
        for (Transaccion t : transacciones) {           // Veo c/ transaccion
            heap.insertar(t);                           // Inserto 1 a 1 en el Heap 
            montoTotal += t.monto();                    // Sumo los montos al monto total
            cantidadTx++;                               // Sumo la cantidad total de Tx

            if (t.id_comprador() != 0) {                // Si el ID del comprador no es el de creacion
                montoSinCreacion += t.monto();          // Sumo monto al total (sin creacion)
                cantidadSinCreacion++;                  // +1 Tx (sin creacion)
            }
        }

        primerTxHandle = heap.devolverPrimerHandle();
    }

    
    // Devuelvo el monto total de todas las Tx
    public int montoTotal() {
        return montoTotal;
    }

    // Devuelvo la cantidad total de Tx en el bloque
    public int totalTx() {
        return cantidadTx;
    }

    // Devuelvo el monto total de todas las Tx sin la de Creacion (la primera)
    public int montoTotalSinCreacion() {
        return montoSinCreacion;
    }
    // Devuelvo la cantidad total de Tx en el bloque sin la de Creacion (la primera)
    public int totalTxSinCreacion() {
        return cantidadSinCreacion;
    }
    
    // Devuelve el Heap de las Tx
    public maxHeapTxConHandles obtenerHeap() {
        return heap;
    }

    // Devuelve las Tx
    public Transaccion[] obtenerTransacciones(){
        return transacciones;
    }

    // Almacena las Tx sin la Tx eliminada en hackearTx
    public void nuevasTx(Transaccion[] nuevasTx) {
        this.transacciones = nuevasTx;
    }

    public void restarMontoTotal(int m) {
        montoTotal -= m;
    }

    public void restarMontoTotalSinCreacion(int m) {
        montoSinCreacion -= m;
    }

    public void restarCantidadTx() {
        cantidadTx-- ;
    }

    public void restarCantidadTxSinCreacion() {
        cantidadSinCreacion--;
    }

    public void eliminarPrimero() {
        if (heap.tamano() > 0) {
            Transaccion txAEliminar = heap.devolverPrimero();
            heap.eliminar(primerTxHandle);
            eliminarDeArray(txAEliminar);
            primerTxHandle = heap.devolverPrimerHandle();
        }
    }

    private void eliminarDeArray(Transaccion t) {
        if (transacciones == null || t == null) return;
        int n = transacciones.length;
        int idx = -1;
        for (int i = 0; i < n; i++) {
            if (transacciones[i].equals(t)) {
                idx = i;
            }
        }

        if (idx != -1) {
            Transaccion[] nuevo = new Transaccion[n - 1];
            for (int i = 0, j = 0; i < n; i++) {
                if (i != idx) {
                    nuevo[j++] = transacciones[i];
                }
            }
            transacciones = nuevo;
        }
    }
    
    public void eliminar(Transaccion t) {
        heap.eliminar(t);
        eliminarDeArray(t);
    }
}