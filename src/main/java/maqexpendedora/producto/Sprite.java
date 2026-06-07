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
     * Obtener el nombre del producto Sprite
     * @return String con el nombre "Sprite"
     */
    @Override
    public String getNombre() {
        return "sprite";
    }

}

