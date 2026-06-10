package maqexpendedora.LogicaGrafica;
import java.awt.Graphics;
import java.awt.Image;
import maqexpendedora.producto.Producto;
import maqexpendedora.*;
/**
 * Representación gráfica de un producto en la interfaz.
 */
public class ProductoVisual {
    private int x, y;
    private Producto productoLogico;
    private Image imagen;
    private int ancho = 64;
    private int alto = 64;
    /**
     * Inicializa la vista del producto con su lógica e imagen.
     * @param productoLogico Producto asociado a esta vista.
     * @param imagen Imagen que se mostrará en pantalla.
     */
    public ProductoVisual(Producto productoLogico, Image imagen){
        this.productoLogico=productoLogico;
        this.imagen = imagen;
/**
 * Verifica si las coordenadas del ratón están sobre este producto.
 * @param mouseX Coordenada X del ratón.
 * @param mouseY Coordenada Y del ratón.
 * @return true si el ratón está encima, false en caso contrario.
 */
    }
    public boolean contains(int mouseX, int mouseY) {
        return mouseX >= this.x && mouseX <= this.x + ancho && mouseY >= this.y && mouseY <= this.y + alto;
    }

    /**
     * Obtiene el número de serie formateado para el ToolTip.
     */
    public String getSerieTexto() {

        return "Serie: " + productoLogico.getSerie();
    }
    /**
     * Establece la posición en pantalla donde se dibujará.
     * @param x Coordenada horizontal.
     * @param y Coordenada vertical.
     */
    public void setXY(int x, int y){
        this.x=x;
        this.y=y;

    }
    /**
     * Devuelve el producto lógico asociado a esta vista.
     * @return El objeto Producto de la lógica.
     */
    public Producto getProductoLogico(){return productoLogico;}

    public void paintComponent(Graphics g){
        if (imagen != null){
            g.drawImage(imagen, x, y, 96, 96, null);
        }
    }
}
