package aed.ext;

public class Handle {
    private int indice;         // Posicion en el Heap
    private Handle handle;      // Referencia a otro Handle

    public Handle(int i) {
        this.indice = i;        // indice que me permite acceder al Handle
    }

    public int getIndice() {
        return indice;          // Devuelvo el indice del Handle que busco
    }

    public void setIndice(int i) {
        this.indice = i;        // Defino el indice del Handle
    }

    public Handle getHandle() {
        return handle;          // Devuelvo el handle que busco
    }

    public void setHandle(Handle handle) {
        this.handle = handle;
    }
}
