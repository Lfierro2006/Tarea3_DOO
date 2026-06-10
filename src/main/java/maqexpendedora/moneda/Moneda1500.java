package moneda;

/**
 * Clase que representa una moneda de 1500
 * Hereda de la clase abstracta Moneda
 */
public class Moneda1500 extends Moneda{
    /**
     * Metodo constructor de la clase Moneda1500
     * Inicializa llamando al metodo constructor de la superclase
     */
    public Moneda1500(){
        super();
    }

    /**
     * Obtener el valor de esta moneda
     * @return Numero entero, con valor 1500
     */
    @Override
    public int getValor() {
        return 1500;
    }
}
