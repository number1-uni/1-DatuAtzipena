package dambi.atzipena;

import java.sql.Statement;
import java.sql.Timestamp;
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

    /**
     * Zerbitzariarekin konektatuko da,
     * IP/DB/USER/PASS erabiliz.
     * 
     * @return Connection
     */
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
            conn.close();
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
                        rs.getFloat("price_unit"), rs.getInt("qty_invoiced"),
                        rs.getFloat("price_subtotal"), rs.getFloat("price_total"), rs.getString("write_date")));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return salmentak;
    }

    /**
     * Datu basean produktu bat sartuko da.
     * 
     * @param id
     * @param izena
     * @param deskripzioa
     * @param prezioa
     */
    public static void insertProduct(int id, String izena, String deskripzioa, float prezioa) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String sql = "INSERT INTO public.product_template VALUES( ?, NULL, ?,1, ?,NULL,NULL,'product','product',1, ?," +
                "10,10,true,true,1,1,NULL,true,NULL,NULL,false,false,0,2, ?,2, ?,0,'none',NULL,NULL,NULL,'receive','no-message'," + 
                "NULL,'false',0,'none','no','order',false);";

        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, izena);
            stmt.setString(3, deskripzioa);
            stmt.setFloat(4, prezioa);
            stmt.setTimestamp(5, timestamp);
            stmt.setTimestamp(6, timestamp);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
        }
    }

    /**
     * Azkenengo produktuaren id-a itzultzen du.
     * 
     * @return id
     */
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
            conn.close();
        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
        }
        return id;
    }

    /**
     * Datu basean salmenta bat sartuko da.
     * 
     * @param id
     * @param order_id
     * @param izena
     * @param prezioa
     * @param kantitatea
     */
    public static void insertSaleOrder(int id, int order_id, String izena, float prezioa, int kantitatea) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        float quantity = kantitatea;
        float price_subtotal = quantity * prezioa;
        float bez = 0.21f * price_subtotal;
        float price_total = quantity * prezioa + bez;
        float priceTaxUnit = 0.21f * prezioa;

        String sql = "INSERT INTO public.sale_order_line VALUES (?, ?, ?, 10,'invoiced', ?, ?, ?, ?, ?, ?, ?, 0.00, 10, ?, 1,"
                + "'stock_move', 0.00, 0.00, 0.00, ?, null, null, 2, 1, 1, 25, null, null, 'sale', 0, null, null, 0, 2, ?,"
                + " 2, ?, null);";

        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setInt(2, order_id);
            stmt.setString(3, izena);
            stmt.setFloat(4, prezioa);
            stmt.setFloat(5, price_subtotal);
            stmt.setFloat(6, bez);
            stmt.setFloat(7, price_total);
            stmt.setFloat(8, prezioa);
            stmt.setFloat(9, priceTaxUnit);
            stmt.setFloat(10, prezioa);
            stmt.setFloat(11, quantity);
            //stmt.setInt(12, kantitatea);
            stmt.setFloat(12, quantity);
            stmt.setTimestamp(13, timestamp);
            stmt.setTimestamp(14, timestamp);
            stmt.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
        }
    }

    /**
     * Azkenengo salmentaren id-a itzultzen du.
     * 
     * @return
     */
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
            conn.close();
        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
        }
        return id;
    }

    /**
     * Azkenengo salmentaren order_id-a itzultzen du.
     * 
     * @return
     */
    public static int findOrderId() {
        String sql = "SELECT id FROM public.sale_order ORDER BY id DESC LIMIT 1";
        int id = 0;
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt(1);
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
        }
        return id;
    }

    public static void createSaleOrderId() {
        int order_id = findOrderId() + 1;
        String name = "S00" + order_id;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String sql = "INSERT INTO public.sale_order VALUES (?, null, null, null, null, null, ?, null, null, null, 'sale', ?, null, true, false, ?,"
                + "2, 24, 24, 24, 1, 1, null, 'to invoice', '<p><br></p>', 0.00, 0.00, 0.00, 1.000000, null, 2, 1, 1, null, null, null,"
                + " true, 2, 1, ?,null, null, 'direct', 1, null, null, null);";

        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, order_id);
            stmt.setString(2, name);
            stmt.setTimestamp(3, timestamp);
            stmt.setTimestamp(4, timestamp);
            stmt.setTimestamp(5, timestamp);
            stmt.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
        }
    }

    /**
     * Salmentako produktua aukeratzeko metodoa.
     * 
     * @return izena
     */
    public static String selectProductName() {
        int produktuaIndex;
        produktuakGorde();
        String izena = null;
        try {
            for (int i = 0; i < produktuak.getProduktuak().size(); i++) {
                System.out.println((i + 1) + ". " + produktuak.getProduktuak().get(i).getName());
            }
            System.out.println("------------------------------------");
            System.out.print("Sartu produktuaren zenbakia:");
            produktuaIndex = in.nextInt();
            izena = produktuak.getProduktuak().get(produktuaIndex - 1).getName();
        } catch (Exception e) {

        }
        return izena;
    }

    public static void importSalmenta(Salmentak salmentak) {
        System.out.println("KARGATZEN...");
        createSaleOrderId();
        for (int i = 0; i < salmentak.getSalmentak().size(); i++) {
            int id = findIdSale() + 1;
            int order_id = findOrderId();
            String izena = salmentak.getSalmentak().get(i).getName();
            float prezioa = salmentak.getSalmentak().get(i).getPrice_unit();
            int kantitatea = salmentak.getSalmentak().get(i).getQty_invoiced();
            insertSaleOrder(id, order_id, izena, prezioa, kantitatea);
        }
        System.out.println("KARGATU DIRA SALMENTAK!");

    }
}
