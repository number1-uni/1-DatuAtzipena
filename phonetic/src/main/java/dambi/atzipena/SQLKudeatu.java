package dambi.atzipena;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dambi.pojoak.erosketa.*;
import dambi.pojoak.produktua.*;
import dambi.pojoak.salmenta.*;

public class SQLKudeatu {

    private static String db = "PhoneTic";
    private static String url = "jdbc:postgresql://192.168.65.13:5432/" + db;
    private static String user = "raul"; // !IMPORTANT! username
    private static String password = "raul"; // !IMPORTANT! password

    Produktuak produktuak = new Produktuak();
    Salmentak salmentak = new Salmentak();
    Erosketak erosketak = new Erosketak();

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void produktuakImprimatu() {

        System.out.println(produktuak.getProduktuak());

        /*
        String taula = "product_template";
        String sql = "SELECT * FROM " + taula;
        int count = 0;

        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println(taula + " taulako datuak:");
            System.out.println("===================================");
            while (rs.next()) {
                System.out.printf("%s", rs.getString("name"));
                System.out.printf("%.2f%n", rs.getDouble("list_price"));
                count++;
            }
            System.out.println("-----------------------------------");
            System.out.println("Guztira " + count + " erregistro");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        */
    }

    public void produktuakGorde() {
        String taula = "product_template";
        String sql = "SELECT * FROM " + taula;

        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                produktuak.add(new Produktua(rs.getInt("id"), rs.getString("name"), rs.getDouble("list_price")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void salmentakImprimatu() {

        // Gorde egin behar dira salmenta lehenengo hurrengo ilara erabiltzeko
        // Beste dena ez da beharrezkoa, hau erabili ezkero
        System.out.println(salmentak.getSalmentak());

        //Bigarren metodoa printa modifikatu nahi badugu
        String taula = "sale_order";
        String sql = "SELECT * FROM " + taula;
        int count = 0;

        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println(taula + " taulako datuak:");
            System.out.println("===================================");
            while (rs.next()) {
                System.out.printf("%s", rs.getString("name"));
                System.out.printf("%.2f%n", rs.getDouble("amount_total"));
                count++;
            }
            System.out.println("-----------------------------------");
            System.out.println("Guztira " + count + " erregistro");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void salmentakGorde() {
        
        String taula = "sale_order_line";
        String sql = "SELECT * FROM " + taula;

        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                salmentak.add(new Salmenta(rs.getInt("id"), rs.getInt("product_id"), rs.getString("name"),
                rs.getDouble("price_unit"), rs.getInt("qty_invoiced"), rs.getDouble("price_subtotal"), rs.getDouble("price_total")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void erosketakGorde() {
        
        String taula = "purchase_order_line";
        String sql = "SELECT * FROM " + taula;

        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {

                /** LocalDateTime "date_planned" String-ean gorde 
                *   eta LocalDateTime formatuan itzuli
                **/
                String data = rs.getString("date_planned");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(data, formatter);

                erosketak.add(new Erosketa(rs.getInt("id"), rs.getInt("product_id"), rs.getString("name"),
                                        rs.getDouble("price_unit"), rs.getInt("product_qty"), rs.getDouble("price_subtotal"),
                                        rs.getDouble("price_total"), dateTime));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void erosketakImprimatu() {

        System.out.println(erosketak.getErosketak());
    
    }

    /**
    public void hornitzaileakImprimatu() {
        String taula = "product_template";
        String sql = "SELECT * FROM " + taula;
        int count = 0;

        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println(taula + " taulako datuak:");
            System.out.println("===================================");
            while (rs.next()) {
                System.out.printf("%s", rs.getString("name"));
                System.out.printf("%.2f%n", rs.getDouble("list_price"));
                count++;
            }
            System.out.println("-----------------------------------");
            System.out.println("Guztira " + count + " erregistro");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void bezeroakImprimatu() {
        String taula = "product_template";
        String sql = "SELECT * FROM " + taula;
        int count = 0;

        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println(taula + " taulako datuak:");
            System.out.println("===================================");
            while (rs.next()) {
                System.out.printf("%s", rs.getString("name"));
                System.out.printf("%.2f%n", rs.getDouble("list_price"));
                count++;
            }
            System.out.println("-----------------------------------");
            System.out.println("Guztira " + count + " erregistro");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    **/
}
