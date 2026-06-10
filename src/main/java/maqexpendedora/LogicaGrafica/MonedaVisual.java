package LogicaGrafica;

import moneda.Moneda;
import maqexpendedora.*;

import java.awt.*;
/**
 * Representación visual de una moneda en la interfaz gráfica.
 * Se encarga de dibujar la imagen y gestionar sus coordenadas.
 */
public class MonedaVisual {
    private int x, y;
    private Moneda monedaVisual;
    private Image imagen;
    /**
     * Inicializa la vista de la moneda con su objeto lógico y su imagen.
     * @param monedaVisual Instancia lógica de la moneda.
     * @param imagen Imagen asociada a esta moneda.
     */
    public MonedaVisual(Moneda monedaVisual, Image imagen){
        this.monedaVisual=monedaVisual;
        this.imagen = imagen;

    }
    /**
     * Establece las coordenadas en pantalla donde se dibujará la moneda.
     * @param x Coordenada horizontal.
     * @param y Coordenada vertical.
     */
    public void setXY(int x, int y){
        this.x=x;
        this.y=y;

    }
    /**
     * Verifica si las coordenadas del ratón están sobre la imagen de esta moneda.
     * @param mouseX Coordenada X del ratón.
     * @param mouseY Coordenada Y del ratón.
     * @return true si el ratón está encima, false en caso contrario.
     */
    public boolean contains(int mouseX, int mouseY) {
        return mouseX >= this.x && mouseX <= this.x + 64 &&
                mouseY >= this.y && mouseY <= this.y + 64;
    }
    /**
     * Devuelve el objeto lógico de la moneda asociada.
     * @return Instancia de Moneda.
     */
    public Moneda getMonedaVisual(){return monedaVisual;}
    /**
     * Obtiene el texto formateado con el valor y serie para mostrar en el ToolTip.
     * @return String con la información de la moneda.
     */

    public String getSerieTexto() {
        return "Valor: $" + monedaVisual.getValor() + " | Serie: " + monedaVisual.getSerie();
    }
    /**
     * Dibuja la imagen de la moneda en las coordenadas establecidas (tamaño 64x64).
     * @param g Objeto Graphics utilizado para pintar.
     */

    public void paintComponent(Graphics g){
        if (imagen != null){
            g.drawImage(imagen, x, y, 64, 64, null);
        }
    }}

