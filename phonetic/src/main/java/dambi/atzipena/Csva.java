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


  public int idatzi(Produktuak produktuak) {
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

  public int idatzi2(Salmentak salmentak) {
    int kopurua = 0;

    try (PrintWriter outputStream = new PrintWriter(new FileWriter("src//data//" + strFileOut))) {
      for (Salmenta m : salmentak.getSalmentak()) {
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
}