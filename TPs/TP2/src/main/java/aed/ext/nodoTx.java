package aed.ext;
import aed.Transaccion;

public class nodoTx {

    private Transaccion[] transacciones;

    maxHeapTx heap;
    int montoTotal;
    int cantidadTx;
    nodoTx anterior;

    private int montoSinCreacion;
    private int cantidadSinCreacion;

    public nodoTx(Transaccion[] transacciones) {
        
        this.transacciones = transacciones;
        this.anterior = null;

        heap = new maxHeapTx();
        montoTotal = 0;
        cantidadTx = 0;
        montoSinCreacion = 0;
        cantidadSinCreacion = 0;
        
        for (Transaccion t : transacciones) {           // Veo c/ transaccion
            heap.insertar(t);                           // Inserto 1 a 1 en el Heap 
            montoTotal += t.monto();                    // Sumo los montos al monto total
            cantidadTx++;                               // Sumo la cantidad total de Tx

            if (t.id() != 0) {                          // Si el ID de Tx no es el de creacion
                montoSinCreacion += t.monto();          // Sumo monto al total (sin creacion)
                cantidadSinCreacion++;                  // +1 Tx (sin creacion)
            }
        }
        //anterior = null;                                
    }

    // Devuelvo el monto total de todas las Tx sin la de Creacion (la primera)
    public int montoTotalSinCreacion() {
        return montoSinCreacion;
    }

    // Devuelvo el monto total de todas las Tx
    public int montoTotal() {
        return montoTotal;
    }

    // Devuelvo la cantidad total de Tx en el bloque sin la de Creacion (la primera)
    public int totalTxSinCreacion() {
        return cantidadSinCreacion;
    }
    
    // Devuelvo la cantidad total de Tx en el bloque
    public int totalTx() {
        return cantidadTx;
    }

    // Devuelve el Heap de las Tx
    public maxHeapTx obtenerHeap() {
        return heap;
    }

    // Devuelve un nuevo heap sin la primer transacción (la de mayor valor)
    public maxHeapTx obtenerHeapPostHack() {
        maxHeapTx heapHackeado = new maxHeapTx();
        for (Transaccion t : this.transacciones) {
            heapHackeado.insertar(t);
        }
        return heapHackeado;
    }

    // El nuevo bloque de Tx apunta al bloque creado antes que el
    public void apuntarAnterior(nodoTx prev) {
        this.anterior = prev;
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
}