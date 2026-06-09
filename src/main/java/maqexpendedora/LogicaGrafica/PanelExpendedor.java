package LogicaGrafica;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import maqexpendedora.*;
import producto.*;

public class PanelExpendedor extends JPanel {
    private Expendedor expendedor;
    private int ancho = 748;
    private int largo = 1198;

    private Image imgMaquina;
    private Image imgCoca, imgFanta, imgSprite, imgSnicker, imgChokita, imgSuper8;

    private DepositoVisual depCoca, depFanta, depSprite, depSnicker, depChokita, depSuper8;

    public PanelExpendedor(Expendedor expendedor){
        this.expendedor = expendedor;
        this.setBackground(Color.DARK_GRAY); // Fondo por si falta una imagen
        cargarImagenes();

        depCoca= new DepositoVisual(40, 80, expendedor.getDepCoca(), imgCoca);
        depFanta= new DepositoVisual(40, 160, expendedor.getDepFanta(), imgFanta);
        depSprite= new DepositoVisual(40, 240, expendedor.getDepSprite(), imgSprite);
        depSnicker = new DepositoVisual(40, 320, expendedor.getDepSnicker(), imgSnicker);
        depChokita= new DepositoVisual(40, 400, expendedor.getDepChokita(), imgChokita);
        depSuper8 = new DepositoVisual(40, 480, expendedor.getDepSuper8(), imgSuper8);






    }
    private void cargarImagenes() {
        try {

            imgMaquina = ImageIO.read(new File("src/main/java/maqexpendedora/Sprites/Maquina.png"));
            imgCoca = ImageIO.read(new File("src/main/java/maqexpendedora/Sprites/CocaCola.png"));
            imgFanta = ImageIO.read(new File("src/main/java/maqexpendedora/Sprites/Fanta.png"));
            imgSprite = ImageIO.read(new File("src/main/java/maqexpendedora/Sprites/Sprite.png"));
            imgSnicker = ImageIO.read(new File("src/main/java/maqexpendedora/Sprites/Snicker.png"));
            imgChokita = ImageIO.read(new File("src/main/java/maqexpendedora/Sprites/Chokita.png"));
            imgSuper8 = ImageIO.read(new File("src/main/java/maqexpendedora/Sprites/Super8.png"));

        } catch (IOException e) {
            System.out.println("Error al cargar una o más imágenes: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Limpia el contenedor

        // 1. Dibujar el mueble de la máquina usando el tamaño completo asignado al panel
        if (imgMaquina != null) {
            g.drawImage(imgMaquina, 0, 0, this.getWidth(), this.getHeight(), null);
        }

        // 2. Ejemplo de renderizado del stock actual (Vitrina)
        // Puedes iterar sobre los depósitos lógicos para ver cuántos quedan y dibujarlos
        if (!expendedor.getDepCoca().isEmpty() && imgCoca != null) {
            // Dibujamos una CocaCola representativa en el estante correspondiente
            g.drawImage(imgCoca, 40, 80, 35, 60, null);
        }

        if (!expendedor.getDepChokita().isEmpty() && imgChokita != null) {
            g.drawImage(imgChokita, 40, 160, 30, 60, null);
        }

        // 3. Dibujar el producto en el Depósito Especial (Bandeja de entrega)
        Producto p = expendedor.getDepEspecial().verObjeto();
        if (p != null) {
            Image imgProductoEntregado = null;
            if (p instanceof CocaCola) imgProductoEntregado = imgCoca;
            else if (p instanceof Chokita) imgProductoEntregado = imgChokita;
            else if (p instanceof Fanta) imgProductoEntregado = imgFanta;
            else if (p instanceof Sprite) imgProductoEntregado = imgSprite;
            else if (p instanceof Snicker) imgProductoEntregado = imgSnicker;
            else if (p instanceof Super8) imgProductoEntregado = imgSuper8;

            if (imgProductoEntregado != null) {
                // Posicionar abajo en el receptáculo de salida
                g.drawImage(imgProductoEntregado, 120, 520, 40, 60, null);
            }
        }
    }
}
