package producto;

/**
 * Clase que represneta al producto Sprite
 * hereda de Bebida
 */
public class Sprite extends Bebida {
    /**
     * Constructor de la clase Sprite
     * Se le da un numero de serie y se inicializa llamando al constructor de la superserie
     * @param serie Numero de seire, identificador para cada Sprite
     */
    public Sprite(int serie) {
        super(serie);
    }

    /**
     * Representa la accion de consumir el producto
     * @return String que simula que la acion fue ejecutada
     */
    @Override
    public String consumir() {
        return "sprite";
    }

}

