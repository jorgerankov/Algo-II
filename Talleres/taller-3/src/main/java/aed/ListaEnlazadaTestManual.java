package aed;

public class ListaEnlazadaTestManual {
    public static void main(String[] args) {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.agregarAdelante(1);
        lista.agregarAdelante(2);
        lista.agregarAtras(0);

        Iterador<Integer> iterador = lista.iterador();

        System.out.println("Recorriendo hacia adelante:");
        while (iterador.haySiguiente()) {
            System.out.println(iterador.siguiente());
        }

        System.out.println("Recorriendo hacia atrás:");
        while (iterador.hayAnterior()) {
            System.out.println(iterador.anterior());
        }
    }
}