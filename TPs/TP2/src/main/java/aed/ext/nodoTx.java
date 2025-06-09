package aed.ext;
import aed.Transaccion;

public class nodoTx {

    private maxHeapTx heap;
    private int montoTotal;
    private int cantidadTx;
    private nodoTx anterior;

    public nodoTx(Transaccion[] transacciones) {
        heap = new maxHeapTx();
        montoTotal = 0;
        cantidadTx = 0;
        
        for (Transaccion t : transacciones) {
            heap.insertar(t);
            montoTotal += t.monto();
            cantidadTx++;
        }

        anterior = null;
    }

    public maxHeapTx obtenerHeap() {
        return heap;
    }

    public int obtenerMontoTotal() {
        return montoTotal;
    }

    public int obtenerTotalTx() {
        return cantidadTx;
    }

    public void setAnterior(nodoTx prev) {
        this.anterior = prev;
    }

    public nodoTx getAnterior() {
        return anterior;
    }
}
