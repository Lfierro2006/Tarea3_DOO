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
     * Obtener el nombre del producto Chokita
     * @return String con el nombre "chokita"
     */
    @Override
    public String getNombre() {
        return "chokita";
    }
}