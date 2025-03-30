package aed;

public class Agenda {
    private ArregloRedimensionableDeRecordatorios recordatorios;
    private Fecha fecha;

    public Agenda(Fecha fechaActual) {
        this.fecha = new Fecha(fechaActual);
        this.recordatorios = new ArregloRedimensionableDeRecordatorios();
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        recordatorios.agregarAtras(recordatorio);
    }

    @Override
    public String toString() {
        StringBuilder agendaHoy = new StringBuilder();

        agendaHoy.append(fecha.toString());
        agendaHoy.append("\n");
        agendaHoy.append("=====\n");

        for (int i = 0; i < recordatorios.longitud(); i++){
            Recordatorio recordatorio = recordatorios.obtener(i);
                // Comparo fechas
                if (recordatorio.fecha().equals(fecha)){
                // Si cumple, la agrego a la agenda de hoy
                    agendaHoy.append(recordatorio.toString());
                    agendaHoy.append("\n");
                }
            }
        return agendaHoy.toString();
    }

    public void incrementarDia() {
        // Implementar
    }

    public Fecha fechaActual() {
        // Implementar
        return null;
    }

}
