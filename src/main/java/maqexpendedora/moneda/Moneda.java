package moneda;

/**
 * Clase abstracta que represnta una moneda de forma generica
 * sirve de molde para monedas de diferentes valores
 */
public abstract class Moneda{
    /**
     * Metodo constructor de la clase Moneda
     */

    public Moneda(){
    }

    /**
     * Consguir la referencia de la instancia actual
     * @return Instancia de la moneda actual
     */
    public Moneda getSerie() {
        return this;
    }

    /**
     * Metodo abstracto, que obtiene el valor de la moneda
     * Cada subclase debe implementarlo con su valor correspondiente
     * @return Numero del valor de la moneda
     */
    public abstract int getValor();
}
