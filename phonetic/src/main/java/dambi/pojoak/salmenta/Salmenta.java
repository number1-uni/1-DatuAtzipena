package dambi.pojoak.salmenta;



import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "id","product_id", "name", "price_unit", "qty_invoiced", "price_subtotal" ,"price_total", "write_date" })
@XmlRootElement(name = "Salmenta")
public class Salmenta {

  private int id;
  private int product_id;
  private String name;
  private double price_unit;
  private int qty_invoiced;
  private double price_subtotal;
  private double price_total;
  private String write_date;


  public Salmenta() {
  }
  
  public Salmenta(int id, int product_id, String name, double price_unit, int qty_invoiced, double price_subtotal,
      double price_total, String write_date) {
    this.id = id;
    this.product_id = product_id;
    this.name = name;
    this.price_unit = price_unit;
    this.qty_invoiced = qty_invoiced;
    this.price_subtotal = price_subtotal;
    this.price_total = price_total;
    this.write_date = write_date;
  }


  public int getId() {
    return id;
  }

  @XmlElement(name = "id")
  public void setId(int id) {
    this.id = id;
  }

  public int getProduct_id() {
    return product_id;
  }

  @XmlElement(name = "product_id")
  public void setProduct_id(int product_id) {
    this.product_id = product_id;
  }

  public String getName() {
    return name;
  }

  @XmlElement(name = "name")
  public void setName(String name) {
    this.name = name;
  }

  public double getPrice_unit() {
    return price_unit;
  }

  @XmlElement(name = "price_unit")
  public void setPrice_unit(double price_unit) {
    this.price_unit = price_unit;
  }

  public int getQty_invoiced() {
    return qty_invoiced;
  }

  @XmlElement(name = "qty_invoiced")
  public void setQty_invoiced(int qty_invoiced) {
    this.qty_invoiced = qty_invoiced;
  }

  public double getPrice_subtotal() {
    return price_subtotal;
  }

  @XmlElement(name = "price_subtotal")
  public void setPrice_subtotal(double price_subtotal) {
    this.price_subtotal = price_subtotal;
  }

  public double getPrice_total() {
    return price_total;
  }

  @XmlElement(name = "price_total")
  public void setPrice_total(double price_total) {
    this.price_total = price_total;
  }

  public String getWrite_date() {
    return write_date;
  }

  @XmlElement(name = "write_date")
  public void setWrite_date(String write_date) {
    this.write_date = write_date;
  }

  @Override
  public String toString() {
    return "Salmenta [id=" + id + ", product_id=" + product_id + ", name=" + name + ", price_unit=" + price_unit
        + ", qty=" + qty_invoiced + ", price_subtotal=" + price_subtotal + ", price_total=" + price_total
        + ", date=" + write_date + "]\n";
  }

  public String toStringCSV() {
    return id + ";" + product_id + ";" + name + ";" + price_unit + ";" + qty_invoiced + ";" +  price_subtotal + ";" +  price_total + ";" +  write_date ;
  }

}
