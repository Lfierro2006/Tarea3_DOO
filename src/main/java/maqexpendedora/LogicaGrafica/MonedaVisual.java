package LogicaGrafica;

import moneda.Moneda;
import maqexpendedora.*;

import java.awt.*;

public class MonedaVisual {
    private int x, y;
    private Moneda monedaVisual;
    private Image imagen;

    public MonedaVisual(Moneda monedaVisual, Image imagen){
        this.monedaVisual=monedaVisual;
        this.imagen = imagen;

    }
    public void setXY(int x, int y){
        this.x=x;
        this.y=y;

    }
    public boolean contains(int mouseX, int mouseY) {
        return mouseX >= this.x && mouseX <= this.x + 64 &&
                mouseY >= this.y && mouseY <= this.y + 64;
    }
    public Moneda getMonedaVisual(){return monedaVisual;}
    public String getSerieTexto() {
        return "Valor: $" + monedaVisual.getValor() + " | Serie: " + monedaVisual.getSerie();
    }
    public void paintComponent(Graphics g){
        if (imagen != null){
            g.drawImage(imagen, x, y, 64, 64, null);
        }
    }
}
