package excepciones;

/**
 * Excepcion que se lanza cuando el pago es menor
 * al precio del producto
 */

public class PagoInsuficienteException extends Exception {
    /**
     * constructor de la clase PagoInsuficienteException
     * Recibe un mensaje y lo pasa a la superclase
     * @param mensaje Mendaje que indica el error
     */
    public PagoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}