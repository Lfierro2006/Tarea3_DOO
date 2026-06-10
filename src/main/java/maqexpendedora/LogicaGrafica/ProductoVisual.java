package LogicaGrafica;
import java.awt.Graphics;
import java.awt.Image;
import producto.Producto;

public class ProductoVisual {
    private int x, y;
    private Producto productoLogico;
    private Image imagen;
    private int ANCHO = 64;
    private int ALTO = 64;

    public ProductoVisual(Producto productoLogico, Image imagen){
        this.productoLogico=productoLogico;
        this.imagen = imagen;

    }
    public boolean contains(int mouseX, int mouseY) {
        return mouseX >= this.x && mouseX <= this.x + ANCHO && mouseY >= this.y && mouseY <= this.y + ALTO;
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
