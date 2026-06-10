package LogicaGrafica;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import deposito.Deposito;
import producto.*;

public class MochilaVisual {
    private int x, y;
    private Deposito<Producto> depositoLogico;


    private Image imgCoca, imgFanta, imgSprite, imgSnicker, imgChokita, imgSuper8;
    private ArrayList<ProductoVisual> vistasProductos;

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

    public String obtenerToolTip(int mouseX, int mouseY) {
        for (ProductoVisual pv : vistasProductos) {
            if (pv.contains(mouseX, mouseY)) {
                return pv.getSerieTexto();
            }
        }
        return null;
    }

    public void paintComponent(Graphics g) {
        for (ProductoVisual pv : vistasProductos) {
            pv.paintComponent(g);
        }
    }
}