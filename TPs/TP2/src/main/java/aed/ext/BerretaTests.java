import aed.Berretacoin;
import aed.Transaccion;

public class BerretaTests {
    public static void main(String[] args) {
        testsBase();
        testConHackeo();
    }    


    public static void testsBase() {
        inicializar();
        txUnica();
        multiplesTxs();
        txCreacion();
    }

    public static void inicializar() {
        System.out.println("===== Iniciar Berretacoin =====");
        Berretacoin bc = new Berretacoin(5);

        int maxTenedor = bc.maximoTenedor();
        System.out.println("ID del maximo tenedor:" + maxTenedor);
    }

    public static void txUnica() {
        System.out.println("===== Una sola Tx en bloque =====");
        Berretacoin bc = new Berretacoin(3);

        Transaccion[] txs = {
            new Transaccion(1, 1, 2, 3)
        };

        bc.agregarBloque(txs);

        System.out.println("Valor de mayor tx: " + bc.txMayorValorUltimoBloque().monto());
        System.out.println("ID del máximo tenedor: " + bc.maximoTenedor());
        System.out.println("Monto promedio: " + bc.montoMedioUltimoBloque());
    }

    public static void multiplesTxs() {
        System.out.println("===== Multiples Txs en un solo bloque =====");
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
        System.out.println("ID del maximo tenedor: " + bc.maximoTenedor());
        System.out.println("Monto promedio: (excluyendo Creación): " + bc.montoMedioUltimoBloque());
    }

    public static void testConHackeo() {
        pruebaHackear();
        agregarMultiplesBloques();
        testearSaldos();
    }

    public static void pruebaHackear() {
        System.out.println("Pruebo funcionalidad de Hackear");
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

        bc.hackearTx();

        System.out.println("Despues de hackear:");
        System.out.println("Tx con mayor valor: " + bc.txMayorValorUltimoBloque().monto());
        System.out.println("Maximo tenedor: " + bc.maximoTenedor());
    }

    public static void agregarMultiplesBloques() {
        System.out.println("Agrego multiples bloques");
        Berretacoin bc = new Berretacoin(5);

        Transaccion[] bloque1 = {
            new Transaccion(1, 0, 5, 2),
            new Transaccion(2, 4, 1, 3)
        };
        bc.agregarBloque(bloque1);
        System.out.println("Maximo tenedor con Bloque1 agregado: " + bc.maximoTenedor());

        Transaccion[] bloque2 = {
            new Transaccion(3, 0, 2, 1),
            new Transaccion(4, 3, 4, 2)
        };
        bc.agregarBloque(bloque2);
        System.out.println("Maximo tenedor con Bloque2 agregado: " + bc.maximoTenedor());

        System.out.println("Mayor monto de ultimo Bloque (en este caso Bloque2): " + bc.txMayorValorUltimoBloque().monto());


    }

}


