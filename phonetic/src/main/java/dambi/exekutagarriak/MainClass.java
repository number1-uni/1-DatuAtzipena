package dambi.exekutagarriak;

import java.util.Scanner;

import dambi.atzipena.*;
import dambi.pojoak.produktua.*;
import dambi.pojoak.salmenta.*;

public class MainClass {
    private static Produktuak produktuak = new Produktuak();
    private static Salmentak salmentak = new Salmentak();
    private static Csva csva = new Csva("produktuak.csv", "produktuakout.txt");
    static XMLa xmla = new XMLa("produktuakout.csv", "produktuakout.xml");
    private static Jsona jsona = new Jsona("produktuak.csv", "produktuakout.json");
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

    public static void exportProducts(){
        Db.connect();
        produktuak = Db.produktuakGorde();

        Scanner in = new Scanner(System.in);
        int aukera = 0;
        do {
            System.out.println();
            System.out.println("PRODUKTU MENUA");
            System.out.println("====================================");
            System.out.println("1.- Produktuak CVS fitxategian gorde");
            System.out.println("2.- Produktuak XML fitxategian gorde");
            System.out.println("3.- Produktuak JSON fitxategian gorde");
            System.out.println("10.- Irten");
            System.out.println("");
            System.out.print("Aukeratu zenbaki bat: ");

            aukera = in.nextInt();

            switch (aukera) {
                case 1:
                    csva.idatziProducts(produktuak);
                    break;
                case 2:
                    xmla.idatziProducts(produktuak);
                    break;
                case 3:
                    jsona.idatziProducts(produktuak);
                    break;
                case 10:
                    System.out.println("Irtetzen ...");
                    //mainClass.main(args);
                    break;
                default:
                    System.out.println("Aukera okerra. Saiatu berriz.");
            }
        } while (aukera != 10);
        in.close();
    }

    public static void saveSales(){
        Db.connect();

        Scanner in = new Scanner(System.in);
        int aukera = 0;
        do {
            System.out.println();
            System.out.println("SALMENTA MENUA");
            System.out.println("====================================");
            System.out.println("1.- Salmentak CSV fitxategian gorde");
            System.out.println("2.- Salmentak XML fitxategian gorde");
            System.out.println("3.- Salmentak JSON fitxategian gorde");
            System.out.println("4.- Salmentak CSV fitxategia irakurri eta bistaratu");
            System.out.println("5.- Salmentak XML fitxategia irakurri eta bistaratu");
            System.out.println("6.- Salmentak XML fitxategia irakurri eta bistaratu");
            System.out.println("10.- Irten");
            System.out.println("");
            System.out.print("Aukeratu zenbaki bat: ");

            aukera = in.nextInt();

            switch (aukera) {
                case 1:
                    salmentak = Db.salmentakGorde();
                    csva.idatziSales(salmentak);
                    break;
                case 2:
                    salmentak = Db.salmentakGorde();
                    xmla.idatziSales(salmentak);
                    break;
                case 3:
                    salmentak = Db.salmentakGorde();
                    jsona.idatziSales(salmentak);
                    break;
                case 4:
                    csva.irakurriSales(salmentak);
                    break;
                case 5:
                    xmla.irakurriSales(salmentak);
                    break;
                case 6:
                    jsona.irakurriSales(salmentak);
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
    
    public static void saveProduct() {
        String izena, deskripzioa;
        float prezioa;
        boolean check = false;
        Scanner in = new Scanner(System.in);
        int id = Postgres.findIdProduct() + 1;
        while (!check){ 
            try{        
                System.out.println("PRODUKTUA SORTZEKO MENUA");
                System.out.print("Izena: ");
                izena = in.nextLine();
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
        in.close();
    }

    public static void saleOrder() {
        String izena = Postgres.selectProductName();
        boolean check = false;
        float prezioa;
        int kantitatea;
        int id = Postgres.findIdSale() + 1;
        int order_id = Postgres.findOrderId();
        while (!check){ 
            try{        
                System.out.print("Prezioa: ");
                prezioa = in.nextFloat();
                System.out.print("Kantitatea: ");
                kantitatea = in.nextInt();
                Postgres.insertProduct(id, order_id, izena, prezioa, kantitatea);
                check = true;
            } catch (Exception e) {
                System.out.println("Sartu duzun balioak ez dira zuzenak. Saiatu berriz.");
                in.nextLine();
            }
        }
        in.close();
    }
}
