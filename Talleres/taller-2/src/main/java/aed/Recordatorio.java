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
        return (mensaje + "@" + fecha + horario);
    }

    @Override
    public boolean equals(Object otro) {
        if (otro == null){
            return false;
        }
        if (otro instanceof Recordatorio){
            Recordatorio otroRecordatorio = (Recordatorio) otro;
            
            String otroMsj = otroRecordatorio.mensaje;
            Fecha otraFecha = otroRecordatorio.fecha;
            Horario otroHorario = otroRecordatorio.horario;

            if (otroMsj == this.mensaje && otraFecha == this.fecha &&
                otroHorario == this.horario){
                    return true;
            }

        }
        return false;
    }

}
