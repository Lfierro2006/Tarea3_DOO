package maqexpendedora.LogicaGrafica;
import java.awt.Graphics;
import java.awt.Image;
import maqexpendedora.producto.Producto;
import maqexpendedora.*;
public class ProductoVisual {
    private int x, y;
    private Producto productoLogico;
    private Image imagen;
    private int ancho = 64;
    private int alto = 64;

    public ProductoVisual(Producto productoLogico, Image imagen){
        this.productoLogico=productoLogico;
        this.imagen = imagen;

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
    public void setXY(int x, int y){
        this.x=x;
        this.y=y;

    }

    public Producto getProductoLogico(){return productoLogico;}

    public void paintComponent(Graphics g){
        if (imagen != null){
            g.drawImage(imagen, x, y, 96, 96, null);
        }
    }
}
