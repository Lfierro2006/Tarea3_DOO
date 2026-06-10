package maqexpendedora.LogicaGrafica;

import java.awt.Graphics;
import java.awt.Image;
import maqexpendedora.deposito.DepositoEspecial;
import maqexpendedora.producto.*;

public class DepositoEspecialVisual {
    private int x;
    private int y;
    private DepositoEspecial depositoEspecial;


    private Image imgCoca, imgFanta, imgSprite, imgSnicker, imgChokita, imgSuper8;


    private ProductoVisual productoVisual;

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
    public String obtenerToolTip(int mouseX, int mouseY) {
        if (productoVisual != null && productoVisual.contains(mouseX, mouseY)) {
            return productoVisual.getSerieTexto();
        }
        return null;
    }

    public void paintComponent(Graphics g) {
        if (productoVisual != null) {
            productoVisual.paintComponent(g);
        }
    }
}