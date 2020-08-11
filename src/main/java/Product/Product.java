package Product;

import Recommendation.Weather;
import StockNotification.StockNotificator;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name="product_name")
    private String name;
    private double price;
    @Enumerated(EnumType.STRING)
    private Manufacturer manufacturer;
    @Enumerated(EnumType.STRING)
    @Column(name="product_condition")
    private Condition condition = Condition.NORMAL;
    @Enumerated(EnumType.STRING)
    private Weather weather;
    private int stock;
    private double dose;
    @Enumerated(EnumType.STRING)
    private Unit unit;

    public Product(){}

    public Product(String name, Unit unit, double dose, double price, Manufacturer manufacturer, Weather weather) {
        this.name = name;
        this.unit = unit;
        this.dose = dose;
        this.price = price;
        this.manufacturer = manufacturer;
        this.weather = weather;
    }

    public double calculatePrice() {
        return price * condition.discountRate();
    }

    public double priceWithoutDiscount(){
        return price;
    }

    public void increaseStock(int amount){
        stock += amount;
    }

    public void decreaseStock(int amount){
        stock -= amount;
    }

    public int getStock() {
        return stock;
    }

    public String getName(){
        return name;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Weather getWeather() {
        return this.weather;
    }

    public Condition getCondition(){
        return this.condition;
    }

    public Integer getId(){
        return this.id;
    }
}