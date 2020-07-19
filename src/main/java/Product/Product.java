package Product;

import StockNotification.StockNotificator;
import Recommendation.Weather;

public class Product {

    private String name;
    private double price;
    private Manufacturer manufacturer;
    private Condition condition = Condition.NORMAL;
    private Weather weather;
    private int stock;

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
}