package LogicaGrafica;
import javax.swing.JFrame;
public class Ventana extends JFrame {
        public Ventana() {
            this.setTitle("Maquina Expendedora");
            this.setSize(1100
                    ,900);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);

            PanelPrincipal panel = new PanelPrincipal();
            this.add(panel);
        }



    public static void main(String[] args) {
        Ventana v= new Ventana();
        v.setVisible(true);
    }
}
