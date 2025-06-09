package aed.ext;

public class usuarioMayorMonto {
    private Usuario[] usuarios;
    private int tamano = 0;

    public usuarioMayorMonto(int capacidad) {
        usuarios = new Usuario[capacidad];
    }

    private int getHijoIzqIndex(int i) { return 2 * i + 1; }
    private int getHijoDerIndex(int i) { return 2 * i + 2; }
    private int getPadreIndex(int i) { return (i - 1) / 2; }

    private boolean tieneHijoIzq(int i) { return getHijoIzqIndex(i) < tamano; }
    private boolean tieneHijoDer(int i) { return getHijoDerIndex(i) < tamano; }
    private boolean tienePadre(int i) { return getPadreIndex(i) >= 0; }

    private Usuario hijoIzq(int i) { return usuarios[getHijoIzqIndex(i)]; }
    private Usuario hijoDer(int i) { return usuarios[getHijoDerIndex(i)]; }
    private Usuario padre(int i) { return usuarios[getPadreIndex(i)]; }


    private void swap(int i, int j) {
        Usuario temp = usuarios[i];
        usuarios[i] = usuarios[j];
        usuarios[j] = temp;
    }

    private boolean esMayor(Usuario a, Usuario b) {
        if (a.getMonto() > b.getMonto()) return true;
        if (a.getMonto() == b.getMonto()) return a.getId() < b.getId();
        return false;
    }

    private void heapifyArriba() {
        int i = tamano - 1;
        while (tienePadre(i) && esMayor(usuarios[i], padre(i))) {
            swap(i, getPadreIndex(i));
            i = getPadreIndex(i);
        }
    }

    private void heapifyAbajo(int i) {
        while (tieneHijoIzq(i)) {
            int mayorHijoIndex = getHijoIzqIndex(i);
            if (tieneHijoDer(i) && esMayor(hijoDer(i), hijoIzq(i))) {
                mayorHijoIndex = getHijoDerIndex(i);
            }

            if (esMayor(usuarios[i], usuarios[mayorHijoIndex])) {
                return;
            } else {
                swap(i, mayorHijoIndex);
                i = mayorHijoIndex;
            }
        
        }
    }

    public void insertar(Usuario nuevo) {
        if (tamano == usuarios.length) {
            throw new IllegalStateException("Heap lleno");
        }
        usuarios[tamano] = nuevo;
        tamano++;
        heapifyArriba();
    }

    public Usuario verMax() {
        if (tamano == 0) throw new IllegalStateException("Heap vacio");
        return usuarios[0];
    }
}
