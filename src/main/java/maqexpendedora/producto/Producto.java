package producto;

/**
 * Clase que representa a todos los productos que pueden ser
 * almacenados en la maquina expendedora
 * clase base de los productos (Bebida y Dulce)
 */

public abstract class Producto {
    private int serie;

    /**
     * Constructor de la clase Producto
     * Otorga un numero de serie al producto al momento de ser istanciado
     * @param serie Numero que identifica a cada producto
     */

    public Producto (int serie){
        this.serie = serie;
    }

    /**
     * Obtener el numero identificador de este producto
     *
     * @return Numero de serie unico, representa al producto
     */

    public int getSerie() {
        return serie;
    }

    /**
     * Metodo abstracto que representa que el producto fue consumido
     * Ya que es abstracta cada subclase tiene implementar este metodo
     * y retornar el String de cada producto especifico
     * @return String que
     */

    public abstract String consumir();
}
