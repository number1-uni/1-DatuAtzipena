package dambi.pojoak.produktua;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "id" , "name", "listPrice" })
@XmlRootElement(name = "Produktua")
public class Produktua {

  private int id;
  private String name;
  private double list_price;

  public Produktua() {
  }

  public Produktua( int id, String name, double list_price) {
    this.id = id;
    this.name = name;
    this.list_price = list_price;
  }

  public int getId() {
    return id;
  }

  @XmlElement(name = "id")
  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  @XmlElement(name = "name")
  public void setName(String name) {
    this.name = name;
  }

  public double getListPrice() {
    return list_price;
  }

  @XmlElement(name = "list_price")
  public void setListPrice(double list_price) {
    this.list_price = list_price;
  }

  @Override
  public String toString() {
    return "Produktua [id=" + id + ", name=" + name + ", list_price=" + list_price + "]";
  }
}
