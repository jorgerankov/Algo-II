package aed;
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    private Nodo _raiz;
    private int _cardinal;
    private int _altura;

    private class Nodo {
        T valor;
        Nodo izq;
        Nodo der;
        Nodo padre;

        Nodo (T v) {
            valor = v;
            izq = null;
            der = null;
            padre = null;
        }
    }

    public ABB() {
        _raiz = null;
        _cardinal = 0;
        _altura = 0;
    }

    @Override
    public int cardinal() {
        return _cardinal;
    }

    @Override
    public T minimo(){
        if (_raiz == null) {
            return null;
        }
        T min = _raiz.valor;
        Nodo actual = _raiz;
        while (actual != null) {
            if (actual.valor.compareTo(min) < 0) {
                min = actual.valor;
            }
            actual = actual.izq;
        }
        return min;
    }

    @Override
    public T maximo(){
        if (_raiz == null) {
            return null;
        }
        T max = _raiz.valor;
        Nodo actual = _raiz;
        while (actual != null) {
            if (actual.valor.compareTo(max) > 0){
                max = actual.valor;
            }
            actual = actual.der;
        }
        return max;
    }

    @Override
    public void insertar(T elem) {
        if (_raiz == null) {
            _raiz = new Nodo(elem);
            _cardinal++;
            return;
        }

        Nodo actual = _raiz;
        while (true) {
            int cmp = elem.compareTo(actual.valor);
            if (cmp == 0) {
                // Ya existe, no se inserta
                return;
            } else if (cmp < 0) {
                if (actual.izq == null) {
                    actual.izq = new Nodo(elem);
                    actual.izq.padre = actual;
                    _cardinal++;
                    return;
                }
                actual = actual.izq;
            } else {
                if (actual.der == null) {
                    actual.der = new Nodo(elem);
                    actual.der.padre = actual;
                    _cardinal++;
                    return;
                }
                actual = actual.der;
            }
        }
    }

    @Override
    public boolean pertenece(T elem) {
        Nodo actual = _raiz;
        while (actual != null) {
            int cmp = elem.compareTo(actual.valor);
            if (cmp == 0) {
                return true;
            } else if (cmp < 0) {
                actual = actual.izq;
            } else {
                actual = actual.der;
            }
        }
        return false;
    }
    
    @Override
    public void eliminar(T elem){
        if (_raiz == null) {
            // No elimino nada
        }
        Nodo actual = _raiz;
        Nodo padre = null;

        while (actual != null && actual.valor.compareTo(elem) != 0){
            padre = actual;
            if (elem.compareTo(actual.valor) < 0){
                actual = actual.izq;
            } else {
                actual = actual.der;
            }
        }

        if (actual == null){
            // No existe el elemento
        }

        // Sin hijos
        if (actual.izq == null && actual.der == null) {
            if (actual == _raiz){
                _raiz = null;
            } else if (padre.izq == actual){
                padre.izq = null;
            } else {
                padre.der = null;
            }
        } 

        // Un solo hijo
        else if (actual.izq == null || actual.der == null) {
            Nodo hijo = (actual.izq != null) ? actual.izq : actual.der;

            if (actual == _raiz) {
                _raiz = hijo;
            } else if (padre.izq == actual) {
                padre.izq = hijo;
            } else {
                padre.der = hijo;
            }
        }

        // 2 hijos
        else {
            Nodo sucPadre = actual;
            Nodo suc = actual.der;

            while (suc.izq != null){
                sucPadre = suc;
                suc = suc.izq;
            }

            actual.valor = suc.valor;

            if (sucPadre.izq == suc) {
                sucPadre.izq = suc.der;
            } else {
                sucPadre.der = suc.der;
            }
        }
        _cardinal--;
    }

    @Override
    public String toString(){
        String res = "{";
        if (_raiz == null){
            res = res.concat("}");
            return res;
        } 
        Nodo actual = _raiz;
        if (actual.izq == null && actual.der == null){
            res = res.concat(actual.valor.toString());
            res = res.concat("}");
            return res;
        }
        return res;
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual = _raiz;

        @Override
        public boolean haySiguiente() {      
            return _actual != null && _actual.der != null;
        }
        
        @Override
        public T siguiente() {
            T res = null;
            if (_actual == null) {
                res = null;
            }
            if (haySiguiente()) {
                Nodo actual = _actual.der;
                Nodo actualPrev = actual;
                while (actual.izq != null) {
                    actualPrev = actual;
                    actual = actual.izq;
                }
                _actual = actualPrev; // Update _actual to the next node
                res = _actual.valor;
            }
            return res;
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
