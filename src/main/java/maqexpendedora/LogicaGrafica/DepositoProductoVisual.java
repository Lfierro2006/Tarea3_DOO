package LogicaGrafica;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import deposito.*;
import producto.*;
import maqexpendedora.*;
public class DepositoProductoVisual {
    private int x;
    private int y;
    private Deposito<Producto> depositoLogico;
    private Image imagenProducto;

    private ArrayList<ProductoVisual> vistasProductos;//lISTA DE LAS REPRESENTACIONES GRAFICAS

    public DepositoProductoVisual(int x, int y, Deposito<Producto> depositoLogico, Image imagenProducto){
        this.x = x;
        this.y = y;
        this.depositoLogico = depositoLogico;
        this.imagenProducto = imagenProducto;
        this.vistasProductos = new ArrayList<>();

        actualizarVistas();
    }
    /**
     * Recibe las coordenadas del ratón y revisa si están sobre algún ProductoVisual.
     * @return El texto del ToolTip, o null si el ratón no toca nada.
     */
    public String obtenerToolTip(int mouseX, int mouseY) {
        for (ProductoVisual vista : vistasProductos) {
            if (vista.contains(mouseX, mouseY)) {
                return vista.getSerieTexto();
            }
        }
        return null;
    }
    public void actualizarVistas() {
        vistasProductos.clear(); // Limpiamos la lista visual actual
        ArrayList<Producto> listaLogica = depositoLogico.getLista(); // Obtenemos el stock real

        // La posición inicial relativa al depósito
        int posX = this.x;
        int posY = this.y;

        for (int i = 0; i < listaLogica.size(); i++) {
            Producto p = listaLogica.get(i);
            ProductoVisual vista = new ProductoVisual(p, imagenProducto);

            // Reposicionamos usando setXY según su posición en el ArrayList (horizontalmente)
            vista.setXY(posX, posY);
            vistasProductos.add(vista);

            // Separación de 77 pixeles entre cada producto
            posX += 77;
        }
    }

    public void paintComponent(Graphics g) {
        for (ProductoVisual vista : vistasProductos) {
            vista.paintComponent(g); // Cada producto se dibuja a sí mismo
        }
    }
}
