package aed;
import aed.ext.*;

public class Berretacoin {
    UsuarioConHandle[] usuarios;                                // Array de Usuarios con Handle
    nodoTx ultimoBloque;                                        // Direcion del ultimo Bloque de Txs
    maxHeapUsuarios heapUsuarios;                               // Usuario de mayor monto actual

    public Berretacoin(int n_usuarios){
        usuarios = new UsuarioConHandle[n_usuarios];                     // Creo array de Usuarios con n usuarios
        heapUsuarios = new maxHeapUsuarios();
        
        for (int i = 0; i < n_usuarios; i++) {                    // Agrego usuarios de 1 a P a la lista de usuarios
            usuarios[i] = new UsuarioConHandle(i + 1, 0);   // Y los inicializo con monto = 0
            heapUsuarios.insertar(usuarios[i]);                   
        }

        ultimoBloque = null;                                    // No tengo Bloques creados
    }


    // Complejidad O(P + n_b) = O(Cantidad de usuarios + Cantidad de Txs en bloque)
    public void agregarBloque(Transaccion[] transacciones){         
        nodoTx nuevoNodo = new nodoTx(transacciones);           // Creo un nuevo Nodo con las Tx del input (O(1))
        ultimoBloque = nuevoNodo;                               // Actualizo el ultimo Bloque agregado (ahora es el nuevo) (O(1))

        for (Transaccion tx : transacciones) {                  // Recorro todas las Tx (O(n_b))
            int comprador = tx.id_comprador();                  // Asigno idComprador (O(1))
            int vendedor = tx.id_vendedor();                    // Asigno idVendedor (O(1))
            int monto = tx.monto();                             // Asigno monto (O(1))

            if (vendedor != 0) {
                UsuarioConHandle u = usuarios[vendedor - 1];    // Busco la posicion del vendedor en el array de Usuarios (O(1))
                u.setMonto(u.getMonto() + monto);               // Sumo el monto al vendedor (O(1))
                heapUsuarios.actualizarHeap(u);                 // Evaluo si hay un nuevo Usuario con mayor monto (O(1))
            }

            if (comprador != 0) {
                UsuarioConHandle u = usuarios[comprador - 1];   // Busco la posicion del comprador en el array de Usuarios (O(1))
                u.setMonto(u.getMonto() - monto);               // Resto el monto al comprador (O(1))
                heapUsuarios.actualizarHeap(u);                 // Evaluo si hay un nuevo Usuario con mayor monto (O(1))
            }
        }
    }

    // Complejidad O(1)
    public Transaccion txMayorValorUltimoBloque(){            
        if (ultimoBloque == null || ultimoBloque.obtenerHeap().tamano() == 0) {
            return null;
        }  
        return ultimoBloque.obtenerHeap().devolverPrimero();    // Devuelvo el primer elemn del heap del ultimo bloque (O(1))
    }

    // Complejidad O(1), obtenerTransacciones() devuelve una copia del array de Txs
    public Transaccion[] txUltimoBloque(){
        if (ultimoBloque == null) {
            return new Transaccion[0];
        }
        return ultimoBloque.obtenerTransacciones();             // Devuelvo las transacciones del ultimo Bloque (en caso que no tenga, devuelve vacío)
    }

    // Complejidad O(1), devuelvo una variable ya guardada
    public int maximoTenedor(){
        UsuarioConHandle maxUser = heapUsuarios.obtenerMaximo();                              // Devuelvo ID del usuario con mayor monto (O(1))
        return maxUser != null ? maxUser.getId() : 0;
    }

    // Complejidad O(1), accedo a valores ya calculados del ultimoBloque
    public int montoMedioUltimoBloque(){
        if (ultimoBloque == null || ultimoBloque.totalTxSinCreacion() <= 0) return 0;
        // Si ultimoBloque no tiene Tx o no hay mas de 1 Tx (la de creacion) devuelvo 0
        return ultimoBloque.montoTotalSinCreacion() / ultimoBloque.totalTxSinCreacion();
        // Sino, devuelvo los montos / la cantidad de Tx (ambos sin la de creacion)
    }

    // Complejidad O(log P * log(n_b))
    public void hackearTx() {         
        Transaccion bloqueHackeado = ultimoBloque.obtenerHeap().devolverPrimero();

        int compradorId = bloqueHackeado.id_comprador();    // Guardo ID de comprador (O(1))
        int vendedorId = bloqueHackeado.id_vendedor();      // Guardo ID de vendedor (O(1))

        // Revertir los saldos de la transacción hackeada
        if (compradorId != 0) {
            UsuarioConHandle comprador = usuarios[compradorId - 1];                      // Busco al comprador en la lista de Usuarios (O(1))
            comprador.setMonto(comprador.getMonto() + bloqueHackeado.monto());  // Revierto monto a comprador (O(1)) 
            heapUsuarios.actualizarHeap(comprador);
        }
        if (vendedorId != 0) {
            UsuarioConHandle vendedor = usuarios[vendedorId - 1];                        // Busco al comprador en la lista de Usuarios (O(1))
            vendedor.setMonto(vendedor.getMonto() - bloqueHackeado.monto());    // Revierto monto a comprador (O(1))
            heapUsuarios.actualizarHeap(vendedor);
        }

        ultimoBloque.eliminarPrimero();

        // Actualizo montos y cantidad de Txs despues de hackear
        if (bloqueHackeado.id_comprador() == 0) {
            ultimoBloque.restarMontoTotal(bloqueHackeado.monto());
            ultimoBloque.restarCantidadTx();
        } else {
            ultimoBloque.restarMontoTotalSinCreacion(bloqueHackeado.monto());
            ultimoBloque.restarCantidadTxSinCreacion();
        }
    }
}
