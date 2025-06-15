package aed.ext;
import aed.Berretacoin;
import aed.Transaccion;

public class berretaCoinTests {
    public static void main(String[] args) {
        testsBase();
        testConHackeo();
        testsSaldos();
        testsCasosEspeciales();
    }

    public static void testsBase() {
        inicializar();  // Inicializa un nuevo Berretacoin()
        txUnica();      // Evaluo un bloque con una unica Tx
        multiplesTxs(); // Evaluo multiples Tx en un mismo Bloque (sin Tx de Creación)
        txCreacion();   // Evaluo Bloque con una Tx de Creación
    }

    public static void testConHackeo() {
        pruebaHackear();            // Hackeo la Tx de mayor monto
        agregarMultiplesBloques();  // Agrego múltiples bloques y evalúo las funciones cada vez que agrego uno nuevo
    }
    
    public static void testsSaldos() {
        testearSaldos();            // Testeo los saldos del maximo tenedor antes y después de hackear la Tx
        saldoNegativo();            // Veo qué saldo recibo en un usuario cuando da más monto del que tiene
    }

    public static void testsCasosEspeciales() {
        unicoUsuario();             // Veo el caso donde solo tengo un usuario trabajado en la Tx
        txsMismoMayorMonto();       // Evalúo qué pasa cuando tengo Txs con el mismo mayor monto (evalúo el mayor ID)
        soloTxCreacion();           // Evalúo las funciones cuando solo agrego Txs de Creación
        testearBloqueVacio();       // Evalúo qué pasa si agrego un bloque vacío (debería dar error)
    }

    public static void inicializar() {
        System.out.println("\n===== Iniciar Berretacoin =====");
        Berretacoin bc = new Berretacoin(5);

        int maxTenedor = bc.maximoTenedor();
        System.out.println("ID del maximo tenedor: " + maxTenedor);
        System.out.println("\n");
    }

    public static void txUnica() {
        System.out.println("===== Una sola Tx en bloque =====");
        Berretacoin bc = new Berretacoin(3);

        Transaccion[] txs = {
            new Transaccion(1, 1, 2, 3)
        };

        bc.agregarBloque(txs);

        System.out.println("Tx de mayor valor: " + bc.txMayorValorUltimoBloque().monto());
        System.out.println("ID del máximo tenedor: " + bc.maximoTenedor());
        System.out.println("Monto promedio: " + bc.montoMedioUltimoBloque());
        System.out.println("\n");
    }

    public static void multiplesTxs() {
        System.out.println("===== Multiples Txs en un solo bloque (sin Tx de Creación)=====");
        Berretacoin bc = new Berretacoin(4);
        Transaccion[] txs = {
            new Transaccion(1, 1, 2, 1),
            new Transaccion(2, 3, 4, 2),
            new Transaccion(3, 2, 1, 1)
        };

        bc.agregarBloque(txs);

        System.out.println("Tx de mayor valor: " + bc.txMayorValorUltimoBloque().monto());
        System.out.println("ID de maximo tenedor: " + bc.maximoTenedor());
        System.out.println("Monto promedio: " + bc.montoMedioUltimoBloque());
        System.out.println("\n");

    }

    public static void txCreacion() {
        System.out.println("===== Txs junto a una Tx de Creación =====");
        Berretacoin bc = new Berretacoin(3);

        Transaccion[] txs = {
            new Transaccion(1, 0, 1, 5),
            new Transaccion(2, 1, 2, 1)
        };

        bc.agregarBloque(txs);

        System.out.println("Tx de mayor valor: " + bc.txMayorValorUltimoBloque().monto());
        System.out.println("Maximo tenedor: " + bc.maximoTenedor());
        System.out.println("Monto promedio: (excluyendo Creación): " + bc.montoMedioUltimoBloque());
        System.out.println("\n");
    }

    public static void pruebaHackear() {
        System.out.println("==== Funcionalidad de Hackear ====");
        Berretacoin bc = new Berretacoin(4);

        Transaccion[] txs = {
            new Transaccion(1, 0, 2, 3),
            new Transaccion(2, 3, 4, 2),
            new Transaccion(3, 2, 3, 1)
        };

        bc.agregarBloque(txs);

        System.out.println("Antes de hackear: ");
        System.out.println("Tx con mayor valor: " + bc.txMayorValorUltimoBloque().monto());
        System.out.println("Maximo tenedor: " + bc.maximoTenedor());
        System.out.println("Monto promedio: " + bc.montoMedioUltimoBloque());

        bc.hackearTx();

        System.out.println("\nDespues de hackear:");
        System.out.println("Tx con mayor valor: " + bc.txMayorValorUltimoBloque().monto());
        System.out.println("Maximo tenedor: " + bc.maximoTenedor());
        System.out.println("Monto promedio: " + bc.montoMedioUltimoBloque());
        System.out.println("\n");
    }

    public static void agregarMultiplesBloques() {
        System.out.println("==== Agrego multiples bloques ====");
        Berretacoin bc = new Berretacoin(5);

        Transaccion[] bloque1 = {
            new Transaccion(1, 0, 5, 2),
            new Transaccion(2, 4, 1, 3)
        };
        bc.agregarBloque(bloque1);

        System.out.println("Maximo tenedor con Bloque1 agregado: " + bc.maximoTenedor());
        System.out.println("Mayor monto de ultimo Bloque (en este caso Bloque1): " + bc.txMayorValorUltimoBloque().monto());

        Transaccion[] bloque2 = {
            new Transaccion(3, 0, 4, 1),
            new Transaccion(4, 3, 2, 4)
        };
        bc.agregarBloque(bloque2);

        System.out.println("\nMaximo tenedor con Bloque2 agregado: " + bc.maximoTenedor());
        System.out.println("Mayor monto de ultimo Bloque (en este caso Bloque2): " + bc.txMayorValorUltimoBloque().monto());
        System.out.println("\n");
    }

    public static void testearSaldos() {
        System.out.println("==== Consistencia de los saldos ====");
        Berretacoin bc = new Berretacoin(3);

        Transaccion[] txs = {
            new Transaccion(1, 0, 2, 1),
            new Transaccion(2, 2, 3, 5),
            new Transaccion(3, 3, 1, 3)
        };

        bc.agregarBloque(txs);
        System.out.println("Maximo tenedor antes de hackear: " + bc.maximoTenedor());

        bc.hackearTx();

        System.out.println("Maximo tenedor despues de hackear: " + bc.maximoTenedor());
        System.out.println("\n");
    }

    public static void saldoNegativo() {
        System.out.println("==== Usuario con saldo insuficiente ====");
        Berretacoin bc = new Berretacoin(3);

        Transaccion[] bloque1 = {
            new Transaccion(1, 0, 1, 5)
        };

        bc.agregarBloque(bloque1);
        System.out.println("Maximo tenedor (bloque1): " + bc.maximoTenedor());

        Transaccion[] bloque2 = {
            new Transaccion(2, 1, 2, 7)
        };

        bc.agregarBloque(bloque2);
        System.out.println("Maximo tenedor (bloque2): " + bc.maximoTenedor());
        System.out.println("Usuario 1 ahora tiene monto negativo = -2");
        System.out.println("Usuario 2 tiene monto 7");
        System.out.println("\n");
    }

    public static void unicoUsuario() {
        System.out.println("==== Agregar un unico usuario ====");

        Berretacoin bc = new Berretacoin(1);

        Transaccion[] txs = {
            new Transaccion(1, 0, 1, 1)
        };

        bc.agregarBloque(txs);

        System.out.println("Maximo tenedor: " + bc.maximoTenedor());
        System.out.println("Monto Promedio: " + bc.montoMedioUltimoBloque());
        System.out.println("\n");
    }

    public static void txsMismoMayorMonto() {
        System.out.println("==== Txs con mismo monto ====");

        Berretacoin bc = new Berretacoin(5);

        Transaccion[] txs = {
            new Transaccion(2, 0, 2, 1),
            new Transaccion(3, 2, 3, 1),
            new Transaccion(4, 3, 4, 1),
            new Transaccion(5, 4, 5, 1)
        };

        bc.agregarBloque(txs);

        System.out.println("Tx mas alta: " + bc.txMayorValorUltimoBloque().id()); // uso ID porque ya se que todos los montos son iguales
        System.out.println("\n");
    }

    public static void soloTxCreacion() {
        System.out.println("==== Agregar solo Txs de Creacion ====");

        Berretacoin bc = new Berretacoin(3);

        Transaccion[] txs = {
            new Transaccion(1, 0, 1, 5),
            new Transaccion(2, 0, 2, 3),
            new Transaccion(3, 0, 3, 7)
        };

        bc.agregarBloque(txs);

        System.out.println("Maximo tenedor: " + bc.maximoTenedor());
        System.out.println("Promedio (debería ser 0 ya que son todas de Creación): " + bc.montoMedioUltimoBloque());
        System.out.println("Tx de mayor valor: " + bc.txMayorValorUltimoBloque().monto());
        System.out.println("\n");
    }

    public static void testearBloqueVacio() {
        System.out.println("==== Agregar un bloque vacío ====\n");

        System.out.println("Debería dar error de índice o devolver una excepción");
        System.out.println("ya que no puedo trabajar con un bloque vacío: \n");

        Berretacoin bc = new Berretacoin(3);
        Transaccion[] txsVacia = {};

        bc.agregarBloque(txsVacia);
        System.out.println("Monto promedio: " + bc.montoMedioUltimoBloque());
        System.out.println("\n");
    }
}


 