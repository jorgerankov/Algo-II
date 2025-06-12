package aed.ext;
import aed.Transaccion;

public class nodoTx {

    private Transaccion[] transacciones;

    maxHeapTx heap;
    int montoTotal;
    int cantidadTx;

    private int montoSinCreacion;
    private int cantidadSinCreacion;

    public nodoTx(Transaccion[] transacciones) {
        
        this.transacciones = transacciones;

        heap = new maxHeapTx();
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
    public maxHeapTx obtenerHeap() {
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

    public void eliminar(Transaccion tx) {
        if (transacciones == null) return;                                  // Si no hay Txs, no devuelvo nada
        int n = transacciones.length;                                       // n = total de Txs
        int idx = -1;                                                       // Tomo caso borde 
        for (int i = 0; i < n; i++) {
            if (transacciones[i] != null && transacciones[i].equals(tx)) {  // Si la Tx del array es igual a la q quiero eliminar
                idx = i;                                                    // Me guardo el caso
            }
        }

        if (idx != -1) {                                                    // Si encontré la Tx que buscaba eliminar
            Transaccion[] nuevo = new Transaccion[n - 1];                   // Creo una lista de Tx con longitud Txs originales menos la eliminada
            for (int i = 0, j = 0; i < n; i++) {                            // 
                if (i != idx) {
                    nuevo[j++] = transacciones[i];
                }
            }
            transacciones = nuevo;
        }
    }
}