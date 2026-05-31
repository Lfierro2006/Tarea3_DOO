package producto;

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
     * Representa el acto de consumir el Super8
     * @return String que confirma la accion de consumir()
     */

    @Override
    public String consumir() {
        return "super8";
    }
}