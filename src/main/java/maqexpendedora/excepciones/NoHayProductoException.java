package excepciones;

/**
 * Error que se lanza cuando no hay del producto solicitado en stock
 */

public class NoHayProductoException extends Exception {
    /**
     * Constructor de la clase NoHayProductoException
     * Recibe un mensaje de error y lo pasa a la superclase
     * @param mensaje Mensaje de error indicando que no hay stock del producto solicitado
     */
    public NoHayProductoException(String mensaje) {
        super(mensaje);
    }
}