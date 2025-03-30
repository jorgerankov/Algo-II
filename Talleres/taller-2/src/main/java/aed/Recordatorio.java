package aed;

public class Recordatorio {
    private final String mensaje;
    private final Horario horario;
    private final Fecha fecha;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        this.mensaje = mensaje;
        this.fecha = new Fecha(fecha);
        this.horario = horario;
    }

    public Horario horario() {
        return this.horario;
    }

    public Fecha fecha() {
        return new Fecha(this.fecha); // Copia de fecha
    }

    public String mensaje() {
        return this.mensaje;
    }

    @Override
    public String toString() {
        return mensaje + " @ " + fecha + " " + horario.toString();
    }

    @Override
    public boolean equals(Object otro) {
        if (this == otro){
            return true; // Si ambos objetos son la misma instancia, son iguales
        }
        if (otro == null || !(otro instanceof Recordatorio)){
            return false; // Si es null o no es de la clase Recordatorio, no son iguales
        }
        Recordatorio otroRecordatorio = (Recordatorio) otro;
        return mensaje.equals(otroRecordatorio.mensaje) &&
        fecha.equals(otroRecordatorio.fecha) &&
        horario.equals(otroRecordatorio.horario); // comparo sus valores en lugar de sus referencias
    }

}
