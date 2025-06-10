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
                Usuario u = usuarios[vendedor - 1];
                u.setMonto(u.getMonto() + monto);           // Sumo el monto al vendedor
                actualizarUsuarioMax(u);                    // Evaluo nuevo Usuario con mayor monto
            }

            if (comprador != 0) {
                Usuario u = usuarios[comprador - 1];
                u.setMonto(u.getMonto() - monto);           // Resto el monto al comprador
                actualizarUsuarioMax(u);                    // Evaluo nuevo Usuario con mayor monto
            }
        }

        actualizarMaximoTenedor();                          // Actualizo si cambia orden de usuarioMax
    }


    private void actualizarUsuarioMax(Usuario u) {
        usuarioMax = Usuario.maximo(usuarioMax, u);         // Veo si u es mayor que el usuario con mayor monto
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
        if (ultimoBloque == null || ultimoBloque.totalTxSinCreacion() <= 1) return 0;
        // Si ultimoBloque no tiene Tx o no hay mas de 1 Tx (la de creacion) devuelvo 0
        return ultimoBloque.montoTotalSinCreacion() / ultimoBloque.totalTxSinCreacion();
        // Sino, devuelvo los montos / la cantidad de Tx (ambos sin la de creacion)
    }


    public void hackearTx() {
        Transaccion bloqueHackeado = ultimoBloque.obtenerHeap().devolverPrimero();  // Obtengo la Tx a hackear

        int compradorId = bloqueHackeado.id_comprador();                        // Tomo el ID del Comprador del Bloque a hackear
        int vendedorId = bloqueHackeado.id_vendedor();                          // Tomo el ID del Vendedor del Bloque a hackear

        if (compradorId != 0) {                                                 // Evaluo que el ID no sea el primero para que no me salte error al buscar compradorID[0-1 = -1]
            Usuario comprador = usuarios[compradorId - 1];                      // Busco el ID del Comprador en la lista de Usuarios
            comprador.setMonto(comprador.getMonto() + bloqueHackeado.monto());  // Devuelvo monto al Comprador
        } else {
            Usuario comprador = usuarios[compradorId];                          // Si es el primer comprador, lo defino
            comprador.setMonto(comprador.getMonto() + bloqueHackeado.monto());  // Devuelvo monto al Comprador
        }

        if (vendedorId != 0) {                                                  // Evaluo que el ID no sea el primero para que no me salte error al buscar vendedorID[0-1 = -1]
            Usuario vendedor = usuarios[vendedorId - 1];                        // Busco el ID del Vendedor en la lista de Usuarios
            vendedor.setMonto(vendedor.getMonto() - bloqueHackeado.monto());    // Le saco el monto al Vendedor
        } else {
            Usuario vendedor = usuarios[vendedorId];                            // Si es el primer vendedor, lo defino
            vendedor.setMonto(vendedor.getMonto() - bloqueHackeado.monto());    // Le saco el monto al Vendedor
        }

        ultimoBloque.obtenerHeap().eliminar(bloqueHackeado);                    // Elimino del Heap el Bloque a hackear
        ultimoBloque.obtenerHeapPostHack();


        Transaccion[] Txs = ultimoBloque.obtenerTransacciones();                // Tomo el Bloque de Txs con la Tx a eliminar
        int longitudTxs = Txs.length;                                           // Y su longitud
        Transaccion[] nuevoBloque = new Transaccion[longitudTxs - 1];           // Creo un nuevo Bloque de Txs sin la Tx eliminada
        int idx = 0;                                                
        for (int i = 0; i < longitudTxs; i++) {
            if (!Txs[i].equals(bloqueHackeado)) {                               // Si la Tx analizada no es la que quiero hackear
                nuevoBloque[idx++] = Txs[i];                                    // La agrego al nuevo Bloque de Txs
            }
        }
        ultimoBloque.nuevasTx(nuevoBloque);                                     // Creo un nuevo conjunto de Tx sin la Tx hackeada

        ultimoBloque.restarMontoTotal(bloqueHackeado.monto());                  // Saco el monto de la Tx hackeada al ultimo Bloque
        ultimoBloque.restarCantidadTx();                                        // -1 Txs (saco la hackeada)

        actualizarMaximoTenedor();
    }
}
