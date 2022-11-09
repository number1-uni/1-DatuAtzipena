package dambi.mainklasea;

import java.util.Scanner;

public class mainClass {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
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
                    saveProduct.main(args);
                    break;
                case 2:
                    exportProducts.main(args);
                    break;
                case 3:
                    saleOrder.main(args);
                    break;
                case 4:
                    saveSales.main(args);
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
