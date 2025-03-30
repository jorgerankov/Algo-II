package aed;
import java.util.ArrayList;

class ArregloRedimensionableDeRecordatorios {
    int longitud;
    Recordatorio agregarAtras;
    Recordatorio obtener;
    private ArrayList<Recordatorio> recordatorios;

    public ArregloRedimensionableDeRecordatorios() {
        this.recordatorios = new ArrayList<>();
    }

    public int longitud() {
        return recordatorios.size();
    }

    public void agregarAtras(Recordatorio i) {
        recordatorios.add(i);
    }

    public Recordatorio obtener(int i) {
        return recordatorios.get(i);
    }

    public void quitarAtras() {
        recordatorios.remove(recordatorios.size() - 1);
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        recordatorios.set(indice, valor);
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        this.recordatorios = new ArrayList<>();
        for (Recordatorio rec : vector.recordatorios){
            this.recordatorios.add(new Recordatorio(rec.mensaje(),rec.fecha(),rec.horario()));
        }
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        return new ArregloRedimensionableDeRecordatorios(this);
    }
}
