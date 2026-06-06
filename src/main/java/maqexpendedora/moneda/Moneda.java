package moneda;

/**
 * Clase abstracta que represnta una moneda de forma generica
 * sirve de molde para monedas de diferentes valores
 */
public abstract class Moneda implements Comparable<Moneda>{
    /**
     * Metodo constructor de la clase Moneda
     */

    public Moneda(){
    }

    /**
     * Consguir la referencia de la instancia actual
     * @return Instancia de la moneda actual
     */
    public Moneda getSerie() {
        return this;
    }

    /**
     * Metodo abstracto, que obtiene el valor de la moneda
     * Cada subclase debe implementarlo con su valor correspondiente
     * @return Numero del valor de la moneda
     */
    public abstract int getValor();

    /**
     * Compara esta moneda con otra por valor.
     * @param otraMoneda Moneda a comparar.
     * @return Negativo, cero o positivo según el valor.
     */
    @Override
    public int compareTo(Moneda otraMoneda){
        return Integer.compare(this.getValor(), otraMoneda.getValor());
    }

    /**
     * Representación en texto de la moneda.
     * @return String con valor y número de serie.
     */
    @Override
    public String toString(){
        return "Valor: "+this.getValor()+" | Serie: "+this.getSerie();
    }
}
