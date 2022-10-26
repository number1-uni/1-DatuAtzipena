package dambi.pojoak.erosketa;

import java.time.LocalDateTime;

import dambi.pojoak.produktua.Produktua;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "id","product_id", "name", "price_unit", "product_qty", "price_subtotal" ,"price_total", "date_planned" })
@XmlRootElement(name = "Erosketa")
public class Erosketa {

  private int id;
  private int product_id;
  private String name;
  private double price_unit;
  private int product_qty;
  private double price_subtotal;
  private double price_total;
  private LocalDateTime date_planned;

  public Erosketa() {
  }

  public Erosketa(int id, int product_id, String name, double price_unit, int product_qty, double price_subtotal,
      double price_total, LocalDateTime date_planned) {
    this.id = id;
    this.product_id = product_id;
    this.name = name;
    this.price_unit = price_unit;
    this.product_qty = product_qty;
    this.price_subtotal = price_subtotal;
    this.price_total = price_total;
    this.date_planned = date_planned;
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

  public int getProduct_qty() {
    return product_qty;
  }

  @XmlElement(name = "product_qty")
  public void setProduct_qty(int product_qty) {
    this.product_qty = product_qty;
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

  public LocalDateTime getDate_planned() {
    return date_planned;
  }

  @XmlElement(name = "date_planned")
  public void setDate_planned(LocalDateTime date_planned) {
    this.date_planned = date_planned;
  }

  @Override
  public String toString() {
    return id + ".Erosketa [product_id=" + product_id + ", name=" + name + ", price_unit=" + price_unit
        + ", product_qty=" + product_qty + ", price_subtotal=" + price_subtotal + ", price_total=" + price_total
        + ", date_planned=" + date_planned + "]\n";
  }

}
