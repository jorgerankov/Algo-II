package aed.ext;
import aed.Transaccion;
import java.util.ArrayList;

public class maxHeapTxConHandles {
    private ArrayList<Transaccion> heap;
    private ArrayList<Handle> handles;

    // Creo un nuevo Heap
    public maxHeapTxConHandles() {
        heap = new ArrayList<>();
        handles = new ArrayList<>();
    }

    void insertar(Transaccion t) {
        heap.add(t);                                    // O(1)
        Handle handle = new Handle(heap.size() - 1);    // O(1)
        handles.add(handle);                            // O(1)
        heapifyArriba(heap.size() - 1);                 // O(log heap.size())
    }

    // E
    public void eliminar(Handle handle) {
        int indice = handle.getIndice();

        int ultimo = heap.size() - 1;   // busco el ultimo elemento en el Heap (O(1))
        swap(indice, ultimo);           // Lo intercambio con el que quiero eliminar (O(1))
        heap.remove(ultimo);            // O(1)

        if (indice < heap.size()) {
            heapifyAbajo(indice);       // Reacomodo en el Heap el elemento intercambiado con el que fue eliminado (O(log indice))
        }
    }

    // Reordeno el Heap con el elemento por su "prioridad" hacia abajo (evaluo hijos con su padre)
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

    // Reordeno el Heap con el elemento por su "prioridad" hacia abajo (evaluo al padre con sus hijos)
    private void heapifyAbajo(int i) {
        int izq = 2 * i + 1;

        while (izq < heap.size()) {
            int mayor = izq;                    // Asumo que el izq es el mayor de los 2
            int der = 2 * i + 2;                // Evaluo hijo derecho en c/ iteracion

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
        Transaccion temp = heap.get(i);         // Guardo i en una var temporal (O(1))
        heap.set(i, heap.get(j));               // i = j (O(1))
        heap.set(j, temp);                      // j = i = temp (O(1))
    }

    // Devuelvo el la primer Tx del heap (mayor monto)
    public Transaccion devolverPrimero() {
        return heap.get(0); // O(1)
    }

    // Devuelve la primer Tx que fue guardada dentro de los Handles
    public Handle devolverPrimerHandle() {
        return handles.get(0);  // O(1)
    }

    // Devuelve la cantidad total de elems que hay en el Heap
    public int tamano() {
        return heap.size(); // O(1)
    }

    // Devuelve todos los elems del Heap
    public ArrayList<Transaccion> obtenerElementos() {
        return heap;        // Devuelvo todas las Txs ordenadas que tengo en el Heap (O(1))
    }
}
