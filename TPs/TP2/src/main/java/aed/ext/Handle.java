package aed.ext;

public class Handle {
    private int indice;         // Posicion en el Heap
    private boolean esValido;   // Revisa si el handle es valido
    private Handle handle;      // Referencia a otro Handle

    public Handle(int i) {
        this.indice = i;
        this.esValido = true;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int i) {
        this.indice = i;
    }

    public boolean esValido() {
        return esValido;
    }

    public void noEsValido() {
        this.esValido = false;
    }

    public Handle getHandle() {
        return handle;
    }

    public void setHandle(Handle handle) {
        this.handle = handle;
    }
    
}
