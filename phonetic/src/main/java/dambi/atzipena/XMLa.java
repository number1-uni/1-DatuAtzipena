package dambi.atzipena;

import java.io.File;

import dambi.pojoak.produktua.*;
import dambi.pojoak.salmenta.Salmentak;
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

  public void irakurri(Salmentak salmentak) {
    try {
      File file = new File("src//data//" + strFileIn);
      JAXBContext jaxbContext = JAXBContext.newInstance(Salmentak.class);

      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      salmentak = (Salmentak) jaxbUnmarshaller.unmarshal(file);

    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(salmentak);
    System.out.println("Ondo irakurri da XMLa.");
  }

  public int idatzi(Produktuak produktuak) {
    int kopurua = 0;
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(Produktuak.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      jaxbMarshaller.marshal(produktuak, new File( "src//data//"+strFileOut));
      kopurua = produktuak.getProduktuak().size();
      System.out.println(strFileOut + " fitxategia ondo idatzi da.");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return kopurua;
  }

  public int idatzi2(Salmentak salmentak) {
    int kopurua = 0;
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(Salmentak.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      jaxbMarshaller.marshal(salmentak, new File( "src//data//"+strFileOut));
      kopurua = salmentak.getSalmentak().size();
      System.out.println(strFileOut + " fitxategia ondo idatzi da.");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return kopurua;
  }
}
