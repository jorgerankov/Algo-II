package aed;

public class Horario {
    int hora;
    int minutos;

    public Horario(int hora, int minutos) {
        this.hora = hora;
        this.minutos = minutos;
    }

    public int hora() {
        return hora;
    }

    public int minutos() {
        return minutos;
    }

    @Override
    public String toString() {
        return (hora+":"+minutos);
    }

    @Override
    public boolean equals(Object otro) {
        if (otro == null){
            return false;
        }
        if (otro instanceof Horario){
            Horario otroHorario = (Horario) otro;
            int otraHora = otroHorario.hora;
            int otroMin = otroHorario.minutos;

            if (otraHora == this.hora && otroMin == this.minutos){
                return true;
            }
        }
        return false;
    }
}
