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
    private final String sabor;
    private int vuelto;

    /**
     * Constructor de la clase Comprador
     * El comprador intenta comprar en la maquina, si lo consigue, guarda el "sonido" del producto y el vuelto
     * de la compra (en monedas de 100, sumandolas)
     * @param m La moneda que fue dada al comprador
     * @param product El tipo de producto que se quiere comprar
     * @param exp La maquina expendedora de la cual se esta comprando
     */
    public Comprador(Moneda m, Expendedor.nomProduct product, Expendedor exp) {
        this.vuelto = 0;
        String saborTemp = null; // guardamos el sabor en una variable temporal
        try { //intentamos la compra
            Producto p = exp.comprarProducto(m, product);
            saborTemp = p.consumir(); // Se consume en caso de exito
        }
        // atrapar el error
        catch (PagoIncorrectoException | PagoInsuficienteException | NoHayProductoException e) {
            saborTemp = null; // no se consiguio producto
            System.out.println(e.getMessage()); // se imprime el error
        }

        this.sabor = saborTemp; // guardamos el sabor de la var temp en la instancia

        Moneda monedavuelto = exp.getVuelto();
        while (monedavuelto != null) {
            this.vuelto = this.vuelto + monedavuelto.getValor();
            monedavuelto = exp.getVuelto();
        }
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

