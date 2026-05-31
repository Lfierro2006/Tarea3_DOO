package producto;

/**
 * Representa una Bebida tipo CocaCola
 * Hereda de Bebida y crea su propio "consumir"
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
     * Simula el beber el producto CocaCola
     * @return Un String que reprenta que CocaCola es consumido
     */
    @Override
    public String consumir() {
        return "cocacola";
    }
}
