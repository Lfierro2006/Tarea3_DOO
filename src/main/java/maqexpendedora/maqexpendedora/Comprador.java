package maqexpendedora;

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
     * Obtener el vuelto de la compra
     * @return Numero entero que representa el el valor del vuelto
     */
    public int cuantoVuelto(){
        return this.vuelto;
    }

    /**
     * Identificar que se consumio
     * @return String que representa lo que se consumio luego de interactuar con la maquina
     */
    public String queConsumiste(){
        return  this.sabor;
    }
}

