package dambi.pojoak;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "izena", "altuera", "probintzia" })
@XmlRootElement(name = "Produktua")
public class Produktua {

  int id = 0;
  private String izena;
  private double altuera;
  private String probintzia;

  public Produktua() {
  }

  public Produktua( String izena, int altuera, String probintzia) {
    
    this.izena = izena;
    this.altuera = altuera;
    this.probintzia = probintzia;
  }

  public int getId(){
    return id;
  }

  @XmlAttribute(name = "id")
    public void setId(int id) {
        this.id = id;
    }

  public String getIzena() {
    return izena;
  }

  @XmlElement(name = "Produktua")
  public void setIzena(String izena) {
    this.izena = izena;
  }

  public double getAltuera() {
    return altuera;
  }

  @XmlElement(name = "Altuera")
  public void setAltuera(double altuera) {
    this.altuera = altuera;
  }

  public String getProbintzia() {
    return probintzia;
  }

  @XmlElement(name = "Probintzia")
  public void setProbintzia(String probintzia) {
    this.probintzia = probintzia;
  }

  @Override
  public String toString() {
    return "Izena= " + izena + "; Altuera= " + altuera + "; Probintzia= " + probintzia + '\n';
  }
}
