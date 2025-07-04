package aed.ext;
import java.util.ArrayList;

public class maxHeapUsuarios {
    private ArrayList<UsuarioConHandle> heap;

    public maxHeapUsuarios() {
        heap = new ArrayList<>();
    }

    public void insertar(UsuarioConHandle u) {
        heap.add(u);
        Handle handle = new Handle(heap.size() - 1);
        u.setHandle(handle);
        heapifyArriba(heap.size() - 1);
    }

    public void actualizarHeap(UsuarioConHandle u) {
        Handle handle = u.getHandle();

        int indice = handle.getIndice();
        if (indice >= 0 && indice < heap.size()) {
            heapifyArriba(indice);
            heapifyAbajo(indice);
        }
    }

    public UsuarioConHandle obtenerMaximo() {
        if (heap.isEmpty()) return null;
        return heap.get(0);
    }

    private void heapifyArriba(int i) {
        while (i > 0) {
            int padre = (i - 1) / 2;
            if (UsuarioConHandle.maximo(heap.get(i), heap.get(padre)) == heap.get(i)) {
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
            int mayor = izq;
            int der = 2 * i + 2;            // Evaluo hijo derecho en c/ iteracion

            if (der < heap.size() && 
                UsuarioConHandle.maximo(heap.get(der), heap.get(izq)) == heap.get(der)) {
                    mayor = der;            // El hijo derecho pasa a ser el nuevo mayor
            }   
            if (UsuarioConHandle.maximo(heap.get(i), heap.get(mayor)) == heap.get(i)) {
                return;
            }

            swap(i, mayor);
            i = mayor;
            izq = 2 * i + 1;            // Actualizo el hijo izq para que pase a ser el nuevo mayor
        }
    }

    private void swap (int i, int j) {
        UsuarioConHandle userI = heap.get(i);
        UsuarioConHandle userJ = heap.get(j);

        heap.set(i, userJ);
        heap.set(j, userI);

        userI.getHandle().setIndice(j);
        userJ.getHandle().setIndice(i);
    }
}
