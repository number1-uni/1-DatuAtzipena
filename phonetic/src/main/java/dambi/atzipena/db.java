package dambi.atzipena;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dambi.pojoak.produktua.*;
import dambi.pojoak.salmenta.*;

/**
 * Klase honen bidez datu basearekin konektatuko da.
 * Horretaz gain, datu baseko produktuak eta salmenta
 * datuak Lista batean gordeko dira.
 */
public class db {

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
     *           price_subtotal, price_total, write_date.
     * @return Salmentak List
     */
    public static Salmentak salmentakGorde() {
        String sql = "SELECT * FROM sale_order_line";

        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {

                salmentak.add(new Salmenta(rs.getInt("id"), rs.getInt("product_id"), rs.getString("name"), rs.getDouble("price_unit"), rs.getInt("qty_invoiced"),
                rs.getDouble("price_subtotal"), rs.getDouble("price_total"), rs.getString("write_date") ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return salmentak;
    }
}
