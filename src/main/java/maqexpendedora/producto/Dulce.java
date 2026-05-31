package producto;

/**
 * Clase abstracta que representa el comportamiendo de los productos "Dulces"
 * Hereda de la clase Producto
 */
abstract class Dulce extends Producto {
    /**
     * Constructor de la clase Dulce
     * Se le asigna un numero llamando al constructor de la superclase Producto
     * @param serie Numero identificador de ese Dulce
     */

        public Dulce(int serie) {
            super(serie);
        }
    }


