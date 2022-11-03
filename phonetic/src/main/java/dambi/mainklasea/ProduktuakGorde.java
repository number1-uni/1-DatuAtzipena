package dambi.mainklasea;

import java.util.Scanner;

import dambi.atzipena.*;
import dambi.pojoak.produktua.*;

public class ProduktuakGorde {

    private static Produktuak produktuak = new Produktuak();
    private static Csva csva = new Csva("produktuak.csv", "produktuakout.txt");
    static XMLa xmla = new XMLa("produktuakout.csv", "produktuakout.xml");
    private static Jsona jsona = new Jsona("produktuak.csv", "produktuakout.json");

    public static void main(String[] args) {
        
        db.connect();
        produktuak = db.produktuakGorde();

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
                    csva.idatzi(produktuak);
                    break;
                case 2:
                    xmla.idatzi(produktuak);
                    break;
                case 3:
                    jsona.idatzi(produktuak);
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


}
