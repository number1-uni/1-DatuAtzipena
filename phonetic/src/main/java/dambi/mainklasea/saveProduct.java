package dambi.mainklasea;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import dambi.atzipena.*;

public class saveProduct {

    public static void main(String[] args) {
        String izena, deskripzioa;
        float prezioa;
        boolean check = false;
        Scanner in = new Scanner(System.in);
        int id = findId() + 1;
        while (!check){ 
            try{        
                System.out.println("PRODUKTUA SORTZEKO MENUA");
                System.out.print("Izena: ");
                izena = in.nextLine();
                System.out.print("Deskripzioa: ");
                deskripzioa = in.nextLine();
                System.out.print("Prezioa: ");
                prezioa = in.nextFloat();
                insertProduct(id, izena, deskripzioa, prezioa);
                check = true;
                System.out.println("Produktua ondo sortu da.");
            } catch (Exception e) {
                System.out.println("Sartu duzun balioak ez dira zuzenak. Saiatu berriz.");
                in.nextLine();
            }
        }
        in.close();
    }

    public static void insertProduct(int id, String izena, String deskripzioa, float prezioa) {

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new java.util.Date());
        ;

        String sql = "INSERT INTO public.product_template VALUES( " + id + ", NULL,'" + izena + "',1,'" + deskripzioa
                + "',NULL,NULL,'product','product',1," + prezioa
                + ",10,10,true,true,1,1,NULL,true,NULL,NULL,false,false,0,2,'" + timeStamp + "',2,'" + timeStamp
                + "',0,'none',NULL,NULL,NULL,'receive','no-message',NULL,'false',0,'none','no','order',false);";

        try {
            Connection conn = db.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
        }
    }

    public static int findId() {
        String sql = "SELECT id FROM public.product_template ORDER BY id DESC LIMIT 1";
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