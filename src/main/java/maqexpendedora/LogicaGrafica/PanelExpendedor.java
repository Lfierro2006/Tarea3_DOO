package LogicaGrafica;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import maqexpendedora.*;

public class PanelExpendedor extends JPanel {
    private Expendedor expendedor;
    private int ANCHO = 502;
    private int ALTO = 850;
    private int x,y;
    private Image imgMaquina;
    private Image imgCoca, imgFanta, imgSprite, imgSnicker, imgChokita, imgSuper8;

    private DepositoProductoVisual depCoca, depFanta, depSprite, depSnicker, depChokita, depSuper8;
    private DepositoEspecialVisual bandejaVisual;

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
        bandejaVisual = new DepositoEspecialVisual(390, 650, expendedor.getDepEspecial(), imgCoca, imgFanta, imgSprite, imgSnicker, imgChokita, imgSuper8);

    }
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
     * Revisa si el click cayó sobre la ranura para insertar monedas.
     */
    public boolean tocoRanuraMonedas(int clickX, int clickY) {
        if (clickX >= this.x && clickX <= this.x + this.ANCHO &&
                clickY >= this.y && clickY <= this.y + this.ALTO) {

            int localX = clickX - this.x;
            int localY = clickY - this.y;

            // EJEMPLO: Hitbox de la ranura de ingreso de monedas
            // Asumiendo que está arriba a la derecha del teclado numérico
            if (localX >= 410 && localX <= 450 && localY >= 160 && localY <= 200) {
                System.out.println("Ranura de Monedas");
                return true;
            }
        }
        return false;
    }

    public Expendedor.NomProduct obtenerBotonClickeado(int clickX, int clickY) { //Seleccion de productos en la barra lateral de la maquina
        if (clickX >= this.x && clickX <= this.x + this.ANCHO &&
                clickY >= this.y && clickY <= this.y + this.ALTO) {

            int localX = clickX - this.x;
            int localY = clickY - this.y;


            if (localX >= 360 && localX <= 455) {
                if (localY >= 230 && localY <= 265) return Expendedor.NomProduct.SNICKER;
                if (localY >= 275 && localY <= 315) return Expendedor.NomProduct.CHOKITA;
                if (localY >= 325 && localY <= 365) return Expendedor.NomProduct.SUPER8;
                if (localY >= 375 && localY <= 415) return Expendedor.NomProduct.COCACOLA;
                if (localY >= 425 && localY <= 465) return Expendedor.NomProduct.FANTA;
                if (localY >= 475 && localY <= 515) return Expendedor.NomProduct.SPRITE;

            }
            depCoca.actualizarVistas();
            depFanta.actualizarVistas();
            depSprite.actualizarVistas();
            depSnicker.actualizarVistas();
            depChokita.actualizarVistas();
            depSuper8.actualizarVistas();
        }
        return null; // No tocó ningún botón
    }

    public boolean tocoRanuraVuelto(int clickX, int clickY) {
        if (clickX >= this.x && clickX <= this.x + this.ANCHO &&
                clickY >= this.y && clickY <= this.y + this.ALTO) {

            int localX = clickX - this.x;
            int localY = clickY - this.y;


            if (localX >= 380 && localX <= 430 && localY >= 560 && localY <= 600) {
                System.out.println("Zona de botones: Retirar Vuelto.");
                return true;
            }
        }
        return false;
    }

    /**
     * Revisa si el click cayó sobre la bandeja de salida de productos.
     */
    public boolean tocoBandejaSalida(int clickX, int clickY) {
        if (clickX >= this.x && clickX <= this.x + this.ANCHO &&
                clickY >= this.y && clickY <= this.y + this.ALTO) {

            int localX = clickX - this.x;
            int localY = clickY - this.y;


            if (localX >= 360 && localX <= 455 && localY >= 615 && localY <= 760) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sincroniza todas las vistas de la máquina con el estado lógico actual.
     */
    public void actualizarVistas() {

        if (depCoca != null) depCoca.actualizarVistas();
        if (depFanta != null) depFanta.actualizarVistas();
        if (depSprite != null) depSprite.actualizarVistas();
        if (depSnicker != null) depSnicker.actualizarVistas();
        if (depChokita != null) depChokita.actualizarVistas();
        if (depSuper8 != null) depSuper8.actualizarVistas();

        if (bandejaVisual != null) bandejaVisual.actualizarVista();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Limpia el contenedor

        //Dibujar el mueble de la máquina usando el tamaño completo asignado al panel
        if (imgMaquina != null) {
            g.drawImage(imgMaquina, 3, 3, ANCHO, ALTO, null);
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


