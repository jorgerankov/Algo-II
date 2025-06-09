package aed.ext;
import java.util.ArrayList;
import aed.Transaccion;

public class maxHeapTx {
    private ArrayList<Transaccion> heap;

    public maxHeapTx() {
        heap = new ArrayList<>();
    }

    public void insertar(Transaccion t) {
        heap.add(t);
        heapifyArriba(heap.size() - 1);
    }

    private void heapifyArriba(int i) {
        while (i > 0) {
            int padre = (i - 1) / 2;
            if (heap.get(i).compareTo(heap.get(padre)) > 0) {
                swap(i, padre);
                i = padre;
            } else {
                return;
            }
        }
    }   

    private void swap(int i, int j) {
        Transaccion temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public Transaccion devolverPrimero() {
        if (heap.isEmpty()) return null;
        return heap.get(0);
    }

    public int tamano() {
        return heap.size();
    }

    public ArrayList<Transaccion> obtenerElementos() {
        return heap;
    }
}
