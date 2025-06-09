// Lista Enlazada que tenga dentro:
//      Heap de prioridad de transacciones de mayor valor
//      Monto total 
//      Cantidad de Transacciones (Tx)

// Heap de prioridad de Usuario con mayor Monto (casi hecho)

// Array con Handles donde los ids coinciden con los indices de los Handles

package aed;
import aed.ext.*;

public class Berretacoin {

    private Usuario[] usuarios;
    private nodoTx ultimoBloque;

    public Berretacoin(int n_usuarios){

        usuarios = new Usuario[n_usuarios];
        for (int i = 0; i < n_usuarios; i++) {                 // Agrego usuarios de 1 a P a la lista de usuarios
            usuarios[i] = new Usuario(i + 1, 0);        // Inicializo usuarios con monto en 0
        }
        ultimoBloque = null;
    }

    public void agregarBloque(Transaccion[] transacciones){ 
        for (Transaccion tx : transacciones) {              // Recorro todas las Tx
            int comprador = tx.id_comprador();              // Asigno idC 
            int vendedor = tx.id_vendedor();                // Asigno idV
            int monto = tx.monto();                         // Asigno monto

            if (vendedor != 0) {
                usuarios[vendedor - 1].setMonto(usuarios[vendedor - 1].getMonto() - monto);
            }
            if (comprador != 0) {
            usuarios[comprador - 1].setMonto(usuarios[comprador - 1].getMonto() + monto);
            }
        }
    }

    public Transaccion txMayorValorUltimoBloque(){
        return (ultimoBloque != null) ? ultimoBloque.obtenerHeap().devolverPrimero() : null;
    }

    public Transaccion[] txUltimoBloque(){
        return (ultimoBloque != null) 
        ? ultimoBloque.obtenerHeap().obtenerElementos().toArray(new Transaccion[0])
        : new Transaccion[0];
    }

    public int maximoTenedor(){
        usuarioMayorMonto heap = new usuarioMayorMonto(usuarios.length);
        for (Usuario u : usuarios) {
            heap.insertar(u);
        }
        return heap.verMax().getId();
    }

    public int montoMedioUltimoBloque(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public void hackearTx(){
        throw new UnsupportedOperationException("Implementar!");
    }
}
