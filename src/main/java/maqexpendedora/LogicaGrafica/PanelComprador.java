package LogicaGrafica;
import javax.imageio.ImageIO;
import javax.swing.*;
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
    private int ancho = 600;
    private int alto = 850;
    private Image img100, img500, img1000, img1500;
    private DepositoMonedaVisual inventarioMonedasVisual;

    // Máquina de estados cíclica
    private int estadoActual = 1;
    private final int SELECCIONANDO_MONEDAS = 1;
    private final int GATILLAR_COMPRA = 2;
    private final int RECOGIENDO_PRODUCTO = 3;
    private final int RECOGIENDO_VUELTO = 4;


    public PanelComprador(int x, int y, Comprador comprador, Expendedor expendedor) {
        this.x=x;
        this.y=y;
        this.comprador = comprador;
        this.expendedor = expendedor;
        this.setBackground(new Color(220, 220, 220));
        cargarImagenes();
        ToolTipManager.sharedInstance().registerComponent(this);
        this.inventarioMonedasVisual = new DepositoMonedaVisual(this.x + 40, this.y + 220, comprador.getInventario(), img100, img500, img1000, img1500);
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
    private void moverMonedaAInventario(int valor) {
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

    public void procesarClick(int clickX, int clickY) {
        if (clickX >= this.x && clickX <= this.x + this.ancho &&
                clickY >= this.y && clickY <= this.y + this.alto) {

            int localX = clickX - this.x;
            int localY = clickY - this.y;

            switch (estadoActual) {
                case SELECCIONANDO_MONEDAS:
                    // El usuario selecciona qué monedas va a ingresar a la máquina
                    if (localY >= 80 && localY <= 130) {
                        if (localX >= 40 && localX <= 90) moverMonedaAInventario(100);
                        else if (localX >= 110 && localX <= 160) moverMonedaAInventario(500);
                        else if (localX >= 180 && localX <= 230) moverMonedaAInventario(1000);
                        else if (localX >= 250 && localX <= 300) moverMonedaAInventario(1500);
                    }
                    // Botón para confirmar las monedas ingresadas y pasar a la selección del producto
                    if (localX >= 40 && localX <= 220 && localY >= 350 && localY <= 390) {
                        if (!comprador.getInventario().getLista().isEmpty()) {
                            estadoActual = GATILLAR_COMPRA;
                        }
                    }
                    break;

                case GATILLAR_COMPRA:
                    // Presionar el botón para comprar el producto
                    if (localX >= 40 && localX <= 220 && localY >= 350 && localY <= 390) {
                        comprador.comprar(Expendedor.NomProduct.SNICKER, expendedor);
                        inventarioMonedasVisual.actualizarVistas();
                        estadoActual = RECOGIENDO_PRODUCTO; // Avanza en el ciclo
                    }
                    break;

                case RECOGIENDO_PRODUCTO:
                    // El usuario hace click en el botón para retirar su producto de la bandeja
                    if (localX >= 40 && localX <= 220 && localY >= 420 && localY <= 460) {
                        comprador.recogerProducto(expendedor);
                        estadoActual = RECOGIENDO_VUELTO; // Avanza en el ciclo
                    }
                    break;

                case RECOGIENDO_VUELTO:
                    // Recoger monedas del depósito de vuelto una a una hasta vaciarlo
                    if (localX >= 40 && localX <= 220 && localY >= 490 && localY <= 530) {
                        if (!expendedor.getMonVuelto().isEmpty()) {
                            Moneda vuelto = expendedor.getMonVuelto().getLista().get(0);
                            comprador.recogerVuelto(expendedor, vuelto);
                        }

                        // Si ya no queda vuelto pendiente por retirar, el ciclo se reinicia al Estado 1
                        if (expendedor.getMonVuelto().isEmpty()) {
                            estadoActual = SELECCIONANDO_MONEDAS;
                        }
                    }
                    break;
            }
        }
    }



    public void actualizarAlmacenVisual() {
        this.inventarioMonedasVisual.actualizarVistas();
    }
    @Override
    public String getToolTipText(MouseEvent event) {
        // Pasa las coordenadas directamente a la cascada de depósitos de monedas
        return inventarioMonedasVisual.obtenerToolTip(event.getX(), event.getY());
    }

    @Override
    public void paintComponent(Graphics g) {
        // Rectángulo base de contención visual para delimitar el área
        g.setColor(new Color(245, 245, 245));
        g.fillRect(x, y, ancho, alto);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, ancho, alto);


        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.setColor(Color.BLUE);
        String textoEstado = "";
        switch(estadoActual) {
            case SELECCIONANDO_MONEDAS: textoEstado = "Fase 1: Selecciona monedas y presiona CONFIRMAR PAGAR"; break;
            case GATILLAR_COMPRA:       textoEstado = "Fase 2: Presiona EFECTUAR COMPRA"; break;
            case RECOGIENDO_PRODUCTO:   textoEstado = "Fase 3: Presiona RECOGER PRODUCTO"; break;
            case RECOGIENDO_VUELTO:     textoEstado = "Fase 4: Presiona RECOGER VUELTO"; break;
        }
        g.drawString(textoEstado, x + 40, y + 40);

        // Renderizado del Monedero Infinito para suministrar dinero
        g.setColor(Color.BLACK);
        if (img100 != null) g.drawImage(img100, x + 40, y + 80, 50, 50, null);
        if (img500 != null) g.drawImage(img500, x + 110, y + 80, 50, 50, null);
        if (img1000 != null) g.drawImage(img1000, x + 180, y + 80, 50, 50, null);
        if (img1500 != null) g.drawImage(img1500, x + 250, y + 80, 50, 50, null);

        g.drawString("Monedas añadidas para el pago actual:", x + 40, y + 190);

        // Efecto cascada delegando la pintura del depósito visual
        inventarioMonedasVisual.paintComponent(g);

        // Configuración visual de los botones de control dinámicos según el estado del ciclo
        if (estadoActual == SELECCIONANDO_MONEDAS) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(x + 40, y + 350, 200, 40);
            g.setColor(Color.BLACK);
            g.drawRect(x + 40, y + 350, 200, 40);
            g.drawString("CONFIRMAR PAGAR", x + 70, y + 375);
        } else if (estadoActual == GATILLAR_COMPRA) {
            g.setColor(new Color(255, 140, 0)); // Naranja de acción
            g.fillRect(x + 40, y + 350, 200, 40);
            g.setColor(Color.BLACK);
            g.drawRect(x + 40, y + 350, 200, 40);
            g.drawString("EFECTUAR COMPRA", x + 65, y + 375);
        }





        g.drawString("Productos en Mochila: " + comprador.getInventarioProductos().getLista().size(), x + 40, y + 580);
    }
}