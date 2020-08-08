package Product;

import Recommendation.Weather;
import StockNotification.StockNotificator;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private double price;
    @Enumerated(EnumType.STRING)
    private Manufacturer manufacturer;
    @Enumerated(EnumType.STRING)
    private Condition condition = Condition.NORMAL;
    @Enumerated(EnumType.STRING)
    private Weather weather;
    private int stock;

    public Product(){}

    public Product(String name, double price, Manufacturer manufacturer, Weather weather) {
        this.name = name;
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
        new StockNotificator().notifyCustomers(this);
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