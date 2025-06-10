package aed.ext;
import aed.Transaccion;

public class nodoTx {

    private Transaccion[] transacciones;

    private maxHeapTx heap;
    private int montoTotal;
    private int cantidadTx;
    private nodoTx anterior;

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
        
        for (Transaccion t : transacciones) {
            heap.insertar(t);
            montoTotal += t.monto();
            cantidadTx++;

            if (t.id_vendedor() != 0) {
                montoSinCreacion += t.monto();
                cantidadSinCreacion++;
            }
        }

        anterior = null;
    }

    public int obtenerMontoSinCreacion() {
        return montoSinCreacion;
    }

    public int obtenerCantidadSinCreacion() {
        return cantidadSinCreacion;
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

    public Transaccion[] obtenerTransacciones(){
        return transacciones;
    }
}
