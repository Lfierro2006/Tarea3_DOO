package maqexpendedora.producto;

/**
 * Clase que representa al producto Super8
 * Hereda de Dulce
 */
public class Super8 extends Dulce {
    /**
     * Metodo Constructor de la clase Super8
     * Se le da un numero de serie y se inicializa llamando al constructor de la superclase
     * @param serie Numero que identifica a cada Super8
     */
    public Super8(int serie) {
        super(serie);
    }

    /**
     * Obtener el nombre del producto Super8
     * @return String con el nombre "super8"
     */
    @Override
    public String getNombre() {
        return "super8";
    }
}