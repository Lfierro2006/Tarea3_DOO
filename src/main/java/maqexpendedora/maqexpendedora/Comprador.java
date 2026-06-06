package maqexpendedora;

import deposito.Deposito;
import excepciones.*;
import moneda.*;
import producto.*;

/**
 * Representa al comprador que puede interactuar con la maquina expendedora
 * intentara comprar un producto especifico con la moneda dada
 * si compro exitosamente, toma ell vuelto y lo dice
 */
public class Comprador{
    private final Deposito<Moneda> inventario;
    private final Deposito<Moneda> vuelto;
    private final Deposito<Producto> inventarioProductos;

    public Comprador() {
        this.inventario=new Deposito<Moneda>();
        this.vuelto=new Deposito<Moneda>();
        this.inventarioProductos=new Deposito<Producto>();
    }
    /**
     * Agrega una moneda al inventario del comprador.
     * @param m Moneda a agregar.
     */
    public void agregarMoneda(Moneda m){
        inventario.addObjeto(m);
    }
    public void comprar(Expendedor.NomProduct product, Expendedor exp){
        try {
            exp.comprarProducto(inventario,product);
        }catch (NoHayProductoException | PagoInsuficienteException e){
            System.out.println(e.getMessage());
        }
        Moneda m= exp.getVuelto();
        while(m!=null){
            vuelto.addObjeto(m);
            m= exp.getVuelto();
        }
    }

}