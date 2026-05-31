package maqexpendedora;

import deposito.Deposito;
import moneda.*;
import producto.*;
import excepciones.*;

/**
 * Clase que representa la maquina expendedora con la que interactua el comprador
 * Almacena productos, verifica las comprar y el vuelto
 */
public class Expendedor{
    private final Deposito<Producto> coca;
    private final Deposito<Producto> sprite;
    private final Deposito<Producto> fanta;
    private final Deposito<Producto> snicker;
    private final Deposito<Producto> chokita;
    private final Deposito<Producto> super8;
    private final Deposito<Moneda> monVuelto;

    /**
     * Enumeracion que representa los productos y el costo de cada uno
     */
    public enum nomProduct{
        SNICKER(500),
        CHOKITA(400),
        SUPER8(500),
        COCACOLA(1300),
        FANTA(1000),
        SPRITE(800);
        private final int precio;

        /**
         * constructor del enumProduct
         * @param precio numero entero, valor especifico del producto
         */
        nomProduct(int precio) {
        this.precio=precio;
        }

        /**
         * obtener el precio del producto
         * @return Numero entero, valor del precio del producto
         */
        public int getPrecio(){
            return precio;
        }
    }

    /**
     * Constructor de la clase Expendedor
     * inicializa cada deposito y los llena con la cantidad indicada
     * @param numProductos La cantidad de stock inicial que tendra cada uno de los productos
     */
    public Expendedor(int numProductos){
        this.coca= new Deposito<Producto>();
        this.fanta= new Deposito<Producto>();
        this.sprite= new Deposito<Producto>();
        this.snicker= new Deposito<Producto>();
        this.chokita= new Deposito<Producto>();
        this.super8= new Deposito<Producto>();
        this.monVuelto= new Deposito<Moneda>();
        for (int i=0; i<numProductos;i++){
            snicker.addObjeto(new Snicker(100+i));
            chokita.addObjeto(new Chokita(200+i));
            super8.addObjeto(new Super8(300+i));
            coca.addObjeto(new CocaCola(400+i));
            fanta.addObjeto(new Fanta(500+i));
            sprite.addObjeto(new Sprite(600+i));
        }
    }

    /**
     * Ejecuta la compra del producto, con la moneda y el producto especificado
     * verifica que sea valido, que haya stock, el pago sea suficiente y que el producto solicitado exista
     * si la compra es exitosa, se da el vuelto
     * @param a La moneda que se ingresa a la maquina para pagar
     * @param product El producto que se desea comprar
     * @return el producto extraido del expendedor
     * @throws PagoIncorrectoException   Si la moneda ingresada es null.
     * @throws PagoInsuficienteException Si el valor de la moneda es menor al precio del producto.
     * @throws NoHayProductoException    Si no queda stock del producto solicitado en el depósito.
     */

    public Producto comprarProducto(Moneda a,nomProduct product)
            throws PagoIncorrectoException, PagoInsuficienteException, NoHayProductoException{
        if(a==null){
            throw new PagoIncorrectoException("Error: Moneda nula o no ingresada.");
        }
        Producto p = null;

        if(a.getValor() >= product.getPrecio()){
            switch (product){
                case SNICKER: p=snicker.getObjeto();break;
                case CHOKITA:p=chokita.getObjeto();break;
                case SUPER8:p=super8.getObjeto();break;
                case COCACOLA:p=coca.getObjeto();break;
                case FANTA:p=fanta.getObjeto();break;
                case SPRITE:p=sprite.getObjeto();break;
            }
            if (p !=null){ //calcular vuelto a devolver
                int vuelto = a.getValor() - product.precio;
                int cantMonedas100 = vuelto/100;//cantidad de monedas

                for (int i = 0; i < cantMonedas100; i++) {//se genera la cantidad de monedas y se meten al deposito
                    monVuelto.addObjeto(new Moneda100()); // se crea para el vuelto
                }
                return p;
            }
            else { //en caso de tipo de bebida pedido inexistente o fuera de stock se devuelve la moneda

                monVuelto.addObjeto(a);
                throw new NoHayProductoException("Error: No queda stock de " + product.name());
            }
        }
        else{ // en caso de que no alcanza la plata tambien se devuelve la moneda
            monVuelto.addObjeto(a);
            throw new PagoInsuficienteException("Error: Dinero insuficiente para comprar " + product.name());
        }
    }

    /**
     * saca moneda del deposito del vuelto
     * @return moneda de 100 correspondiente al vuelto
     */
    public Moneda getVuelto(){
        return monVuelto.getObjeto();
    }
}