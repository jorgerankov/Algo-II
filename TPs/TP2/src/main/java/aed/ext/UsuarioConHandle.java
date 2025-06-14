package aed.ext;

public class UsuarioConHandle {
    public int id;
    public int monto;
    private Handle handle;

    public UsuarioConHandle(int id, int monto) {
        this.id = id;
        this.monto = monto;
        this.handle = null;
    }

    // devuelvo ID de usuario
    public int getId() {
        return id;                      
    }

    // Devuelvo monto de usuario
    public int getMonto() {
        return monto;                   
    }

    // Defino el monto para cada usuario
    public void setMonto(int monto) {
        this.monto = monto;             
    }

    // Defino el handle para el usuario creado
    public void setHandle(Handle handle) {
        this.handle = handle;
    }

    // Devuelvo el handle del usuario que estoy buscando
    public Handle getHandle() {
        return handle;
    }

    // Busco el maximo tenedor de montos entre 2 usuarios
    public static UsuarioConHandle maximo(UsuarioConHandle a, UsuarioConHandle b) {
        if (a == null) return b;            // Si no existe a, devuelvo b
        if (b == null) return a;            // y viceversa

        if (a.getMonto() > b.getMonto()) {  // Si a tiene mas monto que b
            return a;                       // devuelvo a 
        } else if (b.getMonto() > a.getMonto()) {
            return b;                       // y viceversa
        } else if (a.getId() < b.getId()){  // Pero si tienen = monto, evaluo el menor ID
            return a;                       // Y devuelvo al usuario con el menor ID
        } else {
            return b;
        }
    }
}
