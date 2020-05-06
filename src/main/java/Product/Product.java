package Product;

import StockNotification.StockNotificator;
import Recommendation.Weather;

public class Product {

    public Product(String name, double price, Manufacturer manufacturer, Condition condition, Weather weather) {
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.condition = condition;
        this.weather = weather;
    }

    private String name;
    private double price;
    private Manufacturer manufacturer;
    private Condition condition;
    private Weather weather;
    private int stock;

    public double calculatePrice() {
        return price * condition.discountRate();
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
}