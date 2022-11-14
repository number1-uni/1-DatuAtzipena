package dambi.exekutagarriak;

import java.util.InputMismatchException;
import java.util.Scanner;

import dambi.atzipena.*;
import dambi.pojoak.produktua.*;
import dambi.pojoak.salmenta.*;

public class Main {
    private static Produktuak produktuak = new Produktuak();
    private static Salmentak salmentak = new Salmentak();
    private static Csva csvaProduct = new Csva("produktuakout.txt", "produktuakout.txt");
    private static XMLa xmlaProduct = new XMLa("produktuakout.xml", "produktuakout.xml");
    private static Jsona jsonaProduct = new Jsona("produktuakout.json", "produktuakout.json");
    private static Csva csvaSales = new Csva("salmentakOut.txt", "salmentakOut.txt");
    private static XMLa xmlaSales = new XMLa("salmentakOut.xml", "salmentakOut.xml");
    private static Jsona jsonaSales = new Jsona("salmentakOut.json", "salmentakOut.json");
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        int aukera = 0;
        do {
            System.out.println();
            System.out.println("MENU NAGUSIA");
            System.out.println("====================================");
            System.out.println("1.- Produktu bat sortu");
            System.out.println("2.- Produktuak Exportatu");
            System.out.println("3.- Salmenta bat sortu");
            System.out.println("4.- Salmentak inportatu/exportatu");
            System.out.println("10.- Irten");
            System.out.println("");
            System.out.print("Aukeratu zenbaki bat: ");

            aukera = in.nextInt();

            switch (aukera) {
                case 1:
                    saveProduct();
                    break;
                case 2:
                    exportProducts(); 
                    break;
                case 3:
                    saleOrder();
                    break;
                case 4:
                    saveSales();
                    break;
                case 10:
                    System.out.println("Eskerrik asko programa hau erabiltzeagatik.");
                    break;
                default:
                    System.out.println("Aukera okerra. Saiatu berriz.");
            }
        } while (aukera != 10);
        in.close();
    }

    /**
     * Produktuak fitxategi ezberdinetan gordetzeko aukera ematen du.
     */
    public static void exportProducts() {
        Postgres.connect();
        produktuak = Postgres.produktuakGorde();

        int aukera = 0;
        boolean salir = false;
        while (!salir) {

            System.out.println();
            System.out.println("PRODUKTU MENUA");
            System.out.println("====================================");
            System.out.println("1.- Produktuak CVS fitxategian gorde");
            System.out.println("2.- Produktuak XML fitxategian gorde");
            System.out.println("3.- Produktuak JSON fitxategian gorde");
            System.out.println("10.- Irten");
            System.out.println("");
            System.out.print("Aukeratu zenbaki bat: ");
            try {
                aukera = in.nextInt();

                switch (aukera) {
                    case 1:
                        csvaProduct.idatziProducts(produktuak);
                        break;
                    case 2:
                        xmlaProduct.idatziProducts(produktuak);
                        break;
                    case 3:
                        jsonaProduct.idatziProducts(produktuak);
                        break;
                    case 10:
                        salir = true;
                        System.out.println("Irtetzen ...");
                        // mainClass.main(args);
                        break;
                    default:
                        System.out.println("Aukera okerra. Saiatu berriz.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Zenbaki bat aukeratu behar duzu");
                in.nextInt();
            }
        }
    }

    /**
     * Salmentak fitxategi ezberdinetan gordetzeko aukera ematen du 
     * eta fitxategi bat irakurri eta bistaratzeko aukera ematen du.
     */
    public static void saveSales() {
        Postgres.connect();

        int aukera = 0;
        boolean salir = false;
        while (!salir) {
            System.out.println();
            System.out.println("SALMENTA MENUA");
            System.out.println("====================================");
            System.out.println("1.- Salmentak CSV fitxategian gorde");
            System.out.println("2.- Salmentak XML fitxategian gorde");
            System.out.println("3.- Salmentak JSON fitxategian gorde");
            System.out.println("4.- Salmentak CSV fitxategia irakurri eta inportatu");
            System.out.println("5.- Salmentak XML fitxategia irakurri eta inportatu");
            System.out.println("6.- Salmentak JSON fitxategia irakurri eta inportatu");
            System.out.println("10.- Irten");
            System.out.println("");
            System.out.print("Aukeratu zenbaki bat: ");
            try {
                aukera = in.nextInt();

                switch (aukera) {
                    case 1:
                        salmentak = Postgres.salmentakGorde();
                        csvaSales.idatziSales(salmentak);
                        break;
                    case 2:
                        salmentak = Postgres.salmentakGorde();
                        xmlaSales.idatziSales(salmentak);
                        break;
                    case 3:
                        salmentak = Postgres.salmentakGorde();
                        jsonaSales.idatziSales(salmentak);
                        break;
                    case 4:
                        csvaSales.irakurriSales(salmentak);
                        System.out.println(salmentak);
                        Postgres.importSalmenta(salmentak);
                        break;
                    case 5:
                        xmlaSales.irakurriSales(salmentak);
                        System.out.println(salmentak);
                        Postgres.importSalmenta(salmentak);
                        break;
                    case 6:
                        jsonaSales.irakurriSales(salmentak);
                        System.out.println(salmentak);
                        Postgres.importSalmenta(salmentak);
                        break;
                    case 10:
                        salir = true;
                        System.out.println("Eskerrik asko programa hau erabiltzeagatik.");
                        break;
                    default:
                        System.out.println("Aukera okerra. Saiatu berriz.");

                }
            } catch (InputMismatchException e) {
                System.out.println("Zenbaki bat aukeratu behar duzu");
                in.nextInt();
            }
        }
        ;
    }

    /**
     * Produktu bat sortzeko menua.
     */
    public static void saveProduct() {
        String izena, deskripzioa;
        float prezioa;
        boolean check = false;
        int id = Postgres.findIdProduct() + 1;
        while (!check) {
            try {
                System.out.println("PRODUKTUA SORTZEKO MENUA");
                System.out.print("Izena: ");
                izena = in.next();
                System.out.print("Deskripzioa: ");
                deskripzioa = in.nextLine();
                System.out.print("Prezioa: ");
                prezioa = in.nextFloat();
                Postgres.insertProduct(id, izena, deskripzioa, prezioa);
                check = true;
                System.out.println("Produktua ondo sortu da.");
            } catch (Exception e) {
                System.out.println("Sartu duzun balioak ez dira zuzenak. Saiatu berriz.");
                in.nextLine();
            }
        }
    }

    /**
     * Salmenta bat sortzeko menua.
     */
    public static void saleOrder() {
        String izena = Postgres.selectProductName();
        Postgres.createSaleOrderId();
        boolean check = false;
        float prezioa;
        int kantitatea;
        int id = Postgres.findIdSale() + 1;
        int order_id = Postgres.findOrderId();
        while (!check) {
            try {
                System.out.print("Prezioa: ");
                prezioa = in.nextFloat();
                System.out.print("Kantitatea: ");
                kantitatea = in.nextInt();
                Postgres.insertSaleOrder(id, order_id, izena, prezioa, kantitatea);
                check = true;
                System.out.println("Salmenta ondo sortu da.");
            } catch (Exception e) {
                System.out.println("Sartu duzun balioak ez dira zuzenak. Saiatu berriz.");
                in.nextLine();
            }
        }
    }
}
