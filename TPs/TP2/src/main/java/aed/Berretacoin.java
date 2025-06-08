package aed;
import aed.usuarioMayorMonto;
import aed.arrayConHandle;
import java.util.ArrayList;



public class Berretacoin {

    private Nodo primero;

    private class Nodo {
        Nodo sig;
        int[] transaccionesMayorValor;
        int montoTotal;
        int cantTx;
        
        Nodo(int v) {
            this.sig = null;
            this.transaccionesMayorValor = new int[0];
            this.montoTotal = 0;
            this.cantTx = 0;
        }
    }

    public void ListaEnlazada() {
        primero = null;
    }

    public Berretacoin(int n_usuarios){
        int i;
        ArrayList<Integer> listaUsuarios = new ArrayList<>(n_usuarios);

        for (i = 1; i <= n_usuarios; i++) {
            listaUsuarios.add(i);
        }
    }

    public void agregarBloque(Transaccion[] transacciones){
        throw new UnsupportedOperationException("Implementar!");
    }

    public Transaccion txMayorValorUltimoBloque(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public Transaccion[] txUltimoBloque(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public int maximoTenedor(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public int montoMedioUltimoBloque(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public void hackearTx(){
        throw new UnsupportedOperationException("Implementar!");
    }
}
