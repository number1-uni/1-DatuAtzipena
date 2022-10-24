package dambi.atzipena;

import java.io.File;

import dambi.pojoak.Produktuak;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class XMLa {
  String strFileIn, strFileOut;

  public XMLa(String strFileIn, String strFileOut) {
    this.strFileIn = strFileIn;
    this.strFileOut = strFileOut;
  }

  public XMLa(String strFileIn) {
    this.strFileIn = strFileIn;
    this.strFileOut = strFileIn + ".csv";
  }

  public Produktuak irakurri() {
    Produktuak mendiIrakurriak = null;
    try {
      File file = new File("src\\data\\" + strFileIn);
      JAXBContext jaxbContext = JAXBContext.newInstance(Produktuak.class);

      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      mendiIrakurriak = (Produktuak) jaxbUnmarshaller.unmarshal(file);

    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(mendiIrakurriak);
    System.out.println("Ondo irakurri da XMLa.");
    return mendiIrakurriak;
  }

  public int idatzi(Produktuak produktuak) {
    int mendiKopurua = 0;
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(Produktuak.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      jaxbMarshaller.marshal(produktuak, new File("src\\data\\" + strFileOut));
      mendiKopurua = produktuak.getProduktuak().size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(strFileOut + " fitxategia ondo idatzi da.");
    return mendiKopurua;
  }
}
