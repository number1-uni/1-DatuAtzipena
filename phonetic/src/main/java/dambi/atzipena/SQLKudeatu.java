package dambi.atzipena;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLKudeatu {

    /**
     * Connect to a sample database
     */
    private static String db = "PhoneTic";
    private static String url = "jdbc:postgresql://192.168.65.13:5432/" + db;
    private static String user = "raul";
    private static String password = "raul";


    /**
     * SQL Datu Basearekin konektatzeko
     * @return conn; 
     */
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /*Taula JAVA bidez sortzeko*/
    public static void createNewTable() {
        
    }

    /**
     * Taulako datuak imprimatuko ditu
     */
    public void terminoakImprimatu() {
        String sql = "SELECT * FROM EROSKETAK";
        int count=0;

        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            System.out.println(db + " taulako datuak:");
            System.out.println("===================================");
            while (rs.next()) {
                System.out.print(rs.getInt("Erosketa_Id") + "\t"
                        + rs.getInt("Produktu_Id") + "\t" + "\n");
                count++;
            }
            System.out.println("-----------------------------------");
            System.out.println("Guztira " + count + " erregistro");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Baloreak pasatuz erregistro berri bat sortzen du 
     * @param euskaraz
     * @param gazteleraz 
     */
    public void terminoakSartu(String euskaraz, String gazteleraz) {
        String sql = "INSERT INTO Terminoak (euskaraz,gazteleraz) VALUES(?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, euskaraz);
            pstmt.setString(2, gazteleraz);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Baloreak pasatuz sortutako erregistro bat aldatzen du
     * @param id
     * @param euskaraz
     * @param gazteleraz 
     */
    public void terminoakAldatu(int id, String euskaraz, String gazteleraz) {
        String sql = "UPDATE Terminoak SET euskaraz = ? , "
                + "gazteleraz = ? "
                + "WHERE id = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, euskaraz);
            pstmt.setString(2, gazteleraz);
            pstmt.setInt(3, id);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * ID Balorearekin erregistro hori borratzen du
     * @param id 
     */
    public void terminoakBorratu(int id) {
        String sql = "DELETE FROM Terminoak WHERE id = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
