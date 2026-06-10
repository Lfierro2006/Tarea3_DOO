package LogicaGrafica;
import maqexpendedora.*;
import maqexpendedora.Comprador;
import maqexpendedora.Expendedor;


import javax.swing.*;
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
        ToolTipManager.sharedInstance().registerComponent(this);


        exp = new PanelExpendedor(30, 20, LogExp);
        com = new PanelComprador(510, 20, LogCom, LogExp);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                int clickX = e.getX();
                int clickY = e.getY();



                com.procesarClick(clickX, clickY);
                Expendedor.NomProduct productoTocado = exp.obtenerBotonClickeado(clickX, clickY);

                if (exp.tocoRanuraMonedas(clickX, clickY)) {
                    com.procesarIngresoMonedasDesdeMaquina();
                }

                if (productoTocado != null) {
                    com.procesarCompraDesdeMaquina(productoTocado);
                }


                if (exp.tocoRanuraVuelto(clickX, clickY)) {
                    com.procesarVueltoDesdeMaquina();
                }
                if (exp.tocoBandejaSalida(clickX, clickY)) {
                    com.procesarRecojoProductoDesdeMaquina();
                }

                // Interacción Cruzada: Recoger Vuelto
                if (exp.tocoRanuraVuelto(clickX, clickY)) {
                    com.procesarVueltoDesdeMaquina();
                }

                exp.actualizarVistas();

                com.actualizarAlmacenVisual();
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
    public String getToolTipText(MouseEvent event) {
        int mouseX = event.getX();
        int mouseY = event.getY();


        if (exp != null) {
            String textoExpendedor = exp.getToolTipText(event);
            if (textoExpendedor != null) return textoExpendedor;
        }


        if (com != null) {
            String textoComprador = com.getToolTipText(event);
            if (textoComprador != null) return textoComprador;
        }

        return null; // Si no toca nada, no mostramos ToolTip
    }
}
