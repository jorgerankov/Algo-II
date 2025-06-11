// Lista Enlazada que tenga dentro:
//      Heap de prioridad de transacciones de mayor valor
//      Monto total 
//      Cantidad de Transacciones (Tx)

// Heap de prioridad de Usuario con mayor Monto (casi hecho)

// Array con Handles donde los ids coinciden con los indices de los Handles

package aed;
import aed.ext.Usuario;
import aed.ext.nodoTx;


public class Berretacoin {
    private Usuario[] usuarios;                             // Array de Usuarios
    private nodoTx ultimoBloque;                            // Apunta al ultimo Bloque de Tx
    private Usuario usuarioMax;                             // Usuario de mayor monto actual

    public Berretacoin(int n_usuarios){
        usuarios = new Usuario[n_usuarios];                 // Creo array de Usuarios con n usuarios
        for (int i = 0; i < n_usuarios; i++) {              // Agrego usuarios de 1 a P a la lista de usuarios
            usuarios[i] = new Usuario(i + 1, 0);      // Y los inicializo con monto = 0
        }
        usuarioMax = usuarios[0];                           // Inicio Usuario de mayor monto con el primero
        ultimoBloque = null;                                // No tengo Bloques creados
    }


    public void agregarBloque(Transaccion[] transacciones){
        nodoTx nuevoNodo = new nodoTx(transacciones);       // Creo un nuevo Nodo con las Tx del input
        nuevoNodo.apuntarAnterior(ultimoBloque);            // Apunto el nuevo al Bloque anterior
        ultimoBloque = nuevoNodo;                           // Actualizo el ultimo Bloque agregado (ahora es el nuevo)

        for (Transaccion tx : transacciones) {              // Recorro todas las Tx
            int comprador = tx.id_comprador();              // Asigno idComprador
            int vendedor = tx.id_vendedor();                // Asigno idVendedor
            int monto = tx.monto();                         // Asigno monto

            if (vendedor != 0) {
                Usuario u = usuarios[vendedor - 1];         // Busco la posicion del vendedor en el array de Usuarios
                u.setMonto(u.getMonto() + monto);           // Sumo el monto al vendedor
                actualizarUsuarioMax(u);                    // Evaluo si hay un nuevo Usuario con mayor monto
            }

            if (comprador != 0) {
                Usuario u = usuarios[comprador - 1];        // Busco la posicion del comprador en el array de Usuarios
                u.setMonto(u.getMonto() - monto);           // Resto el monto al comprador
                actualizarUsuarioMax(u);                    // Evaluo si hay un nuevo Usuario con mayor monto
            }
        }

        actualizarMaximoTenedor();                          // Actualizo si cambia orden de usuarioMax
    }


    private void actualizarUsuarioMax(Usuario u) {
        if (u == usuarioMax) {
            actualizarMaximoTenedor();                      // Evaluo todo de 0
        } else {
            usuarioMax = Usuario.maximo(usuarioMax, u);     // Veo si u es mayor que el usuario con mayor monto
        }
    }


    private void actualizarMaximoTenedor() {
        for (Usuario u : usuarios) {                        // Recorro todos los usuarios
            usuarioMax = Usuario.maximo(usuarioMax, u);     // Evaluo maximo tenedor o minimo ID en caso de haber +1 con mismo monto maximo
        }
    }


    public Transaccion txMayorValorUltimoBloque(){
        if (ultimoBloque == null) return null;
        return ultimoBloque.obtenerHeap().devolverPrimero();
    }


    public Transaccion[] txUltimoBloque(){
        if (ultimoBloque == null) {
            return new Transaccion[0];                      // Si no hay Bloque, devuelvo un array vacio
        }
        return ultimoBloque.obtenerTransacciones();         // Sino, devuelvo las transacciones del ultimo Bloque
    }


    public int maximoTenedor(){
        return usuarioMax.getId();                          // ID del usuario con mayor monto
    }


    public int montoMedioUltimoBloque(){
        if (ultimoBloque == null || ultimoBloque.totalTx() <= 1) return 0;
        // Si ultimoBloque no tiene Tx o no hay mas de 1 Tx (la de creacion) devuelvo 0
        return ultimoBloque.montoTotalSinCreacion() / ultimoBloque.totalTxSinCreacion();
        // Sino, devuelvo los montos / la cantidad de Tx (ambos sin la de creacion)
    }


    public void hackearTx() {
        Transaccion bloqueHackeado = ultimoBloque.obtenerHeap().devolverPrimero();      // Primer Tx del bloque sin hackear

        int compradorId = bloqueHackeado.id_comprador();                                // IDC de la primer Tx
        int vendedorId = bloqueHackeado.id_vendedor();                                  // IDV de la primer Tx
        

        if (compradorId != 0) {
            Usuario comprador = usuarios[compradorId - 1];
            comprador.setMonto(comprador.getMonto() + bloqueHackeado.monto());
            actualizarUsuarioMax(comprador);
        }

        if (vendedorId != 0) {
            Usuario vendedor = usuarios[vendedorId - 1];
            vendedor.setMonto(vendedor.getMonto() - bloqueHackeado.monto());
            actualizarUsuarioMax(vendedor);
        }

        actualizarMaximoTenedor();

        // Elimino la transacción hackeada directamente del heap y del arreglo de transacciones del bloque
        ultimoBloque.obtenerHeap().eliminar(bloqueHackeado);                // Elimino la Tx del Heap
        ultimoBloque.eliminar(bloqueHackeado);                              // Elimino la Tx hackeada del array de Tx

        ultimoBloque.restarMontoTotal(bloqueHackeado.monto());              // Resto de montoTotal el monto hackeado con Creacion
        ultimoBloque.restarMontoTotalSinCreacion(bloqueHackeado.monto());   // Y sin Creacion

        ultimoBloque.restarCantidadTx();                                    // Resto en 1 la cantidad de Txs con Creacion
        ultimoBloque.restarCantidadTxSinCreacion();                         // Y sin Creacion

        txMayorValorUltimoBloque();
        txUltimoBloque();
        maximoTenedor();
    }
}
