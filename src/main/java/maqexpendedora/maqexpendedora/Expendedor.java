package maqexpendedora.maqexpendedora;

import maqexpendedora.deposito.Deposito;
import maqexpendedora.deposito.DepositoEspecial;
import maqexpendedora.moneda.*;
import maqexpendedora.producto.*;
import maqexpendedora.excepciones.*;

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
    private final Deposito<Moneda> monRecibidas;
    private final Deposito<Moneda> monTemp;
    private final DepositoEspecial depEspecial;

    /**
     * Enumeracion que representa los productos y el costo de cada uno
     */
    public enum NomProduct {
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
        NomProduct(int precio) {
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
     * inicializa cada deposito con 15 elementos de cada producto
     */
    public Expendedor(){
        this.coca= new Deposito<Producto>();
        this.fanta= new Deposito<Producto>();
        this.sprite= new Deposito<Producto>();
        this.snicker= new Deposito<Producto>();
        this.chokita= new Deposito<Producto>();
        this.super8= new Deposito<Producto>();
        this.monVuelto= new Deposito<Moneda>();
        this.monTemp= new Deposito<Moneda>();
        this.monRecibidas= new Deposito<Moneda>();
        this.depEspecial=DepositoEspecial.getInstancia();
        for (int i=0; i<4;i++){
            snicker.addObjeto(new Snicker(100+i));
            chokita.addObjeto(new Chokita(200+i));
            super8.addObjeto(new Super8(300+i));
            coca.addObjeto(new CocaCola(400+i));
            fanta.addObjeto(new Fanta(500+i));
            sprite.addObjeto(new Sprite(600+i));
        }
    }

    /** Ejecuta la compra del producto con las monedas acumuladas.
     * Verifica stock y pago suficiente.
     * @param pagoMonedas Deposito con las monedas de pago.
     * @param product El producto que se desea comprar.
     * @throws PagoInsuficienteException Si el total de monedas es menor al precio.
     * @throws NoHayProductoException Si no queda stock del producto solicitado.
     */

    public void comprarProducto(Deposito<Moneda> pagoMonedas, NomProduct product)
            throws PagoInsuficienteException, NoHayProductoException{
        int pagoTotal=calcularTotal(pagoMonedas);
        Producto p = null;
        //Comprar el producto si es posible
        if(pagoTotal>= product.getPrecio()){
            switch (product){
                case SNICKER: p=snicker.getObjeto();break;
                case CHOKITA:p=chokita.getObjeto();break;
                case SUPER8:p=super8.getObjeto();break;
                case COCACOLA:p=coca.getObjeto();break;
                case FANTA:p=fanta.getObjeto();break;
                case SPRITE:p=sprite.getObjeto();break;
            }
            //Calcular vuelto a devolver
            if (p !=null){
                // pasar de temp a recibidas
                Moneda mt = monTemp.getObjeto();
                while (mt != null) {
                    monRecibidas.addObjeto(mt);
                    mt = monTemp.getObjeto();
                }
                depEspecial.addObjeto(p);
                int vuelto = pagoTotal - product.precio;
                int[]valores={1500,1000,500,100};
                for(int valor:valores){
                    while(vuelto>=valor){
                        monVuelto.addObjeto(crearMoneda(valor));
                        vuelto=vuelto-valor;
                    }
                }
            }
            //En caso de no haber stock se devuelve el dinero
            else {
                devolverMonedas(pagoTotal);
                // limpiar temp
                Moneda mt = monTemp.getObjeto();
                while (mt != null){
                    mt = monTemp.getObjeto();
                }
                throw new NoHayProductoException("Error: No queda stock de " + product.name());
            }
        }
        // en caso de que la plata sea insuficiente se devuelve el dinero
        else{
            devolverMonedas(pagoTotal);
            // limpiar temp
            Moneda mt = monTemp.getObjeto();
            while (mt != null){
                mt = monTemp.getObjeto();
            }
            throw new PagoInsuficienteException("Error: Dinero insuficiente para comprar " + product.name());
        }
    }

    /**
     * Extrae el producto comprado del deposito especial
     * @return Producto comprado, null si no hay
     */
    public Producto getProducto(){
        return depEspecial.getObjeto();
    }

    /**
     * Calcula el total de monedas recibidas y las guarda en monRecibidas
     * Se usa exclusivamente para calcular el pago recibido
     * @param monedas Deposito de monedas a calcular
     * @return Total acumulado de las monedas
     */
    private int calcularTotal(Deposito<Moneda> monedas) {
        int total = 0;
        Moneda m = monedas.getObjeto();
        while (m != null) {
            total += m.getValor();
            monTemp.addObjeto(m); // temporal
            m = monedas.getObjeto();
        }
        return total;
    }

    /**
     * Elimina una moneda del tipo especifico del deposito (una de 100,500,1000 o 1500) de vuelto.
     * @param m Moneda a eliminar.
     */
    public void remVuelto(Moneda m) {
        monVuelto.remObjeto(m);
    }

    /**
     * Rellena los depositos de productos que esten vacios con 4 unidades.
     */
    public void rellenarDepositos(){
        if(snicker.isEmpty()) for(int i=0; i<4; i++) snicker.addObjeto(new Snicker(100+i));
        if(chokita.isEmpty()) for(int i=0; i<4; i++) chokita.addObjeto(new Chokita(200+i));
        if(super8.isEmpty())  for(int i=0; i<4; i++) super8.addObjeto(new Super8(300+i));
        if(coca.isEmpty())    for(int i=0; i<4; i++) coca.addObjeto(new CocaCola(400+i));
        if(fanta.isEmpty())   for(int i=0; i<4; i++) fanta.addObjeto(new Fanta(500+i));
        if(sprite.isEmpty())  for(int i=0; i<4; i++) sprite.addObjeto(new Sprite(600+i));
    }

    /**
     * Crea una moneda del valor especificado, se utiliza de manera local en comprarProducto
     * @param valor Valor de la moneda a crear.
     * @return Moneda del valor indicado.
     */
    private Moneda crearMoneda(int valor) {
        switch (valor) {
            case 1500: return new Moneda1500();
            case 1000: return new Moneda1000();
            case 500:  return new Moneda500();
            default:   return new Moneda100();
        }
    }
    /**
     * Devuelve el monto indicado en monedas al deposito de vuelto.
     * Se usa cuando la compra no es exitosa.
     * @param monto Monto a devolver.
     */
    private void devolverMonedas(int monto) {
        int[]valores={1500,1000,500,100};
        for(int valor:valores){
            while(monto>=valor){
                monVuelto.addObjeto(crearMoneda(valor));
                monto=monto-valor;
            }
        }
    }
    public Deposito<Producto> getDepCoca() { return coca; }
    public Deposito<Producto> getDepFanta() { return fanta; }
    public Deposito<Producto> getDepSprite() { return sprite; }
    public Deposito<Producto> getDepSnicker() { return snicker; }
    public Deposito<Producto> getDepChokita() { return chokita; }
    public Deposito<Producto> getDepSuper8() { return super8; }
    public Deposito<Moneda> getMonVuelto() { return monVuelto; }
    public DepositoEspecial getDepEspecial() { return depEspecial; }
}