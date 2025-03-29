package aed;

public class Fecha {
    int dia;
    int mes;

    public Fecha(int dia, int mes) {
        this.dia = dia;
        this.mes = mes;
    }

    public Fecha(Fecha fecha) {
        this.dia = fecha.dia;
        this.mes = fecha.mes;
    }

    public Integer dia() {
        return this.dia;
    }

    public Integer mes() {
        return this.mes;
    }
    
    @Override
    public String toString() {
        return dia + "/" + mes;
    }

    @Override
    public boolean equals(Object otra) {
        if (otra == null){
            return false;
        }
        if (otra instanceof Fecha){
            Fecha otraFecha = (Fecha) otra;

            int otroDia = otraFecha.dia;
            int otroMes = otraFecha.mes;

            if (otroDia == this.dia && otroMes == this.mes){
                return true;
            }
        }
            return false;
    }

    public void incrementarDia() {
        if (1 <= mes && mes <= 12){
            if (dia < diasEnMes(mes)){
                dia += 1;                    
            }
            dia = 1;
        }
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }
}
