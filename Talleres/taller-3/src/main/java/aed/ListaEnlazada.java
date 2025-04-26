package aed;

import java.util.ArrayList;
import java.util.List;

public class ListaEnlazada<T> implements Secuencia<T> {
    // Completar atributos privados
    private Nodo primero;

    private class Nodo {
        private T valor;
        private Nodo sig;

        private Nodo(T v) {
            this.valor = v;
            this.sig = null;
        }
    }

    public ListaEnlazada() {
        primero = null;
    }

    @Override
    public int longitud() {
        int longitud = 0; 
        Nodo actual = primero;
        while (actual != null) {
            longitud++;
            actual = actual.sig;
        }
        return longitud;
    }

    @Override
    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);
        nuevo.sig = primero;
        primero = nuevo;
    }

    @Override
    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (primero == null) {
            primero = nuevo;
        }
        else {
            Nodo actual = primero;
            while (actual.sig != null) {
                actual = actual.sig;
            }
            actual.sig = nuevo;
        }
    }

    @Override
    public T obtener(int i) {
        Nodo actual = primero;
        int contador = 0;
        while (actual != null && contador < i) {
            actual = actual.sig;
            contador++;
        }
        return actual.valor;
    }

    @Override
    public void eliminar(int i) {
        Nodo actual = primero;
        Nodo prev = primero;
        for (int j = 0; j < i; j++) {
            prev = actual;
            actual = actual.sig;
        }
        if (i == 0) {
            primero = actual.sig;
        } else {
            prev.sig = actual.sig;
        }
    }

    @Override
    public void modificarPosicion(int indice, T elem) {
        Nodo actual = primero;
        int contador = 0;
        while (actual != null && contador != indice) {
            actual = actual.sig;
            contador++;
        }
        actual.valor = elem;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        if (lista.primero == null) {
            this.primero = null;
        } else {
            this.primero = new Nodo(lista.primero.valor);
            Nodo actualNuevo = this.primero;
            Nodo actualOriginal = lista.primero.sig;
            while (actualOriginal != null) {
                actualNuevo.sig = new Nodo(actualOriginal.valor);
                actualNuevo = actualNuevo.sig;
                actualOriginal = actualOriginal.sig;
            }
        }
    }
    
    @Override
    public String toString() {
        List<String> listaDeElems = new ArrayList<>();
        Nodo actual = primero;
        while (actual != null) {
            listaDeElems.add(actual.valor.toString());
            actual = actual.sig;
        }
        return listaDeElems.toString();

    }

    public class ListaIterador implements Iterador<T> {
    	private Nodo actual;
        private Nodo prev;

        private ListaIterador() {
            this.actual = primero;
            this.prev = null;
        }

        @Override
        public boolean haySiguiente() {
	        return actual != null && actual.sig != null;
        }

        @Override
        public boolean hayAnterior() {
	        return prev != null;
        }

        @Override
        public T siguiente() {
            if (actual == null) {
                actual = primero;
            } else {
                prev = actual;
                actual = actual.sig;
            }
            return actual.valor;
        }
        
        @Override
        public T anterior() {
	        actual = prev;
            return actual.valor;
        }
    }

    public Iterador<T> iterador() {
        ListaIterador iterador = new ListaIterador();
        if (iterador.haySiguiente() == true && iterador.hayAnterior() == true) {
            return iterador;
        }
        return iterador;
    }

}
