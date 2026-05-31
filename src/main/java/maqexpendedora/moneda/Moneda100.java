package moneda;

/**
 * Clase que representa una moneda con valor de 100
 * Hereda de la clase abstracta Moneda
 */
public class Moneda100 extends Moneda{
    /**
     * Metodo constructor de la clase Moneda100
     * Inicializa llamando al constructor de la superclase
     */
    public Moneda100(){
        super();
    }

    /**
     * Obtiene el valor de esta moneda
     * @return Numero entero con valor de 100
     */
    @Override
    public int getValor() {
        return 100;
    }
}
