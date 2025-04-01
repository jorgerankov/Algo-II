package aed;
import java.util.ArrayList;

class ArregloRedimensionableDeRecordatorios {
    private final ArrayList<Recordatorio> recordatorios;

    public ArregloRedimensionableDeRecordatorios() {
        recordatorios = new ArrayList<>();
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
        recordatorios = new ArrayList<>();
        for (Recordatorio recordar : vector.recordatorios){
            recordatorios.add(new Recordatorio(recordar.mensaje(),recordar.fecha(),recordar.horario()));
        }
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        return new ArregloRedimensionableDeRecordatorios(this);
    }
}
