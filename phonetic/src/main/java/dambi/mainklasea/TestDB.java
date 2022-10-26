package dambi.mainklasea;

import java.util.Scanner;

import dambi.atzipena.*;

public class TestDB {

    /**
     * @param args
     */
    public static void main(String[] args) {
        /*PRODUKTUAK BAKARRIK ERABILI
         * EEEEEZZZZZZ ERABILI SALMENTAK ETA EROSKETAK
         */
        
        SQLKudeatu sql = new SQLKudeatu();
        sql.connect();

        Scanner in = new Scanner(System.in);
        int aukera = 0;
        do {
            System.out.println();
            System.out.println("MAIN MENUA");
            System.out.println("====================================");
            System.out.println("1.- Produktuak ikusi");
            System.out.println("2.- Salmentak ikusi");
            System.out.println("3.- Erosketak ikusi");
            System.out.println("10.- Irten");
            System.out.println("");
            System.out.print("Aukeratu zenbaki bat: ");

            aukera = in.nextInt();

            switch (aukera) {
                case 1:
                    sql.produktuakGorde();
                    sql.produktuakImprimatu();
                    break;
                case 2:
                    sql.salmentakGorde();
                    sql.salmentakImprimatu();
                    break;
                case 3:
                    sql.erosketakGorde();
                    sql.erosketakImprimatu();
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
