package dambi.atzipena;

import java.io.FileOutputStream;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonWriter;
import java.io.FileReader;


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

  /**
   * Json fitxategia irakurtzen du eta salmentak ArrayList-ean gordeko da. 
   * @param salmentak
   */
  public void irakurriSales(Salmentak salmentak) {
    /* READ JSON FILE */
    try {
      JsonReader reader = Json.createReader(new FileReader("src\\data\\" + strFileIn));
      JsonStructure jsonst = reader.read(); // json structure estruktura sortzen da

      JsonArray jsonstArray = jsonst.asJsonArray(); // json structure arrayra bihurtzen da
      for (int i = 0; i < jsonstArray.size(); i++) {
        Salmenta salmenta = new Salmenta();

        salmenta.setId(jsonstArray.getJsonObject(i).getInt("id"));
        salmenta.setProduct_id(jsonstArray.getJsonObject(i).getInt("product_id"));
        salmenta.setName(jsonstArray.getJsonObject(i).getString("name"));
        salmenta.setPrice_unit(jsonstArray.getJsonObject(i).getInt("price_unit"));
        salmenta.setQty_invoiced(jsonstArray.getJsonObject(i).getInt("qty_invoiced"));
        salmenta.setPrice_subtotal(jsonstArray.getJsonObject(i).getInt("price_subtotal"));
        salmenta.setPrice_total(jsonstArray.getJsonObject(i).getInt("price_total"));
        salmenta.setWrite_date(jsonstArray.getJsonObject(i).getString("write_date"));
        salmentak.add(salmenta);

      }
      System.out.println(salmentak);
      System.out.println("Ondo irakurri da JSONa.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * produktuak ArrayList-a irakurtzen du eta produktuak json fitxategian idatzen ditu.
   * @param produktuak
   */
  public int idatziProducts(Produktuak produktuak) {
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

  /**
   * salmentak ArrayList-a irakurtzen du eta salmentak json fitxategian idatzen ditu.
   * @param salmentak
   */
  public int idatziSales(Salmentak salmentak) {
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
