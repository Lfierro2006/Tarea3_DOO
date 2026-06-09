package LogicaGrafica;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import maqexpendedora.*;
import moneda.Moneda;

public class PanelComprador extends JPanel {
    private Comprador comprador;
    private Expendedor expendedor;
    private int x, y;

    private Image img100, img500, img1000, img1500;
    private DepositoMonedaVisual inventarioMonedasVisual;

    public PanelComprador(Comprador comprador, Expendedor expendedor) {

        this.comprador = comprador;
        this.expendedor = expendedor;
        this.setBackground(new Color(220, 220, 220));
        cargarImagenes();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                procesarClick(e.getX(), e.getY());

                if (getParent() != null) {
                    getParent().repaint();
                }
            }
        });
    }

    private void cargarImagenes() {
        try {
            img100 = ImageIO.read(new File("src/main/java/Sprites/Moneda100.png"));
            img500 = ImageIO.read(new File("src/main/java/Sprites/Moneda500.png"));
            img1000 = ImageIO.read(new File("src/main/java/Sprites/Moneda1000.png"));
            img1500 = ImageIO.read(new File("src/main/java/Sprites/Moneda1500.png"));
        } catch (IOException e) {
            System.out.println("Error al cargar imágenes de monedas: " + e.getMessage());
        }

    }

    public void procesarClick(int clickX, int clickY) {
        if (clickX >= this.getX() && clickX <= this.getX() + this.getWidth() &&
                clickY >= this.getY() && clickY <= this.getY() + this.getHeight()) {

            int localX = clickX - this.getX();
            int localY = clickY - this.getY();

            // Zonas rectangulares de interacción (Botones del monedero infinito para sacar dinero)
            if (localY >= 80 && localY <= 130) {
                if (localX >= 40 && localX <= 90) moverMonedaAInvetario(100);
                else if (localX >= 110 && localX <= 160) moverMonedaAInvetario(500);
                else if (localX >= 180 && localX <= 230) moverMonedaAInvetario(1000);
                else if (localX >= 250 && localX <= 300) moverMonedaAInvetario(1500);
            }

            // Botón interactivo: Seleccionar Snicker y gatillar compra
            if (localX >= 40 && localX <= 220 && localY >= 350 && localY <= 390) {
                comprador.comprar(Expendedor.NomProduct.SNICKER, expendedor);
                // Al cambiar el estado de las monedas, reordenamos la vista
                inventarioMonedasVisual.actualizarVistas();
            }
//ME FALTAN BOTONES

            // Botón interactivo: Retirar de la bandeja
            if (localX >= 40 && localX <= 220 && localY >= 420 && localY <= 460) {
                comprador.recogerProducto(expendedor);
            }
        }
    }

    private void moverMonedaAInvetario(int valor) {
        ArrayList<Moneda> listaMonedero = comprador.getMonedero().getLista();
        for (int i = 0; i < listaMonedero.size(); i++) {
            if (listaMonedero.get(i).getValor() == valor) {
                Moneda m = listaMonedero.remove(i);
                comprador.getInventario().addObjeto(m);
                inventarioMonedasVisual.actualizarVistas(); // Sincroniza la cascada visual
                break;
            }
        }
    }


    @Override
    public String getToolTipText(MouseEvent event) {
        // Pasa las coordenadas directamente a la cascada de depósitos de monedas
        return inventarioMonedasVisual.obtenerToolTip(event.getX(), event.getY());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.BLACK);
        g.drawString("1. Selecciona tus Monedas (Monedero):", 30, 50);

        // Dibujar Monedas Disponibles
        if (img100 != null) g.drawImage(img100, 30, 80, 50, 50, null);
        if (img500 != null) g.drawImage(img500, 100, 80, 50, 50, null);
        if (img1000 != null) g.drawImage(img1000, 170, 80, 50, 50, null);
        if (img1500 != null) g.drawImage(img1500, 240, 80, 50, 50, null);

        // Dibujar Monedas en la Mano / Listas para Pagar
        g.drawString("2. Monedas dispuestas para el pago:", 30, 180);
        ArrayList<Moneda> enMano = comprador.getInventario().getLista();
        int posX = 30;
        int posY = 200;
        for (Moneda m : enMano) {
            Image imgM = null;
            if (m.getValor() == 100) imgM = img100;
            else if (m.getValor() == 500) imgM = img500;
            else if (m.getValor() == 1000) imgM = img1000;
            else if (m.getValor() == 1500) imgM = img1500;

            if (imgM != null) {
                g.drawImage(imgM, posX, posY, 35, 35, null);
                posX += 40;
                if (posX > 400) {
                    posX = 30;
                    posY += 40;
                }
            }
        }



        // Resumen de Estado en la Mochila
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Productos en Mochila: " + comprador.getInventarioProductos().getLista().size(), 30, 500);
    }
}