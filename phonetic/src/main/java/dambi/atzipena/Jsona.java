package dambi.atzipena;

import java.io.FileOutputStream;
import java.io.FileReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonWriter;

import dambi.pojoak.produktua.*;
import dambi.pojoak.salmenta.*;

public class Jsona { 
  String strFileIn, strFileOut;

  public Jsona(String strFileIn, String strFileOut) {
    this.strFileIn = strFileIn;
    this.strFileOut = strFileOut;
  }

  public Jsona(String strFileIn) {
    this.strFileIn = strFileIn;
    this.strFileOut = strFileIn + ".csv";
  }

  public int idatzi(Produktuak produktuak) {
    int mendiKopurua = 0;
    JsonArray model = null;
    JsonArrayBuilder jab = Json.createArrayBuilder();

    for (Produktua produktua : produktuak.getProduktuak()) {
      jab.add(Json.createObjectBuilder()
          .add("id", produktua.getId())
          .add("name", produktua.getName())
          .add("list_price", produktua.getListPrice())
          .build());
      mendiKopurua++;
    }
    model = jab.build();
    try (JsonWriter jsonWriter = Json.createWriter(new FileOutputStream("src\\data\\" + strFileOut))) {
      jsonWriter.writeArray(model);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(strFileOut + " fitxategia ondo idatzi da.");
    return mendiKopurua;
  }

  public int idatzi2(Salmentak salmentak) {
    int mendiKopurua = 0;
    JsonArray model = null;
    JsonArrayBuilder jab = Json.createArrayBuilder();

    for (Salmenta salmenta : salmentak.getSalmentak()) {
      jab.add(Json.createObjectBuilder()
          .add("id", salmenta.getId())
          .add("product_id", salmenta.getProduct_id())
          .add("name", salmenta.getName())
          .add("price_unit", salmenta.getPrice_unit())
          .add("qty_invoiced", salmenta.getQty_invoiced())
          .add("price_subtotal", salmenta.getPrice_subtotal())
          .add("price_total", salmenta.getPrice_total())
          .add("write_date", salmenta.getWrite_date())
          .build());
      mendiKopurua++;
    }
    model = jab.build();
    try (JsonWriter jsonWriter = Json.createWriter(new FileOutputStream("src\\data\\" + strFileOut))) {
      jsonWriter.writeArray(model);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(strFileOut + " fitxategia ondo idatzi da.");
    return mendiKopurua;
  }
}
