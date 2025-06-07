package aed;

class Nodo {
    int valor;                                  // "Prioridad" dada al Nodo
    Handle handle;                              // Handle asociado al Nodo

    Nodo(int valor) {
        this.valor = valor;
    }
}

class Handle {
    int indiceEnHeap;                           // Indice actual del Nodo en el heap

    Handle(int i) {
        this.indiceEnHeap = i;                  // posicion/indice
    }
}

public class arrayConHandle {

    Nodo[] heap;
    int size;                                   // Cantidad de elems en el heap

    void minHeap(int capacidad) {
        heap = new Nodo[capacidad];             // Creo array (heap) con determinada capacidad
        size = 0;                               // Inicializo el heap vacio
    }

    public Handle insertar(int valor) {
        Nodo nuevoNodo = new Nodo(valor);       // Nuevo Nodo con una prioridad
        Handle handle = new Handle(size);       // Handle que apunta al indice actual
        nuevoNodo.handle = handle;              // Asocio el Handle al Nodo
        heap[size] = nuevoNodo;                 // Inserto el Nodo al final del Heap
        heapifyUp(size);                        // Reordeno (si llega a ser necesario)
        size++;                                 // +1 el tamaño del Handle
        return handle;                          // Devuelvo el Handle
    }

    void restarValor(Handle h, int valorARestar) {  
        int i = h.indiceEnHeap;                 // Accedo a la pos del Nodo
        if (heap[i].valor > valorARestar) {     // Busco que el valor no quede negativo
            heap[i].valor -= valorARestar;      // Resto el valor
            heapifyUp(i);                       // Reordeno el Heap
        }
    }

    void sumarValor(Handle h, int valorASumar) {
        int i = h.indiceEnHeap;                 // Accedo a la pos del Nodo
            heap[i].valor += valorASumar;       // Resto el valor
            //heapifyDown(i);                     // Reordeno el Heap
    }

    void swap(int i, int j) {
        Nodo temp = heap[i];                    // Guardo el Nodo en una var temporal
        heap[i] = heap[j];                      // Intercambio Nodos en las posiciones
        heap[j] = temp;                         // Idem

        heap[i].handle.indiceEnHeap = i;        // Actualizo indices con su nueva pos
        heap[j].handle.indiceEnHeap = j;        // Idem

    }

    void heapifyUp(int i) {
        while (i > 0) {
            int padre = (i-1) / 2;                      // Calculo indice de padre
            if (heap[i].valor < heap[padre].valor) {    // Si prioridad es menor que su padre
                swap(i, padre);                         // Swapeo
                i = padre;                              // Sigo buscando
            }
            return;
        }
    }

}
