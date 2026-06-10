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
    private int ANCHO = 650;
    private int ALTO = 800;
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

    /**
     * Confirma el ingreso de las monedas al hacer click en la ranura de la máquina.
     */
    public void procesarIngresoMonedasDesdeMaquina() {
        if (this.estadoActual == SELECCIONANDO_MONEDAS) {
            // Solo avanzamos si el usuario realmente preparó alguna moneda en su mano
            if (!comprador.getInventario().getLista().isEmpty()) {
                this.estadoActual = GATILLAR_COMPRA;
            }
        }
    }
    /**
     * Ejecuta la compra usando el botón físico tocado en la máquina, respetando el estado actual.
     */
    public void procesarCompraDesdeMaquina(Expendedor.NomProduct producto) {
        if (this.estadoActual == GATILLAR_COMPRA) {
            // El usuario tocó un botón válido de la máquina durante la fase de compra
            comprador.comprar(producto, expendedor);
            inventarioMonedasVisual.actualizarVistas(); // El dinero entra a la máquina
            this.estadoActual = RECOGIENDO_PRODUCTO; // Avanzamos de fase
        }
    }
    /**
     * Recoge el producto haciendo click directamente en la bandeja de la máquina.
     */
    public void procesarRecojoProductoDesdeMaquina() {
        if (this.estadoActual == RECOGIENDO_PRODUCTO) {
            comprador.recogerProducto(expendedor);
            this.estadoActual = RECOGIENDO_VUELTO; // Avanza en el ciclo
        }
    }

    /**
     * Recoge el vuelto haciendo click en la ranura de la máquina.
     */
    public void procesarVueltoDesdeMaquina() {
        if (this.estadoActual == RECOGIENDO_VUELTO) {
            while (!expendedor.getMonVuelto().isEmpty()) {
                Moneda vuelto = expendedor.getMonVuelto().getLista().get(0);
                comprador.recogerVuelto(expendedor, vuelto);
            }



            this.estadoActual = SELECCIONANDO_MONEDAS;

        }
    }

    public void procesarClick(int clickX, int clickY) {
        if (clickX >= this.x && clickX <= this.x + this.ANCHO &&
                clickY >= this.y && clickY <= this.y + this.ALTO) {

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


                    break;

                case GATILLAR_COMPRA:


                    break;

                case RECOGIENDO_PRODUCTO:

                    break;

                case RECOGIENDO_VUELTO:

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
        g.setColor(new Color(255, 240, 240));
        g.fillRect(x, y, ANCHO, ALTO);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, ANCHO, ALTO);


        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.setColor(Color.BLUE);
        String textoEstado = "";
        switch(estadoActual) {
            case SELECCIONANDO_MONEDAS: textoEstado = "Selecciona monedas y presiona la ranura para Monedas al lado de la maquina"; break;
            case GATILLAR_COMPRA:       textoEstado = "Presiona el producto en el lateral de la maquina, estan ordenados por altura"; break;
            case RECOGIENDO_PRODUCTO:   textoEstado = "Recoja su Producto"; break;
            case RECOGIENDO_VUELTO:     textoEstado = "Recoja su Vuelto arriba de donde recogio su producto"; break;
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






        g.drawString("Productos en Mochila: " + comprador.getInventarioProductos().getLista().size(), x + 40, y + 580);

}}