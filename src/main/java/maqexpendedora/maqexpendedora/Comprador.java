package maqexpendedora;

import deposito.Deposito;
import excepciones.*;
import moneda.*;
import producto.*;

/**
 * Representa al comprador que interactua con la maquina expendedora.
 * Acumula monedas en su inventario y compra productos mediante la GUI.
 */

public class Comprador{
    private final Deposito<Moneda> inventario;
    private final Deposito<Producto> inventarioProductos;
    private final Deposito<Moneda> monedero;

    /**
     * Constructor de la clase Comprador.
     * Inicializa el inventario de monedas, el monedero con 20 monedas de cada tipo e inventario
     * de productos dejandolos vacíos
     */

    public Comprador() {
        this.inventario=new Deposito<Moneda>();
        this.inventarioProductos=new Deposito<Producto>();
        this.monedero=new Deposito<Moneda>();
        for(int i = 0; i < 20; i++){
            monedero.addObjeto(new Moneda100());
            monedero.addObjeto(new Moneda500());
            monedero.addObjeto(new Moneda1000());
            monedero.addObjeto(new Moneda1500());
        }
    }
    /**
     * Mueve una moneda del monedero al inventario del comprador
     * @param m Moneda a agregar.
     */

    public void agregarMoneda(Moneda m){
        monedero.remObjeto(m);
        inventario.addObjeto(m);
    }

    /**
     * Mueve una moneda del inventario del comprador al monedero
     * @param m Moneda a eliminar
     */

    public void eliminarMoneda(Moneda m){
        inventario.remObjeto(m);
        monedero.addObjeto(m);
    }


    /**
     * Intenta comprar un producto en el expendedor con las monedas del inventario de monedas.
     * Si falla, recupera el vuelto al inventario.
     * @param product El producto que se desea comprar.
     * @param exp La maquina expendedora.
     */

    public void comprar(Expendedor.NomProduct product, Expendedor exp){
        try {
            exp.comprarProducto(inventario,product);
        }catch (NoHayProductoException | PagoInsuficienteException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Recoge el producto del deposito especial del expendedor
     * y lo guarda en el inventario de productos del comprador.
     * @param exp La maquina expendedora de la cual se recoge el producto.
     */
    public void recogerProducto(Expendedor exp) {
        Producto p = exp.getProducto();
        if (p != null) {
            inventarioProductos.addObjeto(p);
        }
    }

    /**
     * Recoge un tipo de moneda especifica del deposito de vuelto del expendedor
     * y la agrega al inventario del comprador
     * @param exp La maquina expendedora
     * @param monedaVuelto tipo de Moneda especifica a recoger
     */

    public void recogerVuelto(Expendedor exp,Moneda monedaVuelto) {
        exp.remVuelto(monedaVuelto);
        inventario.addObjeto(monedaVuelto);
    }
    public Deposito<Moneda> getInventario() {
        return inventario;
    }

    public Deposito<Producto> getInventarioProductos() {
        return inventarioProductos;
    }

    public Deposito<Moneda> getMonedero() {
        return monedero;
    }
}