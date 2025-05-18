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
            return;
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
                hijo.padre = null;
            } else if (padre.izq == actual) {
                padre.izq = hijo;
                hijo.padre = padre;
            } else {
                padre.der = hijo;
                hijo.padre = padre;
            }
        }

        // 2 hijos
        else {
            Nodo suc = actual.der;

            while (suc.izq != null){
                suc = suc.izq;
            }

            T valorSuc = suc.valor;
            eliminar(valorSuc);
            actual.valor = valorSuc;
            return;
        }
        _cardinal--;
    }

    @Override
    public String toString(){

        if (_raiz == null) {
            return "{}";
        }

        StringBuilder res = new StringBuilder("{");

        Nodo actual = _raiz;
        while (actual.izq != null){
            actual = actual.izq;
        }

        while (actual != null) {
            res.append(actual.valor);

            Nodo sig = null;
            if (actual.der != null){
                sig = actual.der;
                while (sig.izq != null){
                    sig = sig.izq;
                }
            } else {
                Nodo padre = actual.padre;
                while (padre != null && actual == padre.der){
                    actual = padre;
                    padre = padre.padre;
                }
                sig = padre;
            }

            if (sig != null){
                res.append(",");
            }
            actual = sig;
        }
        res.append("}");
        return res.toString();

    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual = _raiz;
        
        public ABB_Iterador() {
            _actual = _raiz;
            if (_actual != null) {
                while (_actual.izq != null) {
                    _actual = _actual.izq;
                }
            }
        }

        @Override
        public boolean haySiguiente() {      
            return _actual != null;
        }
        
        @Override
        public T siguiente() {
            
            if (!haySiguiente()) {
                return null;
            }

            T valor = _actual.valor;

            if (_actual.der != null){
                _actual = _actual.der;
                while (_actual.izq != null){
                    _actual = _actual.izq;
                }
            } else {
                Nodo padre = _actual.padre;
                while (padre != null && _actual == padre.der) {
                    _actual = padre;
                    padre = padre.padre;
                }
                _actual = padre;
            }
            return valor;
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
