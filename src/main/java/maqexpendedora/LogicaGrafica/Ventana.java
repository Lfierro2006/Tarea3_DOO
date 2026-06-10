package LogicaGrafica;
import javax.swing.JFrame;
/**
 * Ventana principal de la interfaz gráfica.
 * Contiene y muestra la simulación de la máquina expendedora.
 */
public class Ventana extends JFrame {
    /**
     * Configura el tamaño, título y añade el panel principal a la ventana.
     */
        public Ventana() {
            this.setTitle("Maquina Expendedora");
            this.setSize(1200
                    ,900);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);

            PanelPrincipal panel = new PanelPrincipal();
            this.add(panel);
        }

    /**
     * Punto de entrada del programa.
     * @param args Argumentos de consola.
     */

    public static void main(String[] args) {
        Ventana v= new Ventana();
        v.setVisible(true);
    }
}
