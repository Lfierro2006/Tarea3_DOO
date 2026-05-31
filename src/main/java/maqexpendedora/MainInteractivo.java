import maqexpendedora.*;
import moneda.*;
import java.util.Scanner;
import excepciones.*;


public class MainInteractivo {
    public static void main(String[] args) {
            Scanner teclado = new Scanner(System.in);

        Expendedor maquina = new Expendedor(3);
        boolean maquinaEncendida = true;


        while (maquinaEncendida){
            System.out.println("\n¿Qué deseas hacer?");
            System.out.println("1. Insertar moneda y comprar");
            System.out.println("2. Irse de la máquina");
            System.out.print("Elige una opción: ");

            int opcion = teclado.nextInt();
            if (opcion == 2){
                System.out.println("Gracias por su servicio, adios");
                maquinaEncendida=false;
            } else if (opcion==1 ) {
                System.out.println("\n¿Qué moneda vas a insertar?");
                System.out.println("1. $500");
                System.out.println("2. $1000");
                System.out.println("3. $1500");
                System.out.println("4. $100 "); // solo lo inclui para que haya comprobacion que funcionan los exception
                System.out.print("Opción: ");
                int opMoneda = teclado.nextInt();

                Moneda monedaInsertada =null;

                    switch (opMoneda) {
                        case 1:
                            monedaInsertada = new Moneda500();
                            break;
                        case 2:
                            monedaInsertada = new Moneda1000();
                            break;
                        case 3:
                            monedaInsertada = new Moneda1500();
                            break;
                        case 4:
                            monedaInsertada = new Moneda100();
                            break;
                        default:
                           monedaInsertada = null;
                    }
                    System.out.println();
                    System.out.println("¿Que deseas comprar?");
                    System.out.println("1. Snicker Precio: 500$");
                    System.out.println("2. Chokita Precio: 400$");
                    System.out.println("3. Super8 Precio: 500$");
                    System.out.println("4. Coca-Cola Precio: 1300$");
                    System.out.println("5. Fanta Precio: 1000$");
                    System.out.println("6. Sprite Precio: 800$");
                    System.out.println("Opcion: ");
                    int opProducto= teclado.nextInt();
                    Expendedor.nomProduct productoelegido=null;
                    try {
                        switch (opProducto) {
                            case 1:
                                productoelegido = Expendedor.nomProduct.SNICKER;
                                break;
                            case 2:
                                productoelegido = Expendedor.nomProduct.CHOKITA;
                                break;
                            case 3:
                                productoelegido = Expendedor.nomProduct.SUPER8;
                                break;
                            case 4:
                                productoelegido = Expendedor.nomProduct.COCACOLA;
                                break;
                            case 5:
                                productoelegido = Expendedor.nomProduct.FANTA;
                                break;
                            case 6:
                                productoelegido = Expendedor.nomProduct.SPRITE;
                                break;
                            default:
                                throw new NoHayProductoException("No existe este producto");
                        }
                        System.out.println();
                        System.out.println("Procesando Compra...");

                        Comprador cliente = new Comprador(monedaInsertada, productoelegido, maquina);
                        if (cliente.queConsumiste() == null) {
                            System.out.println();
                        } else {
                            System.out.println("Disfruta tu " + cliente.queConsumiste());
                        }

                        System.out.println("Tu Vuelto:" + cliente.cuantoVuelto());

                    }catch(NoHayProductoException e){ //tuve que añadirlo porque sino saltaba la excepcion de NULLPOINTER
                        System.out.println("Error:"+ e.getMessage());
                        System.out.println("Volviendo al Menu principal:");

                }
            }

        }
    }
}
