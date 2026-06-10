package maqexpendedora.producto;

/**
 * Representa una Fanta
 * Hereda de la clase Bebida
 * Crea su propia forma de getNombre
 */
public class Fanta extends Bebida {
    /**
     * Constructor de la clase Fanta
     * Recibe un numero de serie unico y se llama al constructor de la superclase
     * @param serie Numero unico que identifica a la bebida "fanta"
     */
    public Fanta(int serie) {
        super(serie);
    }

    /**
     * Obtener el nombre del producto Fanta
     * @return String con el nombre "fanta"
     */
    @Override
    public String getNombre() {
        return "fanta";
    }
}

