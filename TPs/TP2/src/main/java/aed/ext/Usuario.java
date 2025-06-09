package aed.ext;

public class Usuario {
    public int id;
    public int monto;

    public Usuario(int id, int monto) {
        this.id = id;
        this.monto = monto;
    }

    public int getId() {
        return id;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }
}
