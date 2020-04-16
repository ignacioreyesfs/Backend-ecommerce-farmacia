package Product;

import Recommendation.Weather;

public class Product {

    public Product(String name, double price, Manufacturer manufacturer, Condition condition, Weather weather) {
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.condition = condition;
        this.weather = weather;
    }

    String name;
    double price;
    Manufacturer manufacturer;
    Condition condition;
    Weather weather;
    int stock;

    public double calculatePrice() {
        return price * condition.discountRate();
    }

    public void increaseStock(int cantidad){
        this.stock += cantidad;
    }

    public void decreaseStock(int cantidad){
        this.stock -= cantidad;
    }

    public int getStock() {
        return stock;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Weather getWeather() {
        return weather;
    }
}