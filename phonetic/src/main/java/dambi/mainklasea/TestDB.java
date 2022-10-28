package dambi.mainklasea;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import dambi.atzipena.*;
import dambi.pojoak.produktua.*;
import dambi.pojoak.salmenta.*;

public class TestDB {

    private static String db = "PhoneTic";
    private static String url = "jdbc:postgresql://192.168.65.13:5432/" + db;
    private static String user = "raul"; // !IMPORTANT! username
    private static String password = "raul"; // !IMPORTANT! password

    private static Produktuak produktuak = new Produktuak();
    private static Salmentak salmentak = new Salmentak();
    private static Csva csva = new Csva("produktuak.csv", "produktuakout.txt");
    static XMLa xmla = new XMLa("produktuakout.csv", "produktuakout.xml");
    private static Jsona jsona = new Jsona("produktuak.csv", "produktuakout.json");

    public static void main(String[] args) {
        /*PRODUKTUAK BAKARRIK ERABILI
         * EEEEEZZZZZZ ERABILI SALMENTAK ETA EROSKETAK
         */
        connect();
        produktuakGorde();
        salmentakGorde();

        Scanner in = new Scanner(System.in);
        int aukera = 0;
        do {
            System.out.println();
            System.out.println("MAIN MENUA");
            System.out.println("====================================");
            System.out.println("1.- Produktuak CVS fitxategian gorde");
            System.out.println("2.- Produktuak XML fitxategian gorde");
            System.out.println("3.- Produktuak JSON fitxategian gorde");
            System.out.println("10.- Irten");
            System.out.println("");
            System.out.print("Aukeratu zenbaki bat: ");

            aukera = in.nextInt();

            switch (aukera) {
                case 1:
                    //csva.idatzi(produktuak);
                    csva.idatzi2(salmentak);
                    break;
                case 2:
                    //xmla.idatzi(produktuak);
                    xmla.idatzi2(salmentak);
                    break;
                case 3:
                    //jsona.idatzi(produktuak);
                    jsona.idatzi2(salmentak);
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

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void produktuakGorde() {
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
    }

    public static void salmentakGorde() {
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
    }
}
