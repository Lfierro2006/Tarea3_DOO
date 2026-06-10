package LogicaGrafica;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import deposito.Deposito;
import producto.*;
/**
 * Administrador visual encargado de representar gráficamente el inventario
 * de productos comprados (la mochila) del comprador.
 * Sincroniza el estado lógico del depósito con una cuadrícula visual de sprites.
 */
public class MochilaVisual {
    private int x, y;
    private Deposito<Producto> depositoLogico;


    private Image imgCoca, imgFanta, imgSprite, imgSnicker, imgChokita, imgSuper8;
    private ArrayList<ProductoVisual> vistasProductos;
    /**
     * Constructor de la clase MochilaVisual.
     * Inicializa la posición de la mochila, asigna el depósito lógico a observar
     * y carga las imágenes correspondientes para cada tipo de producto.
     * @param x Coordenada X global donde inicia el área de la mochila.
     * @param y Coordenada Y global donde inicia el área de la mochila.
     * @param depositoLogico El depósito lógico de productos del comprador que se va a representar.
     * @param imgCoca Imagen correspondiente al producto CocaCola.
     * @param imgFanta Imagen correspondiente al producto Fanta.
     * @param imgSprite Imagen correspondiente al producto Sprite.
     * @param imgSnicker Imagen correspondiente al producto Snicker.
     * @param imgChokita Imagen correspondiente al producto Chokita.
     * @param imgSuper8 Imagen correspondiente al producto Super8.
     */
    public MochilaVisual(int x, int y, Deposito<Producto> depositoLogico, Image imgCoca, Image imgFanta, Image imgSprite, Image imgSnicker, Image imgChokita, Image imgSuper8) {
        this.x = x;
        this.y = y;
        this.depositoLogico = depositoLogico;
        this.imgCoca = imgCoca;
        this.imgFanta = imgFanta;
        this.imgSprite = imgSprite;
        this.imgSnicker = imgSnicker;
        this.imgChokita = imgChokita;
        this.imgSuper8 = imgSuper8;
        this.vistasProductos = new ArrayList<>();
        actualizarVistas();
    }
    /**
     * Sincroniza las vistas gráficas lo que hay en la "mochila" tras la compra,
     * posicionando los productos horizontalmente con una separación constante.
     */
    public void actualizarVistas() {
        vistasProductos.clear();
        ArrayList<Producto> listaLogica = depositoLogico.getLista();

        int posX = this.x;
        int posY = this.y;
        int cuenta = 0;

        for (Producto p : listaLogica) {
            Image imgCorrespondiente = null;
            if (p instanceof CocaCola) imgCorrespondiente = imgCoca;
            else if (p instanceof Fanta) imgCorrespondiente = imgFanta;
            else if (p instanceof Sprite) imgCorrespondiente = imgSprite;
            else if (p instanceof Snicker) imgCorrespondiente = imgSnicker;
            else if (p instanceof Chokita) imgCorrespondiente = imgChokita;
            else if (p instanceof Super8) imgCorrespondiente = imgSuper8;

            ProductoVisual pv = new ProductoVisual(p, imgCorrespondiente);
            //  dentro de la cuadrícula de la mochila
            pv.setXY(posX, posY);
            vistasProductos.add(pv);

            posX += 35; // Espaciado horizontal entre latas/barras
            cuenta++;
            if (cuenta % 10 == 0) { // Si hay más de 10 productos en la mochila, salta de fila
                posX = this.x;
                posY += 70; // Salto vertical (un poco más que el alto del producto)
            }
        }
    }
     /** Recibe las coordenadas del ratón y revisa si están sobre algún ProductoVisual.
      * @return El texto del ToolTip, o null si el ratón no toca nada.
      */
    public String obtenerToolTip(int mouseX, int mouseY) {
        for (ProductoVisual pv : vistasProductos) {
            if (pv.contains(mouseX, mouseY)) {
                return pv.getSerieTexto();
            }
        }
        return null;
    }
    /**
     * Dibuja todos los productos visuales que contiene este depósito.
     * @param g Objeto Graphics utilizado para pintar.
     */
    public void paintComponent(Graphics g) {
        for (ProductoVisual pv : vistasProductos) {
            pv.paintComponent(g);
        }
    }
}