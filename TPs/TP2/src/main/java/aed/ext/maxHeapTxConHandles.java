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

    public Handle insertar(Transaccion t) {
        heap.add(t);                                    // O(1)
        Handle handle = new Handle(heap.size() - 1);    // O(1)
        handles.add(handle);                            // O(1)
        heapifyArriba(heap.size() - 1);                 // O(log heap.size())
        return handle;                                  // O(1)
    }

    public void eliminar(Handle handle) {
        if (handle == null || !handle.esValido()) return;   // O(1)

        int indice = handle.getIndice();
        if (indice < 0 || indice >= heap.size()) return;    // O(1)

        int ultimo = heap.size() - 1;   // O(1)
        swap(indice, ultimo);           // O(1)
        heap.remove(ultimo);            // O(1)
        handles.remove(ultimo);         // O(1)
        handle.noEsValido();            // O(1)

        if (indice < heap.size()) {
            heapifyArriba(indice);      // O(log indice)
            heapifyAbajo(indice);       // O(log indice)
        }
    }

    // Elimino una Tx del heap (despues reordeno el heap para que quede bien armado)
    public void eliminar(Transaccion t) {
        int idTx = -1;                      // Tomo un caso borde (uno que nunca cumple lo pedido)
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).equals(t)) {    // Si la Tx del heap coincide con la pedida
                idTx = i;                   // Es la que buscaba, la guardo
            }
        }

        if (idTx == -1) return;             // Si no encuentro la Tx que quiero eliminar, no existe
                                            // -> No elimino nada, termino la funcion

        int ultimo = heap.size() - 1;       // Busco el ultimo elem del heap
        swap(idTx, ultimo);                 // Y lo coloco en la posicion de idTx (y viceversa) 
        heap.remove(ultimo);                // Luego, elimino la t que buscaba (ahora guardada en la ultima posicion del heap)

        heapifyAbajo(idTx);                 // Reordeno idTx despues de de hacer swap y eliminar la Tx  
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
        heap.set(j, temp);                      // j = i (temp) (O(1))

        Handle handleTemp = handles.get(i);
        handles.set(i, handles.get(j));         // O(1)
        handles.set(j, handleTemp);             // O(1)

        if (handles.get(i) != null && handles.get(i).esValido()) {
            handles.get(i).setIndice(i);        // O(1)
        }
        if (handles.get(j) != null && handles.get(j).esValido()) {
            handles.get(j).setIndice(j);        // O(1)
        }
    }

    // Devuelvo el primer elem del heap (mayor monto)
    public Transaccion devolverPrimero() {
        return heap.get(0); // O(1)
    }

    public Handle devolverPrimerHandle() {
        if (handles.isEmpty()) return null; // O(1)
        return handles.get(0);  // O(1)
    }

    // Devuelve la cantidad total de elems que hay en el Heap
    public int tamano() {
        return heap.size(); // O(1)
    }

    // Devuelve todos los elems del Heap
    public ArrayList<Transaccion> obtenerElementos() {
        return new ArrayList<>(heap);   // O(1)
    }
}
