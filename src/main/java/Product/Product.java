package Product;

import Customer.Customer;
import Recommendation.RecommendedWeather;
import StockNotification.Exceptions.ClientNotNotifiedException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name="PRODUCT_NAME")
    private String name;
    private double price;
    @Enumerated(EnumType.STRING)
    private Manufacturer manufacturer;
    @Enumerated(EnumType.STRING)
    @Column(name="PRODUCT_CONDITION")
    private Condition condition = Condition.NORMAL;
    @Enumerated(EnumType.STRING)
    private RecommendedWeather recommendedWeather;
    private int stock;
    private double dose;
    @Enumerated(EnumType.STRING)
    private Unit unit;
    @ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="AWAITED_PRODUCT_CLIENT", joinColumns = @JoinColumn(name="AWAITED_PRODUCT_ID"),
            inverseJoinColumns = @JoinColumn(name="CLIENT_ID"))
    private Set<Customer> customersWaitingStock = new HashSet<>();

    public Product(){}

    public Product(String name, Unit unit, double dose, double price, Manufacturer manufacturer, RecommendedWeather recommendedWeather) {
        this.name = name;
        this.unit = unit;
        this.dose = dose;
        this.price = price;
        this.manufacturer = manufacturer;
        this.recommendedWeather = recommendedWeather;
    }

    public double getPrice() {
        return price * condition.discountRate();
    }

    public double getPriceWithoutDiscount(){
        return price;
    }

    public void increaseStock(int amount){
        stock += amount;
    }

    public void decreaseStock(int amount){
        stock -= amount;
    }

    public void notifyCustomersStock(){
        customersWaitingStock.forEach(customer -> this.notifyCustomerStock(customer));
    }

    private void notifyCustomerStock(Customer customer){
        try{
            customer.notifyNewProductStock(name);
            customersWaitingStock.remove(customer);
        }catch (ClientNotNotifiedException e){
            // TODO: if notifier is offline (check exception), retry later (schedule a job).
        }
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

    public RecommendedWeather getWeather() {
        return this.recommendedWeather;
    }

    public Condition getCondition(){
        return this.condition;
    }

    public Integer getId(){
        return this.id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    private void setRecommendedWeather(RecommendedWeather recommendedWeather) {
        this.recommendedWeather = recommendedWeather;
    }

    private void setStock(int stock) {
        this.stock = stock;
    }

    private void setDose(double dose) {
        this.dose = dose;
    }

    private void setUnit(Unit unit) {
        this.unit = unit;
    }

    private void setCustomersWaitingStock(Set<Customer> customersWaitingStock) {
        this.customersWaitingStock = customersWaitingStock;
    }
}