package aed;

public class Agenda {
    private final ArregloRedimensionableDeRecordatorios recordatorios;
    private final Fecha fecha;

    public Agenda(Fecha fechaActual) {
        fecha = new Fecha(fechaActual);
        recordatorios = new ArregloRedimensionableDeRecordatorios();
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        recordatorios.agregarAtras(recordatorio);
    }

    @Override
    public String toString() {
        String agendaHoy = "";

        for (int i = 0; i < recordatorios.longitud(); i++){
            Recordatorio recordatorio = recordatorios.obtener(i);
                // Comparo fechas
                if (recordatorio.fecha().equals(fecha)){
                // Si cumple, la agrego a la agenda de hoy
                    agendaHoy += recordatorio.toString() + "\n";
                }
            }
        return fecha.toString() + "\n" + "=====\n" + agendaHoy;
    }

    public void incrementarDia() {
        fecha.incrementarDia();
    }

    public Fecha fechaActual() {
        return fecha;
    }

}
