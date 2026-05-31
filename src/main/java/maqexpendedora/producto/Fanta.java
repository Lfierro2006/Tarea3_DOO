package producto;

/**
 * Representa una Fanta
 * Hereda de la clase Bebida
 * Crea su propia forma de consumir
 */
public class Fanta extends Bebida {
    /**
     * Constructor de la clase Fanta
     * Recibe un numero de serie unico y se llama al constructor de la superclase
     * @param serie Numero unico que identifica a la bebida "fanta"
     */
    public Fanta(int serie) {
        super(serie);
    }

    /**
     * Simula la accion de beber la fanta
     * @return String representando lo consumido
     */
    @Override
    public String consumir() {
        return "fanta";
    }
}

