package aed;

public class Horario {
    int hora;
    int minutos;

    public Horario(int hora, int minutos) {
        this.hora = hora;
        this.minutos = minutos;
    }

    public int hora() {
        return this.hora;
    }

    public int minutos() {
        return this.minutos;
    }

    @Override
    public String toString() {
        String horaConCero = "0" + hora;
        String minutoConCero = "0" + minutos;

        if (hora >= 10 && minutos < 10){
            return (hora+":"+minutoConCero);
        }
        else if (hora < 10 && minutos >= 10){
            return (horaConCero+":"+minutos);
        }
        else if(hora < 10 && minutos < 10){
            return (horaConCero+":"+minutoConCero);
        }
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
