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
    private Usuario usuarioMax;

    public Berretacoin(int n_usuarios){
        usuarios = new Usuario[n_usuarios];
        for (int i = 0; i < n_usuarios; i++) {                 // Agrego usuarios de 1 a P a la lista de usuarios
            usuarios[i] = new Usuario(i + 1, 0);        // Inicializo usuarios con monto en 0
        }
        usuarioMax = usuarios[0];
        ultimoBloque = null;
    }

    public void agregarBloque(Transaccion[] transacciones){
        nodoTx nuevoNodo = new nodoTx(transacciones);
        nuevoNodo.setAnterior(ultimoBloque);
        ultimoBloque = nuevoNodo;

        for (Transaccion tx : transacciones) {                                                      // Recorro todas las Tx
            int comprador = tx.id_comprador();                                                      // Asigno idComprador
            int vendedor = tx.id_vendedor();                                                        // Asigno idVendedor
            int monto = tx.monto();                                                                 // Asigno monto

            if (vendedor != 0) {
                usuarios[vendedor - 1].setMonto(usuarios[vendedor - 1].getMonto() + monto);         // Cambio monto de vendedor cuando vendo
                actualizarUsuarioMax(usuarios[vendedor - 1]);
            }

            if (comprador != 0) {
                usuarios[comprador - 1].setMonto(usuarios[comprador - 1].getMonto() - monto);       // Cambio monto de comprador cuando compro
                actualizarUsuarioMax(usuarios[comprador - 1]);
            }
        }

        actualizarMaximoTenedor();  // Actualizo si cambia orden
    }

    private void actualizarUsuarioMax(Usuario u) {
        if (usuarioMax == null || u.getMonto() > usuarioMax.getMonto() ||
            (u.getMonto() == usuarioMax.getMonto() && u.getId() < usuarioMax.getId())) {
            usuarioMax = u;
        }
    }

    private void actualizarMaximoTenedor() {
        usuarioMax = usuarios[0];
        for (int i = 1; i < usuarios.length; i++) {
            Usuario actual = usuarios[i];
            if (actual.getMonto() > usuarioMax.getMonto() ||
                (actual.getMonto() == usuarioMax.getMonto() &&
                    actual.getId() < usuarioMax.getId())) {
                        usuarioMax = actual;
                    } 
        }
    }

    public Transaccion txMayorValorUltimoBloque(){
        return (ultimoBloque != null) ? ultimoBloque.obtenerHeap().devolverPrimero() : null;
    }

    public Transaccion[] txUltimoBloque(){
        if (ultimoBloque == null) return new Transaccion[0];
        return ultimoBloque.obtenerTransacciones();
    }

    public int maximoTenedor(){
        return usuarioMax.getId();
    }

    public int montoMedioUltimoBloque(){
        if (ultimoBloque == null || ultimoBloque.obtenerCantidadSinCreacion() <= 1) return 0;
        return ultimoBloque.obtenerMontoSinCreacion() / ultimoBloque.obtenerCantidadSinCreacion();
    }

    public void hackearTx(){
        throw new UnsupportedOperationException("Implementar!");
    }
}
