package excepciones;
/**
 * Error que se lanza al pagar de manera incorrecta el producto
 * Cuando se intenta pagar con NULL
 */

public class PagoIncorrectoException extends Exception {
    /**
     * Constructor de la clase PagoIncorrectoExeption
     * Recibe un mensaje de error y lo manda a la superclase
     * @param mensaje Mensaje de error indicando el pago incorrecto
     */
    public PagoIncorrectoException(String mensaje) {
        super(mensaje);
    }
}