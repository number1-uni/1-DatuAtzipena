package dambi.atzipena;

import java.io.FileOutputStream;
import java.io.FileReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonWriter;

import dambi.pojoak.*;

public class Jsona { /*
  String strFileIn, strFileOut;

  public Jsona(String strFileIn, String strFileOut) {
    this.strFileIn = strFileIn;
    this.strFileOut = strFileOut;
  }

  public Jsona(String strFileIn) {
    this.strFileIn = strFileIn;
    this.strFileOut = strFileIn + ".csv";
  }

  public Produktuak irakurri() {
   
    Produktuak produktuak = null;
    try {
      JsonReader reader = Json.createReader(new FileReader("src\\data\\" + strFileIn));
      JsonStructure jsonst = reader.read(); // json structure estruktura sortzen da

      JsonArray jsonstArray = jsonst.asJsonArray(); // json structure arrayra bihurtzen da
      produktuak = new Produktuak();
      for (int i = 0; i < jsonstArray.size(); i++) {
        Produktua produktua = new Produktua();
        produktua.setId(jsonstArray.getJsonObject(i).getInt("id"));
        produktua.setIzena(jsonstArray.getJsonObject(i).getString("produktua"));
        produktua.setAltuera(jsonstArray.getJsonObject(i).getInt("altuera"));
        produktua.setProbintzia(jsonstArray.getJsonObject(i).getString("probintzia"));
        produktuak.add(produktua);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return produktuak;
  }

  public int idatzi(Produktuak produktuak) {
    int mendiKopurua = 0;
    JsonArray model = null;
    JsonArrayBuilder jab = Json.createArrayBuilder();

    for (Produktua produktua : produktuak.getProduktuak()) {
      jab.add(Json.createObjectBuilder()
          .add("id", produktua.getId())
          .add("izena", produktua.getIzena())
          .add("altuera", produktua.getAltuera())
          .add("probintzia", produktua.getProbintzia())
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
  }*/
}
