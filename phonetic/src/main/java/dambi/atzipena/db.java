package dambi.atzipena;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dambi.pojoak.produktua.*;
import dambi.pojoak.salmenta.*;

public class db {

    private static String db = "PhoneTic";
    private static String url = "jdbc:postgresql://192.168.65.13:5432/" + db;
    private static String user = "DA"; // !IMPORTANT! username
    private static String password = "admin"; // !IMPORTANT! password
    private static Salmentak salmentak = new Salmentak();
    private static Produktuak produktuak = new Produktuak();
    
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    
    public static Produktuak produktuakGorde() {
        String taula = "product_template";
        String sql = "SELECT * FROM " + taula;

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

    public static Salmentak salmentakGorde() {
        String taula = "sale_order_line";
        String sql = "SELECT * FROM " + taula;

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
