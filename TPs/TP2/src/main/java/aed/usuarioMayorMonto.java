package aed;

import java.util.Arrays;

public class usuarioMayorMonto {
    private int capacidad;
    private int tamano = 0;
    int[] items = new int[capacidad];


    private int getHijoIzqIndex(int padreIndex) { return 2 * padreIndex + 1; }
    private int getHijoDerIndex(int padreIndex) { return 2 * padreIndex + 2; }
    private int getPadreIndex(int hijoIndex) { return (hijoIndex - 1) / 2; }

    private boolean tieneHijoIzq(int i) { return getHijoIzqIndex(i) < tamano; }
    private boolean tieneHijoDer(int i) { return getHijoDerIndex(i) < tamano; }
    private boolean tienePadre(int i) { return getPadreIndex(i) >= 0; }

    private int hijoIzq(int i) { return items[getHijoIzqIndex(i)]; }
    private int hijoDer(int i) { return items[getHijoDerIndex(i)]; }
    private int padre(int i) { return items[getPadreIndex(i)]; }

    private void swap(int i, int j) {
        int temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    private void capacidadExtra() {
        if (tamano == capacidad) {
            items = Arrays.copyOf(items, capacidad * 2);
            capacidad *= 2;
        }
    }


}
