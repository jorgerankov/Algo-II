package aed;

public class Transaccion implements Comparable<Transaccion> {
    private int id;
    private int id_comprador;
    private int id_vendedor;
    private int monto;

    public Transaccion(int id, int id_comprador, int id_vendedor, int monto) {
        this.id = id;
        this.id_comprador = id_comprador;
        this.id_vendedor = id_vendedor;
        this.monto = monto;
    }

    @Override
    public int compareTo(Transaccion otro) {
        if (this.monto != otro.monto) {
            return Integer.compare(this.monto, otro.monto);     // mayor Monto si es unico
        } else {
            return Integer.compare(this.id, otro.id);           // Si son iguales, comparo los IDs
        }
    }

    @Override
    public boolean equals(Object otro){        
        // Modifico tipo Object por tipo Transaccion para evaluar las 2 Tx 
        Transaccion t = (Transaccion) otro; 

        // Comparo todos los atributos de la Tx y otro, si son todos = devuelve True, sino False
        return id == t.id &&
                id_comprador == t.id_comprador &&
                id_vendedor == t.id_vendedor &&
                monto == t.monto;
    }

    public int id() {
        return id;
    }

    public int id_comprador() {
        return id_comprador;
    }
    
    public int id_vendedor() {
        return id_vendedor;
    }

    public int monto() {
        return monto;
    }
}