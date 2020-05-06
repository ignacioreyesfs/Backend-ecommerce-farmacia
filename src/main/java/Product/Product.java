package Product;

import Utilities.MailSender.GmailSender;
import Product.Exceptions.CannotNotifyStock;
import Recommendation.Weather;

import java.util.HashSet;
import java.util.Set;

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
    private Set<String> stockSubscribers = new HashSet<>();

    public double calculatePrice() {
        return price * condition.discountRate();
    }

    public void increaseStock(int amount){
        this.stock += amount;
        this.notifyCustomers();
    }

    private void notifyCustomers(){
        if(!stockSubscribers.isEmpty()){
            try {
                new GmailSender().newStockEmailNotification(this.stockSubscribers, this.name);
            } catch (Exception e) {
                // TODO: retry
                e.printStackTrace();
                throw new CannotNotifyStock();
            }
        }
    }

    public void decreaseStock(int amount){
        this.stock -= amount;
    }

    public void addStockSubscriber(String email){
        this.stockSubscribers.add(email);
    }

    public void deleteSubscribers(){
        this.stockSubscribers.clear();
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