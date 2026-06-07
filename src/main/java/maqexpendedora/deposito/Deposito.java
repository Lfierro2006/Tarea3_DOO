package deposito;

import java.util.ArrayList;

/**
 * Clase que representa a un deposito generico en el que se puede
 * almacenar objetos, usa un ArrayList para almacenar los elementos
 * @param <T> El tipo de elemento que se almacenara en el deposito
 */

public class Deposito<T>{
    private ArrayList<T> lista;

    /**
     * Constructor de la calse Deposito
     * Inicializa el ArrayList vacio
     */
    public Deposito(){
        this.lista= new ArrayList<T>();
    }

    /**
     * Agrega el objeto
     * @param objeto Elemento de tipo T que se almacenara en el deposito
     */
    public void addObjeto(T objeto){ //añade el objeto y se comprueba primero que sea del mismo tipo T
        lista.add(objeto);
        }

    /**
     * Extraer objeto de la lista comprobando si no esta vacia
     * @return Objeto de tipo T extraido, o null si esta vacio el deposito
     */
    public T getObjeto() {
        if (lista.isEmpty()) {
            return null;
        }else{
            return  lista.remove(0);
        }
    }

/* Deposito de monedas para unirlo al deposito normal
class DepositoM {
    private ArrayList<Moneda> mlist;

    public DepositoM() {
        this.mlist = new ArrayList<Moneda>();
    }

    public void addMoneda(Moneda m) {
        mlist.add(m);
    }

}