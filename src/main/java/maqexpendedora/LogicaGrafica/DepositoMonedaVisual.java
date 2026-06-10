package maqexpendedora.LogicaGrafica;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import maqexpendedora.deposito.Deposito;
import maqexpendedora.moneda.Moneda;
import maqexpendedora.*;
/**
 * Representación visual de un depósito de monedas.
 * Organiza y dibuja las monedas en la interfaz, formando filas estructuradas.
 */
public class DepositoMonedaVisual {
    private int x, y;
    private Deposito<Moneda> depositoLogico;

    // Referencias a las imágenes cargadas en el panel principal
    private Image img100, img500, img1000, img1500;
    private ArrayList<MonedaVisual> vistasMonedas;
    /**
     * Inicializa la vista del depósito y configura las imágenes base para las monedas.
     * @param x Coordenada X inicial del depósito.
     * @param y Coordenada Y inicial del depósito.
     * @param depositoLogico Instancia lógica del depósito de monedas.
     * @param img100 Imagen para la moneda de 100.
     * @param img500 Imagen para la moneda de 500.
     * @param img1000 Imagen para la moneda de 1000.
     * @param img1500 Imagen para la moneda de 1500.
     */
    public DepositoMonedaVisual(int x, int y, Deposito<Moneda> depositoLogico,
                                 Image img100, Image img500, Image img1000, Image img1500) {
        this.x = x;
        this.y = y;
        this.depositoLogico = depositoLogico;
        this.img100 = img100;
        this.img500 = img500;
        this.img1000 = img1000;
        this.img1500 = img1500;
        this.vistasMonedas = new ArrayList<>();
        actualizarVistas();
    }
    /**
     * Sincroniza las vistas gráficas con el inventario lógico.
     * Organiza las monedas en pantalla, creando una nueva fila cada 6 elementos.
     */
    public void actualizarVistas() {
        vistasMonedas.clear();
        ArrayList<Moneda> listaLogica = depositoLogico.getLista();

        int posX = this.x;
        int posY = this.y;
        int cuenta = 0;

        for (Moneda m : listaLogica) {
            Image imgCorrespondiente = null;
            switch (m.getValor()) {
                case 100:  imgCorrespondiente = img100; break;
                case 500:  imgCorrespondiente = img500; break;
                case 1000: imgCorrespondiente = img1000; break;
                case 1500: imgCorrespondiente = img1500; break;
            }

            MonedaVisual mv = new MonedaVisual(m, imgCorrespondiente);
            mv.setXY(posX, posY);
            vistasMonedas.add(mv);

            posX += 50; // Espaciado horizontal
            cuenta++;
            if (cuenta % 6 == 0) { // Si hay más de 6 monedas, salta de fila
                posX = this.x;
                posY += 50;
            }
        }
    }
    /**
     * Verifica si el ratón está sobre alguna de las monedas para mostrar su información.
     * @param mouseX Coordenada X del ratón.
     * @param mouseY Coordenada Y del ratón.
     * @return String con la información de la moneda, o null si el ratón no toca nada.
     */
    public String obtenerToolTip(int mouseX, int mouseY) {
        for (MonedaVisual mv : vistasMonedas) {
            if (mv.contains(mouseX, mouseY)) {
                return mv.getSerieTexto();
            }
        }
        return null;
    }
    /**
     * Dibuja todas las representaciones visuales de las monedas almacenadas.
     * @param g Objeto Graphics utilizado para pintar.
     */
    public void paintComponent(Graphics g) {
        for (MonedaVisual mv : vistasMonedas) {
            mv.paintComponent(g);
        }
    }
}