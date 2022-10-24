package dambi.atzipena;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import dambi.pojoak.*;

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

  public Produktuak irakurri() {

    Produktuak produktuak = new Produktuak();
    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader("src\\data\\" + strFileIn));
      String line = br.readLine();
      int iterator = 0;
      while (line != null) {

        String[] zatiak = line.split(";");
        if (iterator != 0) {
          Produktua mendi = new Produktua(zatiak[0], Integer.parseInt(zatiak[1]), zatiak[2]);
          produktuak.add(mendi);
          line = br.readLine();
        } else {
          line = br.readLine();
          iterator++;
        }
      }
      br.close();
      System.out.println(produktuak);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return produktuak;
  }

  public int idatzi(Produktuak produktuak) {
    int mendiKopurua = 0;

    try (PrintWriter outputStream = new PrintWriter(new FileWriter("src\\data\\" + strFileOut))) {
      for (Produktua m : produktuak.getProduktuak()) {
        if (mendiKopurua == 0) {
          outputStream.println("PRODUKTUA;ALTUERA;PROBINTZIA");
        }
        mendiKopurua++;
        outputStream.println(m.getId()+ ";" + m.getIzena() + ";" + m.getAltuera() + ";" + m.getProbintzia());
      }
      System.out.println(strFileOut + " fitxategia ondo idatzi da.");
    } catch (IOException e) {
      System.out.println(strFileOut + " fitxategiarekin arazoren bat egon da.");
    }
    return mendiKopurua;
  }
}