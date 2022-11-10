package dambi.atzipena;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Postgres {
    private static Scanner in = new Scanner(System.in);
    
    public static void insertProduct(int id, String izena, String deskripzioa, float prezioa) {

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new java.util.Date());
        ;

        String sql = "INSERT INTO public.product_template VALUES( " + id + ", NULL,'" + izena + "',1,'" + deskripzioa
                + "',NULL,NULL,'product','product',1," + prezioa
                + ",10,10,true,true,1,1,NULL,true,NULL,NULL,false,false,0,2,'" + timeStamp + "',2,'" + timeStamp
                + "',0,'none',NULL,NULL,NULL,'receive','no-message',NULL,'false',0,'none','no','order',false);";

        try {
            Connection conn = Db.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
        }
    }

    public static int findIdProduct() {
        String sql = "SELECT id FROM public.product_template ORDER BY id DESC LIMIT 1";
        int id = 0;
        try {
            Connection conn = Db.connect();
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
            Connection conn = Db.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            System.out.println("Produktua ondo sortu da.");
        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
        }
    }

    public static int findIdSale() {
        String sql = "SELECT id FROM public.sale_order_line ORDER BY id DESC LIMIT 1";
        int id = 0;
        try {
            Connection conn = Db.connect();
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
            Connection conn = Db.connect();
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
        Db.produktuakGorde();
        System.out.println("PRODUKTUA SORTZEKO MENUA");
        for (int i = 0; i < Db.produktuak.getProduktuak().size(); i++) {
            System.out.println((i+1) + ". " + Db.produktuak.getProduktuak().get(i).getName());
        }
        System.out.println("------------------------------------");
        System.out.print("Sartu produktuaren zenbakia:");
        produktuaIndex = in.nextInt();
        String izena = Db.produktuak.getProduktuak().get(produktuaIndex-1).getName();
        return izena;
    }
}
