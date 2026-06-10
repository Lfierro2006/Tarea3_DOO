package moneda;

/**
 * Clase que representa una moneda con valor exacto de 1000
 * Hereda de la clase abstracta Moneda
 */
public class Moneda1000 extends Moneda{
    /**
     * Metodo constructor de la clase Moneda1000
     * Inicializa llamando al metodo constructor de la superclase
     */
    public Moneda1000(){
        super();
    }

    /**
     * Consigue el valor de esta moneda
     * @return Numero entero, con valor 1000
     */
    @Override
    public int getValor() {
        return 1000;
    }
}
