import maqexpendedora.*;
import moneda.*;
import excepciones.*;

/**
 * Clase principal que prueba todas las funcionalidades de la máquina expendedora.
 * Incluye casos normales de compra y pruebas de excepciones.
 */
public class Main {

    /**
     * Metodo principal que ejecuta las pruebas de la máquina expendedora.
     * @param args Argumentos de línea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
        // Crear expendedora con stock inicial de 2 productos por tipo
        Expendedor exp = new Expendedor(2);

        System.out.println("---------- CASOS NORMALES ----------");

        // Compra exitosa de CocaCola con Moneda1500
        Comprador c1 = new Comprador(new Moneda1500(), Expendedor.nomProduct.COCACOLA, exp);
        System.out.println("Compró: " + c1.queConsumiste() + " | Valor: "+ Expendedor.nomProduct.COCACOLA.getPrecio()+ " | Moneda:1500 | Vuelto: " + c1.cuantoVuelto());

        // Compra exitosa de Snicker con Moneda1000
        Comprador c2 = new Comprador(new Moneda1000(), Expendedor.nomProduct.SNICKER, exp);
        System.out.println("Compró: " + c2.queConsumiste() +" | Valor: "+Expendedor.nomProduct.SNICKER.getPrecio()+ " | Moneda:1000 | Vuelto: " + c2.cuantoVuelto());

        // Compra exitosa de Fanta con Moneda1000 (precio exacto)
        Comprador c3 = new Comprador(new Moneda1000(), Expendedor.nomProduct.FANTA, exp);
        System.out.println("Compró: " + c3.queConsumiste() +" | Valor: "+Expendedor.nomProduct.FANTA.getPrecio()+" | Moneda:1000 | Vuelto: " + c3.cuantoVuelto());

        // Compra exitosa de Sprite con Moneda1500
        Comprador c10 = new Comprador(new Moneda1500(), Expendedor.nomProduct.SPRITE, exp);
        System.out.println("Compró: " + c10.queConsumiste() +" | Valor: "+Expendedor.nomProduct.SPRITE.getPrecio()+" | Moneda:1500 | Vuelto: " + c10.cuantoVuelto());

        System.out.println("\n---------- PRUEBAS DE EXCEPCIONES ----------");

        // 1. Pago incorrecto (moneda nula)
        System.out.println("\n--- Caso 1: Moneda nula ---");
        Comprador c4 = new Comprador(null, Expendedor.nomProduct.SPRITE, exp);
        System.out.println("Queria comprar:"+ Expendedor.nomProduct.SPRITE+" | Valor:"+Expendedor.nomProduct.SPRITE.getPrecio()+"| Moneda:null | vuelto: " + c4.cuantoVuelto()+" | Resultado: " + c4.queConsumiste());

        // 2. Pago insuficiente (Moneda100 para producto de 1300)
        System.out.println("\n--- Caso 2: Pago insuficiente ---");
        Comprador c5 = new Comprador(new Moneda100(), Expendedor.nomProduct.COCACOLA, exp);
        System.out.println("Quería comprar: "+Expendedor.nomProduct.COCACOLA+" | Valor:"+Expendedor.nomProduct.COCACOLA.getPrecio() + " | Moneda:100 | vuelto: " + c5.cuantoVuelto()+" | Resultado: " + c5.queConsumiste());

        // 3. Sin stock (vendemos los 2 Super8 que hay)
        System.out.println("\n--- Prueba 3: Sin stock ---");
        System.out.println("Stock inicial de Super8: 2 unidades");
        Comprador c6 = new Comprador(new Moneda1000(), Expendedor.nomProduct.SUPER8, exp);
        Comprador c7 = new Comprador(new Moneda1000(), Expendedor.nomProduct.SUPER8, exp);
        Comprador c8 = new Comprador(new Moneda1000(), Expendedor.nomProduct.SUPER8, exp);
        System.out.println("Primer Super8: " + c6.queConsumiste());
        System.out.println("Segundo Super8: " + c7.queConsumiste());
        System.out.println("Tercer Super8 (falla): " + c8.queConsumiste());
    }
}