package producto;

/**
 * clase que representa el producto Snicker
 * hereda de la clase Dulce
 */
public class Snicker extends Dulce {
    /**
     * Constructor de la clase Snicker
     * Se le da un numero de serie y se llama al constructor de la superserie
     * @param serie Numero que identifica a cada Snicker
     */
    public Snicker(int serie) {
        super(serie);
    }

    /**
     * Representa el acto de consumir el producto
     * @return String que representa que el producto fue consumido
     */

    @Override
    public String consumir() {
        return "snicker";
    }
}