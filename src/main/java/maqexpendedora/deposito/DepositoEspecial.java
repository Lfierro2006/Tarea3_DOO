package deposito;

import producto.Producto;
/**
 * Depósito de capacidad única para un solo Producto.
 * Implementa el patrón Singleton.
 */
public class DepositoEspecial {
    private static DepositoEspecial instancia;
    private Producto objeto;

    private DepositoEspecial() {}

    /**
     * Retorna la única instancia de DepositoEspecial.
     * @return instancia única.
     */
    public static DepositoEspecial getInstancia() {
        if(instancia==null) {
            instancia=new DepositoEspecial();
        }
        return instancia;
    }

    /**
     * Almacena un producto en el depósito.
     * @param objeto Producto a almacenar.
     * @throws IllegalStateException si el depósito ya tiene un producto.
     */
    public void addObjeto(Producto objeto) {
        if (this.objeto != null) {
            throw new IllegalStateException("El depósito ya tiene un producto");
        }
        this.objeto = objeto;
    }

    /**
     * Extrae y retorna el producto almacenado.
     * @return Producto extraído, null si está vacío.
     */
    public Producto getObjeto() {
        Producto temp = this.objeto;
        this.objeto = null;
        return temp;
    }

    /**
     * Indica si el depósito está vacío.
     * @return true si no hay producto.
     */
    public boolean isEmpty() {
        return this.objeto == null;
    }
}