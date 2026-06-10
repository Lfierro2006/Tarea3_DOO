package producto;

/**
 * Representa una Bebida tipo CocaCola
 * Hereda de Bebida y crea su propio "getNombre"
 */

public class CocaCola extends Bebida {
    /**
     * Constructor la de clase CocaCola
     * Asgigna un numero a cada instancia de CocaCola llamando al constructor de la superclase Bebida
     * @param serie Identificador de esta unidad de CocaCola
     */
    public CocaCola(int serie) {
        super(serie);
    }

    /**
     * Obtener el nombre del producto CocaCola.
     * @return String con el nombre "cocacola".
     */
    @Override
    public String getNombre() {
        return "cocacola";
    }
}
