package deposito;

import java.util.ArrayList;

/**
 * Clase que representa a un deposito generico en el que se puede
 * almacenar objetos, usa un ArrayList para almacenar los elementos
 * @param <T> El tipo de elemento que se almacenara en el deposito
 */

public class Deposito<T>{ // clase generica
    private ArrayList<T> lista; //se define para que solo maneje del tipo T

    /**
     * Constructor de la calse Deposito
     * Inicializa el ArrayList vacio
     */
    public Deposito(){
        this.lista= new ArrayList<T>();
    } //cuando se hace el arraylist le pasamos el tipo T

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
    public T getObjeto() { // extrae el objeto si hay disponible o regresa null si no
        if (lista.isEmpty()) {
            return null;
        }else{
            return  lista.remove(0);
        }
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

    public Moneda getMoneda() {
            if (mlist.size() == 0) {
                return null;
            }
            else{
            return mlist.remove(0);
            }
    }
}
 */