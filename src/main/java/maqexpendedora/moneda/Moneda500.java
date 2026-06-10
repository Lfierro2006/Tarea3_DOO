package moneda;

/**
 * Clase que representa a una moneda con valor de 500
 * Hereda de la clase abstracta Moneda
 */
public class Moneda500 extends Moneda{
    /**
     * Metodo constructor de la clase Moneda500
     * Inicializa llamando al metodo constructor de la superclase
     */
    public Moneda500(){
        super();
    }

    /**
     * Consigue el valor de esta moneda especifica
     * @return Numero entero, con valor 500
     */
    @Override
    public int getValor() {
        return 500;
    }
}
