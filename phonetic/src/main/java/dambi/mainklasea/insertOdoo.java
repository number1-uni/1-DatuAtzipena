package dambi.mainklasea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import dambi.atzipena.db;

public class insertOdoo {
    public static void main(String[] args) {
        String izena;
        float prezioa;
        int kantitatea;
        boolean check = false;
        Scanner in = new Scanner(System.in);
        int id = findId() + 1;
        while (!check){ 
            try{        
                System.out.println("PRODUKTUA SORTZEKO MENUA");
                System.out.print("Izena: ");
                izena = in.nextLine();
                System.out.print("Prezioa: ");
                prezioa = in.nextFloat();
                System.out.print("Kantitatea: ");
                kantitatea = in.nextInt();
                insertProduct(id, izena, prezioa, kantitatea);
                check = true;
                System.out.println("Produktua ondo sortu da.");
            } catch (Exception e) {
                System.out.println("Sartu duzun balioak ez dira zuzenak. Saiatu berriz.");
                in.nextLine();
            }
        }
        in.close();
        
    }

    public static void insertProduct(int id, String izena, float prezioa, int kantitatea) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new java.util.Date());
        float quantity = kantitatea;
        float price_subtotal = quantity * prezioa;
        float BEZ = 0.21f * price_subtotal;
        float price_total = quantity * prezioa + BEZ;
        float priceTaxUnit = 0.21f * prezioa;
        
        String sql = "INSERT INTO public.sale_order_line VALUES ("+ id + ", 6, '" + izena + "', 10,'invoiced', "+ prezioa + ", "+ price_subtotal + ", "
                    + BEZ +", " + price_total + ", " + prezioa +", "+ priceTaxUnit +", " + prezioa + ", 0.00, 10, "+ quantity + " , "+ kantitatea 
                    + ", 'stock_move', 0.00, 0.00, 0.00, 1.00, 0.00, 159.00, 2, 1, 1, 25, null, null, 'sale', 0, null, null, 0, 2, '"
                    + timeStamp + "', 2, '"+ timeStamp + "', null);";

        try {
            Connection conn = db.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
        }
    }

    public static int findId() {
        String sql = "SELECT id FROM public.sale_order_line ORDER BY id DESC LIMIT 1";
        int id = 0;
        try {
            Connection conn = db.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
        }
        return id;
    }

}
