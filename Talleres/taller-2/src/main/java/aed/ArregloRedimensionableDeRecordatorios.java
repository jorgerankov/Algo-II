package aed;

class ArregloRedimensionableDeRecordatorios {
    int longitud;
    Recordatorio agregarAtras;
    Recordatorio obtener;

    public ArregloRedimensionableDeRecordatorios() {
        Recordatorio arregloRecordatorio[] = new Recordatorio[10];
    }

    public int longitud() {
        return this.longitud;
    }

    public void agregarAtras(Recordatorio i) {
        // HACER
    }

    public Recordatorio obtener(int i) {
        return this.obtener;
    }

    public void quitarAtras() {
        // Implementar
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        // Implementar
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        // Implementar
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        // Implementar
        return null;
    }
}
