package maqexpendedora.LogicaGrafica;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import maqexpendedora.*;
import maqexpendedora.maqexpendedora.Expendedor;
/**
 * Panel gráfico que representa visualmente la máquina expendedora.
 * Contiene las imágenes y vistas de los depósitos de productos.
 */
public class PanelExpendedor extends JPanel {
    private Expendedor expendedor;
    private int ancho = 502;
    private int alto = 850;
    private int x,y;
    private Image imgMaquina;
    private Image imgCoca, imgFanta, imgSprite, imgSnicker, imgChokita, imgSuper8;

    private DepositoProductoVisual depCoca, depFanta, depSprite, depSnicker, depChokita, depSuper8;
    private DepositoEspecialVisual bandejaVisual;
    /**
     * Inicializa la vista del expendedor, carga sus imágenes y crea las vistas de los depósitos.
     * @param x Coordenada X del panel.
     * @param y Coordenada Y del panel.
     * @param expendedor Instancia lógica del expendedor.
     */
    public PanelExpendedor(int x, int y, Expendedor expendedor){
        this.x=x;
        this.y=y;
        this.expendedor = expendedor;
        this.setBackground(Color.DARK_GRAY); // Fondo por si falta una imagen
        cargarImagenes();

        depCoca= new DepositoProductoVisual(36, 358, expendedor.getDepCoca(), imgCoca);
        depFanta= new DepositoProductoVisual(36, 459, expendedor.getDepFanta(), imgFanta);
        depSprite= new DepositoProductoVisual(36, 563, expendedor.getDepSprite(), imgSprite);
        depSnicker = new DepositoProductoVisual(36, 48, expendedor.getDepSnicker(), imgSnicker);
        depChokita= new DepositoProductoVisual(36, 148, expendedor.getDepChokita(), imgChokita);
        depSuper8 = new DepositoProductoVisual(36, 254, expendedor.getDepSuper8(), imgSuper8);
        ToolTipManager.sharedInstance().registerComponent(this);
        bandejaVisual = new DepositoEspecialVisual(120, 520, expendedor.getDepEspecial(), imgCoca, imgFanta, imgSprite, imgSnicker, imgChokita, imgSuper8);

    }
    /**
     * Carga las imágenes de la máquina y los productos desde el sistema de archivos.
     */
    private void cargarImagenes() {
        try {

            imgMaquina = ImageIO.read(new File("src/main/java/Sprites/Maquina.png"));
            imgCoca = ImageIO.read(new File("src/main/java/Sprites/CocaCola.png"));
            imgFanta = ImageIO.read(new File("src/main/java/Sprites/Fanta.png"));
            imgSprite = ImageIO.read(new File("src/main/java/Sprites/Sprite.png"));
            imgSnicker = ImageIO.read(new File("src/main/java/Sprites/Snicker.png"));
            imgChokita = ImageIO.read(new File("src/main/java/Sprites/Chokita.png"));
            imgSuper8 = ImageIO.read(new File("src/main/java/Sprites/Super8.png"));

        } catch (IOException e) {
            System.out.println("Error al cargar una o más imágenes: " + e.getMessage());
        }
    }
    /**
     * Evalúa las interacciones del usuario según las coordenadas del clic.
     * Gestiona botones, bandeja de salida y el rellenado de depósitos.
     * @param clickX Coordenada X del clic.
     * @param clickY Coordenada Y del clic.
     */
    public void procesarClick(int clickX, int clickY) {
        // Validar si el click ocurrió dentro de los límites de este PanelExpendedor
        if (clickX >= this.x && clickX <= this.x + this.ancho && clickY >= this.y && clickY <= this.y + this.alto) {

            int localX = clickX - this.x;
            int localY = clickY - this.y;
            // Variable para controlar si el usuario interactuó con un botón específico
            boolean seHizoInteraccion = false;

            //Hitbox del botón para comprar Snicker
            if (localX >= 380 && localX <= 440 && localY >= 100 && localY <= 140) {
                System.out.println("Zona de botones: Snicker detectado.");
                seHizoInteraccion = true;
            }

            //Zona del depósito especial (bandeja de salida)
            if (localX >= 50 && localX <= 350 && localY >= 500 && localY <= 600) {
                System.out.println("Zona de bandeja de salida detectada.");
                seHizoInteraccion = true;
            }

            //ME FALTA AÑADIR MAS HITBOX

            //Si el click fue dentro del expendedor pero NO tocó
            // ningún botón o bandeja, se deben rellenar los depósitos vacíos.
            if (!seHizoInteraccion) {
                System.out.println("Click en espacio vacío del expendedor: Rellenando depósitos...");
                expendedor.rellenarDepositos();

                // Al rellenar los ArrayList lógicos, debe refrescar las repisas visuales
                depCoca.actualizarVistas();
                depFanta.actualizarVistas();
                depSprite.actualizarVistas();
                depSnicker.actualizarVistas();
                depChokita.actualizarVistas();
                depSuper8.actualizarVistas();
            }
        }
    }

    /**
     * Devuelve la vista correspondiente a la bandeja de salida.
     * @return Objeto DepositoEspecialVisual.
     */
    public DepositoEspecialVisual getBandejaVisual() {
        return this.bandejaVisual;
    }
    @Override
    /**
     * Dibuja el fondo de la máquina y delega el dibujo a cada depósito visual.
     * @param g Objeto Graphics usado para dibujar.
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Limpia el contenedor

        //Dibujar el mueble de la máquina usando el tamaño completo asignado al panel
        if (imgMaquina != null) {
            g.drawImage(imgMaquina, 3, 3, ancho, alto, null);
        }
        // Llamado en cascada a las vistas de depósitos y productos
        depCoca.paintComponent(g);
        depFanta.paintComponent(g);
        depSprite.paintComponent(g);
        depSnicker.paintComponent(g);
        depChokita.paintComponent(g);
        depSuper8.paintComponent(g);
        bandejaVisual.paintComponent(g);
    }
    /**
     * Muestra la información del producto si el ratón está sobre él.
     * @param event Evento del ratón.
     * @return Texto con la serie del producto, o null si no hay nada.
     */
    @Override
    public String getToolTipText(MouseEvent event) {
        int mouseX = event.getX();
        int mouseY = event.getY();

        String texto;


        texto = depCoca.obtenerToolTip(mouseX, mouseY);
        if (texto != null) return texto; //

        texto = depFanta.obtenerToolTip(mouseX, mouseY);
        if (texto != null) return texto;

        texto = depSprite.obtenerToolTip(mouseX, mouseY);
        if (texto != null) return texto;

        // 2. Revisamos el estante de Snickers
        texto = depSnicker.obtenerToolTip(mouseX, mouseY);
        if (texto != null) return texto;

        texto = depChokita.obtenerToolTip(mouseX, mouseY);
        if (texto != null) return texto;

        texto = depSuper8.obtenerToolTip(mouseX, mouseY);
        if (texto != null) return texto;

        texto = bandejaVisual.obtenerToolTip(mouseX, mouseY);
        if (texto != null) return texto;


        return null;
    }
}


