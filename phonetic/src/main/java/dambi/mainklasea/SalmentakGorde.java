package dambi.mainklasea;

import java.util.Scanner;

import dambi.atzipena.*;
import dambi.pojoak.salmenta.*;

public class SalmentakGorde {

    private static Salmentak salmentak = new Salmentak();
    private static Csva csva = new Csva("salmentakOut.csv", "salmentakOut.csv");
    private static XMLa xmla = new XMLa("salmentakOut.xml", "salmentakOut.xml");
    private static Jsona jsona = new Jsona("salmentakOut.json", "salmentakOut.json");

    public static void main(String[] args) {
        
        db.connect();

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
                    salmentak = db.salmentakGorde();
                    csva.idatzi2(salmentak);
                    break;
                case 2:
                    salmentak = db.salmentakGorde();
                    xmla.idatzi2(salmentak);
                    break;
                case 3:
                    salmentak = db.salmentakGorde();
                    jsona.idatzi2(salmentak);
                    break;
                case 4:
                    csva.irakurri(salmentak);
                    break;
                case 5:
                    xmla.irakurri(salmentak);
                    break;
                case 6:
                    jsona.irakurri(salmentak);
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
