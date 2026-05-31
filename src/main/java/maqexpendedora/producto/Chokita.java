package producto;
/**
 * Representa una Chokita.
 * Hereda de la clase abstracta Dulce y define su propio texto al ser consumido.
 */
public class Chokita extends Dulce {
    /**
     * constructor de la clase chokita
     * asigna un numero de serie único de la instancia llamando al constructor de la superclase
     * @param serie El identificador de la instancia del dulde chokita
    */
    public Chokita(int serie) {
        super(serie);
    }

    /**
     * Simula la acciónde comerse una Chokita.
     * @return Un string que representa lo consumido.
     */

    @Override
    public String consumir() {
        return "chokita";
    }
}