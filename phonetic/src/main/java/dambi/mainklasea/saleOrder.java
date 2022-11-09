package dambi.mainklasea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import dambi.atzipena.db;

public class saleOrder {
    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        String izena = selectProductName();
        boolean check = false;
        float prezioa;
        int kantitatea;
        int id = findId() + 1;
        int order_id = findOrderId();
        while (!check){ 
            try{        
                System.out.print("Prezioa: ");
                prezioa = in.nextFloat();
                System.out.print("Kantitatea: ");
                kantitatea = in.nextInt();
                insertProduct(id, order_id, izena, prezioa, kantitatea);
                check = true;
            } catch (Exception e) {
                System.out.println("Sartu duzun balioak ez dira zuzenak. Saiatu berriz.");
                in.nextLine();
            }
        }
        in.close();
    }
    
    public static void insertProduct(int id,int order_id, String izena,  float prezioa, int kantitatea) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new java.util.Date());
        float quantity = kantitatea;
        float price_subtotal = quantity * prezioa;
        float BEZ = 0.21f * price_subtotal;
        float price_total = quantity * prezioa + BEZ;
        float priceTaxUnit = 0.21f * prezioa;
        
        String sql = "INSERT INTO public.sale_order_line VALUES ("+ id + ", 7, '" + izena + "', 10,'invoiced', "+ prezioa + ", "+ price_subtotal + ", "
                    + BEZ +", " + price_total + ", " + prezioa +", "+ priceTaxUnit +", " + prezioa + ", 0.00, 10, "+ quantity + " , "+ kantitatea 
                    + ", 'stock_move', 0.00, 0.00, 0.00, " + quantity + ", null, null, 2, 1, 1, 25, null, null, 'sale', 0, null, null, 0, 2, '"
                    + timeStamp + "', 2, '"+ timeStamp + "', null);";

        try {
            Connection conn = db.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            System.out.println("Produktua ondo sortu da.");
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

    public static int findOrderId() {
        String sql = "SELECT order_id FROM public.sale_order_line ORDER BY id DESC LIMIT 1";
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

    public static String selectProductName(){
        int produktuaIndex;
        db.produktuakGorde();
        System.out.println("PRODUKTUA SORTZEKO MENUA");
        for (int i = 0; i < db.produktuak.getProduktuak().size(); i++) {
            System.out.println((i+1) + ". " + db.produktuak.getProduktuak().get(i).getName());
        }
        System.out.println("------------------------------------");
        System.out.print("Sartu produktuaren zenbakia:");
        produktuaIndex = in.nextInt();
        String izena = db.produktuak.getProduktuak().get(produktuaIndex-1).getName();
        return izena;
    }

}
