package deposito;

import producto.Producto;

public class DepositoEspecial {
    private static DepositoEspecial instancia;
    private Producto objeto;

    private DepositoEspecial() {}

    public static DepositoEspecial getInstancia() {
        if(instancia==null) {
            instancia=new DepositoEspecial();
        }
        return instancia;
    }

    public void addObjeto(Producto objeto) {
        if (this.objeto != null) {
            throw new IllegalStateException("El depósito ya tiene un producto");
        }
        this.objeto = objeto;
    }

    public Producto getObjeto() {
        Producto temp = this.objeto;
        this.objeto = null;
        return temp;
    }

    public boolean isEmpty() {
        return this.objeto == null;
    }
}