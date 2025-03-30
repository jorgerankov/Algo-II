package aed;

public class Recordatorio {
    String mensaje;
    Fecha fecha;
    Horario horario;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.horario = horario;
    }

    public Horario horario() {
        return this.horario;
    }

    public Fecha fecha() {
        return this.fecha;
    }

    public String mensaje() {
        return this.mensaje;
    }

    @Override
    public String toString() {
        return mensaje + "@" + fecha + horario;
    }

    @Override
    public boolean equals(Object otro) {
        if (this == otro){
            return true; // Si ambos objetos son la misma instancia, son iguales
        }
        if (otro == null || getClass() != otro.getClass()){
            return false; // Si es null o no es de la clase Recordatorio, no son iguales
        }
        Recordatorio otroRecordatorio = (Recordatorio) otro;
        return mensaje.equals(otroRecordatorio.mensaje) &&
        fecha.equals(otroRecordatorio.fecha) &&
        horario.equals(otroRecordatorio.horario); // comparo sus valores en lugar de sus referencias
    }

}
