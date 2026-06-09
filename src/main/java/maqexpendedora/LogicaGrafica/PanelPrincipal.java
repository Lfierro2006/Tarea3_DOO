package LogicaGrafica;
import maqexpendedora.*;
import maqexpendedora.Comprador;
import maqexpendedora.Expendedor;


import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelPrincipal extends JPanel {
    private PanelComprador com;
    private PanelExpendedor exp;
    private Comprador LogCom;
    private Expendedor LogExp;

    public PanelPrincipal(){

        this.setLayout(null);
        this.setBackground(Color.lightGray);


        this.LogExp = new Expendedor();
        this.LogCom= new Comprador();



        exp = new PanelExpendedor(30, 20, LogExp);
        com = new PanelComprador(510, 20, LogCom, LogExp);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                int clickX = e.getX();
                int clickY = e.getY();


                exp.procesarClick(clickX, clickY);
                com.procesarClick(clickX, clickY);
                exp.getBandejaVisual().actualizarVista();
                com.actualizarAlmacenVisual();
                repaint();

                repaint();
            }
        });

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Pinta el fondo


        if (exp != null) exp.paintComponent(g);
        if (com != null) com.paintComponent(g);
    }
}
