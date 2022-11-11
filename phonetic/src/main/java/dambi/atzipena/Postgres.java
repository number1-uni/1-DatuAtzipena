package dambi.atzipena;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

import dambi.pojoak.produktua.*;
import dambi.pojoak.salmenta.*;

public class Postgres {

    private static Scanner in = new Scanner(System.in);
    private static String url = "jdbc:postgresql://192.168.65.13:5432/PhoneTic";
    private static String user = "DA"; // !IMPORTANT! username
    private static String password = "admin"; // !IMPORTANT! password
    public static Salmentak salmentak = new Salmentak();
    public static Produktuak produktuak = new Produktuak();

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Datu baseko produktuak Lista batean gordeko dira.
     * Zutabeak: id, name, list_price.
     * 
     * @return Produktuak List
     */
    public static Produktuak produktuakGorde() {
        String sql = "SELECT * FROM product_template";

        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                produktuak.add(new Produktua(rs.getInt("id"), rs.getString("name"), rs.getDouble("list_price")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return produktuak;
    }

    /**
     * Datu baseko salmentak Lista batean gordeko dira.
     * Zutabeak: id, product_id, name, price_unit, qty_invoiced,
     * price_subtotal, price_total, write_date.
     * 
     * @return Salmentak List
     */
    public static Salmentak salmentakGorde() {
        String sql = "SELECT * FROM sale_order_line";

        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {

                salmentak.add(new Salmenta(rs.getInt("id"), rs.getInt("product_id"), rs.getString("name"),
                        rs.getDouble("price_unit"), rs.getInt("qty_invoiced"),
                        rs.getDouble("price_subtotal"), rs.getDouble("price_total"), rs.getString("write_date")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return salmentak;
    }
    
    public static void insertProduct(int id, String izena, String deskripzioa, float prezioa) {

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new java.util.Date());
        ;

        String sql = "INSERT INTO public.product_template VALUES( " + id + ", NULL,'" + izena + "',1,'" + deskripzioa
                + "',NULL,NULL,'product','product',1," + prezioa
                + ",10,10,true,true,1,1,NULL,true,NULL,NULL,false,false,0,2,'" + timeStamp + "',2,'" + timeStamp
                + "',0,'none',NULL,NULL,NULL,'receive','no-message',NULL,'false',0,'none','no','order',false);";

        try {
            Connection conn = connect();
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
            Connection conn = connect();
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
    
    public static void insertSaleOrder(int id,int order_id, String izena,  float prezioa, int kantitatea) {
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
            Connection conn = connect();
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
            Connection conn = connect();
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
            Connection conn = connect();
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
        produktuakGorde();
        System.out.println("PRODUKTUA SORTZEKO MENUA");
        for (int i = 0; i < produktuak.getProduktuak().size(); i++) {
            System.out.println((i+1) + ". " + produktuak.getProduktuak().get(i).getName());
        }
        System.out.println("------------------------------------");
        System.out.print("Sartu produktuaren zenbakia:");
        produktuaIndex = in.nextInt();
        String izena = produktuak.getProduktuak().get(produktuaIndex-1).getName();
        return izena;
    }
}
