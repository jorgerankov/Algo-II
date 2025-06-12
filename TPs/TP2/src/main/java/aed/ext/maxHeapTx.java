package aed.ext;
import aed.Transaccion;
import java.util.ArrayList;

public class maxHeapTx {
    ArrayList<Transaccion> heap;

    // Creo un nuevo Heap
    public maxHeapTx() {
        heap = new ArrayList<>();
    }

    // Inserto nueva Tx en el Heap y la "acomodo"
    public void insertar(Transaccion t) {
         heap.add(t);
         heapifyArriba(heap.size() - 1);
    }

    public void eliminar(Transaccion t) {
        int idTxHackeada = -1;
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).equals(t)) {
                idTxHackeada = i;
                break;
            }
        }

        if (idTxHackeada == -1) return;

        int ultimo = heap.size() - 1;
        swap(idTxHackeada, ultimo);
        heap.remove(ultimo);

        if (idTxHackeada < heap.size()) {
            // heapifyArriba(idTxHackeada);
            heapifyAbajo(idTxHackeada);
        }
    }

    // Evaluo hijos con padre para ir acomodandolos por "prioridad" en el Heap
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

    private void heapifyAbajo(int i) {
        int izq = 2 * i + 1;

        while (izq < heap.size()) {
            int der = 2 * i + 2;
            int mayor = izq;

            if (der < heap.size() && heap.get(der).compareTo(heap.get(izq)) > 0) {
                mayor = der;
            }

            if (heap.get(i).compareTo(heap.get(mayor)) >= 0) {
                return;
            }

            swap(i, mayor);
            i = mayor;
            izq = 2 * i + 1;
        }
    }

    // Intercambio elems en el heap para mantenerlo correcto (maxHeap, que no haya elems hijos mayores al padre)
    private void swap(int i, int j) {
        Transaccion temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Devuelvo el primer elem del heap (mayor monto)
    public Transaccion devolverPrimero() {
        if (heap.isEmpty()) return null;
        return heap.get(0);
    }

    // Devuelve la cantidad total de elems que hay en el Heap
    public int tamano() {
        return heap.size();
    }

    // Devuelve todos los elems del Heap
    public ArrayList<Transaccion> obtenerElementos() {
        return heap;
    }
}
