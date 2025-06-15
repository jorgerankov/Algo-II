package aed;
import aed.ext.UsuarioConHandle;
import aed.ext.maxHeapUsuarios;
import aed.ext.nodoTx;

public class Berretacoin {
    UsuarioConHandle[] usuarios;                                    // Array de Usuarios con Handle
    nodoTx ultimoBloque;                                            // Direcion del ultimo Bloque de Txs
    maxHeapUsuarios heapUsuarios;                                   // Usuario de mayor monto actual

    public Berretacoin(int n_usuarios){
        usuarios = new UsuarioConHandle[n_usuarios];                // Creo array de Usuarios con n usuarios
        heapUsuarios = new maxHeapUsuarios();
        
        for (int i = 0; i < n_usuarios; i++) {                      // Agrego usuarios de 1 a P a la lista de usuarios
            usuarios[i] = new UsuarioConHandle(i + 1, 0);     // Y los inicializo con monto = 0
            heapUsuarios.insertar(usuarios[i]);                   
        }

        ultimoBloque = null;                                        // No tengo Bloques creados
    }

    // Complejidad O(n_b * log P) = O(Cantidad de usuarios + Cantidad de Txs en bloque)
    public void agregarBloque(Transaccion[] transacciones){         
        nodoTx nuevoNodo = new nodoTx(transacciones);           // Creo un nuevo Nodo con las Tx del input (O(1))
        ultimoBloque = nuevoNodo;                               // Actualizo el ultimo Bloque agregado (ahora es el nuevo) (O(1))

        for (Transaccion tx : transacciones) {                  // Recorro todas las Tx (O(n_b))
            int comprador = tx.id_comprador();                  // Asigno idComprador (O(1))
            int vendedor = tx.id_vendedor();                    // Asigno idVendedor (O(1))
            int monto = tx.monto();                             // Asigno monto (O(1))

            if (vendedor != 0) {
                UsuarioConHandle u = usuarios[vendedor - 1];    // Busco la posicion del vendedor (O(1))
                u.setMonto(u.getMonto() + monto);               // Sumo el monto al vendedor (O(1))
                heapUsuarios.actualizarHeap(u);                 // Actualizo el heap de usuarios (log P)
            }

            if (comprador != 0) {
                UsuarioConHandle u = usuarios[comprador - 1];   // Busco la posicion del comprador (O(1))
                u.setMonto(u.getMonto() - monto);               // Resto el monto al comprador (O(1))
                heapUsuarios.actualizarHeap(u);                 // Actualizo el heap de usuarios (log P)
            }
        }
    }

    // Complejidad O(1), devuelvo el primer elemento del Heap
    public Transaccion txMayorValorUltimoBloque(){            
        return ultimoBloque.obtenerHeap().devolverPrimero();    // Devuelvo el primer elem del heap del ultimo bloque (O(1))
    }

    // Complejidad O(n_b), obtenerTransacciones() devuelve una copia del array de Txs
    public Transaccion[] txUltimoBloque(){
        return ultimoBloque.obtenerTransacciones();             // Devuelvo las transacciones del ultimo Bloque (en caso que no tenga, devuelve vacío)
    }

    // Complejidad O(1), devuelvo una variable ya guardada
    public int maximoTenedor(){
        UsuarioConHandle maxTenedor = heapUsuarios.obtenerMaximo();                         // Devuelvo el ID del usuario con mayor monto (O(1))
        return maxTenedor != null ? maxTenedor.getId() : 0;
    }

    // Complejidad O(1), accedo a valores ya calculados del ultimoBloque
    public int montoMedioUltimoBloque(){
        if (ultimoBloque == null || ultimoBloque.totalTxSinCreacion() <= 0) return 0;       // Si ultimoBloque no tiene Tx o no hay mas de 1 Tx (la de creacion) devuelvo 0
        return ultimoBloque.montoTotalSinCreacion() / ultimoBloque.totalTxSinCreacion();    // Sino, devuelvo los montos / la cantidad de Tx (ambos sin la de creacion)
        
    }

    // Complejidad O(log(n_b) + log P), accedo a modificar el heap tanto de los usuarios como el de las Txs
    public void hackearTx() {         
        Transaccion bloqueHackeado = ultimoBloque.obtenerHeap().devolverPrimero();

        int compradorId = bloqueHackeado.id_comprador();                        // Guardo ID de comprador (O(1))
        int vendedorId = bloqueHackeado.id_vendedor();                          // Guardo ID de vendedor (O(1))

        // Revierto los saldos de la transacción hackeada
        if (compradorId != 0) {
            UsuarioConHandle comprador = usuarios[compradorId - 1];             // Busco al comprador en la lista de Usuarios (O(1))
            comprador.setMonto(comprador.getMonto() + bloqueHackeado.monto());  // Revierto monto a comprador (O(1)) 
            heapUsuarios.actualizarHeap(comprador);                             // Accedo al Handle del usuario y actualizo su posicion en el heap (O(log P))
        }
        if (vendedorId != 0) {
            UsuarioConHandle vendedor = usuarios[vendedorId - 1];               // Busco al comprador en la lista de Usuarios (O(1))
            vendedor.setMonto(vendedor.getMonto() - bloqueHackeado.monto());    // Revierto monto a comprador (O(1))
            heapUsuarios.actualizarHeap(vendedor);                              // Accedo al Handle del usuario y actualizo su posicion en el heap (O(log P))
        }

        ultimoBloque.eliminarPrimero();                                         // Elimino la Tx de mayor valor (la primera) del Bloque y actualizo el heap de Txs (O(log n_b))

        // Actualizo montos y cantidad de Txs despues de hackear
        if (bloqueHackeado.id_comprador() == 0) {
            ultimoBloque.restarMontoTotal(bloqueHackeado.monto());              // Resto el monto hackeado del total (O(1))
            ultimoBloque.restarCantidadTx();                                    // Resto en 1 la cantidad de Txs totales que hay (O(1))
        } else {
            ultimoBloque.restarMontoTotalSinCreacion(bloqueHackeado.monto());   // Resto el monto hackeado del total sin Creacion (O(1))
            ultimoBloque.restarCantidadTxSinCreacion();                         // Resto en 1 la cantidad de Txs totales que hay sin Creacion (O(1))
        }
    }
}
