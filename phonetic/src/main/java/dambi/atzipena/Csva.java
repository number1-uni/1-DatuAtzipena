package dambi.atzipena;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import dambi.pojoak.produktua.*;
import dambi.pojoak.salmenta.*;

public class Csva {
  String strFileIn, strFileOut;

  public Csva(String strFileIn, String strFileOut) {
    this.strFileIn = strFileIn;
    this.strFileOut = strFileOut;
  }

  public Csva(String strFileIn) {
    this.strFileIn = strFileIn;
    this.strFileOut = strFileIn + "out.csv";
  }

  /**
   * Csv fitxategia irakurtzen du eta salmentak ArrayList-ean gordeko da. 
   * @param salmentak
   */
  public void irakurriSales(Salmentak salmentak) {

    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader("src//data//" + strFileIn));
      String line = br.readLine();
      int iterator = 0;
      while (line != null) {

        String[] zatiak = line.split(";");
        if (iterator != 0) {
          //int id, int product_id, String name, double price_unit, int qty_invoiced, double price_subtotal, double price_total, String write_date
          Salmenta salmenta = new Salmenta(
            Integer.parseInt(zatiak[0]), 
            Integer.parseInt(zatiak[1]), 
            zatiak[2],
            Float.parseFloat(zatiak[3]), 
            Integer.parseInt(zatiak[4]),
            Float.parseFloat(zatiak[5]),
            Float.parseFloat(zatiak[6]),
            zatiak[7]);

          salmentak.add(salmenta);
          line = br.readLine();
        } else {
          line = br.readLine();
          iterator++;
        }
      }
      br.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * produktuak ArrayList-a irakurtzen du eta produktuak csv fitxategian idatzen ditu.
   * @param produktuak
   */
  public int idatziProducts(Produktuak produktuak) {
    int kopurua = 0;

    try (PrintWriter outputStream = new PrintWriter(new FileWriter("src//data//" + strFileOut))) {
      for (Produktua m : produktuak.getProduktuak()) {
        if (kopurua == 0) {
          outputStream.println("PRODUKTUA;IZENA;PREZIOA");
        }
        kopurua++;
        outputStream.println(m.toString());
      }
      System.out.println(strFileOut + " fitxategia ondo idatzi da.");
    } catch (IOException e) {
      System.out.println(strFileOut + " fitxategiarekin arazoren bat egon da.");
    }
    return kopurua;
  }

  /**
   * salmentak ArrayList-a irakurtzen du eta salmentak csv fitxategian idatzen ditu.
   * @param salmentak
   */
  public int idatziSales(Salmentak salmentak) {
    int kopurua = 0;

    try (PrintWriter outputStream = new PrintWriter(new FileWriter("src//data//" + strFileOut))) {
      for (Salmenta m : salmentak.getSalmentak()) {
        if (kopurua == 0) {
          outputStream.println("SALMENTA");
        }
        kopurua++;
        outputStream.println(m.toStringCSV());
      }
      System.out.println(strFileOut + " fitxategia ondo idatzi da.");
    } catch (IOException e) {
      System.out.println(strFileOut + " fitxategiarekin arazoren bat egon da.");
    }
    return kopurua;
  }
}