package LogicaGrafica;

import java.awt.Graphics;
import java.awt.Image;
import deposito.DepositoEspecial;
import producto.*;
/**
 * Representación visual de la bandeja de salida (depósito especial).
 * Se encarga de mostrar el único producto que el comprador acaba de adquirir.
 */
public class DepositoEspecialVisual {
    private int x;
    private int y;
    private DepositoEspecial depositoEspecial;


    private Image imgCoca, imgFanta, imgSprite, imgSnicker, imgChokita, imgSuper8;


    private ProductoVisual productoVisual;
    /**
     * Inicializa la vista de la bandeja de salida y carga las imágenes de todos los productos posibles.
     * @param x Coordenada X de la bandeja.
     * @param y Coordenada Y de la bandeja.
     * @param depositoEspecial Instancia lógica del depósito especial.
     * @param imgCoca Imagen de CocaCola.
     * @param imgFanta Imagen de Fanta.
     * @param imgSprite Imagen de Sprite.
     * @param imgSnicker Imagen de Snicker.
     * @param imgChokita Imagen de Chokita.
     * @param imgSuper8 Imagen de Super8.
     */
    public DepositoEspecialVisual(int x, int y, DepositoEspecial depositoEspecial, Image imgCoca, Image imgFanta, Image imgSprite, Image imgSnicker, Image imgChokita, Image imgSuper8) {
        this.x = x;
        this.y = y;
        this.depositoEspecial = depositoEspecial;
        this.imgCoca = imgCoca;
        this.imgFanta = imgFanta;
        this.imgSprite = imgSprite;
        this.imgSnicker = imgSnicker;
        this.imgChokita = imgChokita;
        this.imgSuper8 = imgSuper8;

        actualizarVista();
    }
    /**
     * Sincroniza la vista con la lógica del depósito especial.
     * Evalúa qué producto está en la bandeja y le asigna su imagen correspondiente,
     * o la vacía si no hay ningún producto.
     */
    public void actualizarVista() {
        Producto p = depositoEspecial.verObjeto();

        if (p == null) {
            productoVisual = null; // La bandeja está vacía
        } else {

            Image imgCorrespondiente = null;
            if (p instanceof CocaCola) imgCorrespondiente = imgCoca;
            else if (p instanceof Fanta) imgCorrespondiente = imgFanta;
            else if (p instanceof Sprite) imgCorrespondiente = imgSprite;
            else if (p instanceof Snicker) imgCorrespondiente = imgSnicker;
            else if (p instanceof Chokita) imgCorrespondiente = imgChokita;
            else if (p instanceof Super8) imgCorrespondiente = imgSuper8;

            productoVisual = new ProductoVisual(p, imgCorrespondiente);

            productoVisual.setXY(this.x, this.y);
        }
    }
    /**
     * Verifica si el ratón está sobre el producto en la bandeja para mostrar su serie.
     * @param mouseX Coordenada X del ratón.
     * @param mouseY Coordenada Y del ratón.
     * @return String con la serie del producto, o null si la bandeja está vacía o el ratón no la toca.
     */
    public String obtenerToolTip(int mouseX, int mouseY) {
        if (productoVisual != null && productoVisual.contains(mouseX, mouseY)) {
            return productoVisual.getSerieTexto();
        }
        return null;
    }
    /**
     * Dibuja el producto en la bandeja de salida si es que hay uno disponible.
     * @param g Objeto Graphics utilizado para pintar.
     */
    public void paintComponent(Graphics g) {
        if (productoVisual != null) {
            productoVisual.paintComponent(g);
        }
    }
}