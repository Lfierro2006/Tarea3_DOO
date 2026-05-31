package producto;

/**
 *Clase abstracta que simula el comportamiento base de los productos bebida.
 * Hereda de la clase producto.
 */

abstract class Bebida extends Producto {
    /**
     * Constructor de la clase Bebida.
     * Se inicializa la instancia llamando al constructor de su superclase y dandol un numero de serie.
     * @param serie Numero unico para esta Bebida.
     */
    public Bebida(int serie) {
        super(serie);
    }

}
