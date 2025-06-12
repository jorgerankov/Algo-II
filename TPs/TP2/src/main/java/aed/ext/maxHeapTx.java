package aed.ext;
import aed.Transaccion;
import java.util.ArrayList;

public class maxHeapTx {
    private ArrayList<Transaccion> heap;

    // Creo un nuevo Heap
    public maxHeapTx() {
        heap = new ArrayList<>();
    }

    // Inserto nueva Tx en el Heap y la "acomodo"
    public void insertar(Transaccion t) {
         heap.add(t);
         heapifyArriba(heap.size() - 1);
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
        int mayor = izq;                    // Asumo que el izq es el mayor de los 2

        while (izq < heap.size()) {
            int der = 2 * i + 2;            // Evaluo hijo derecho en c/ iteracion

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
        Transaccion temp = heap.get(i);         // Guardo i en una var temporal 
        heap.set(i, heap.get(j));               // i = j
        heap.set(j, temp);                      // j = i (temp)
    }

    // Devuelvo el primer elem del heap (mayor monto)
    public Transaccion devolverPrimero() {
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
