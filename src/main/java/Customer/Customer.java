package Customer;

import Order.Order;
import StockNotification.Exceptions.ClientNotNotifiedException;
import StockNotification.Notification.NotifierType;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    Integer id;
    @Column(name="COSTUMER_NAME")
    private String name;
    private String email;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="COSTUMER_ID")
    private List<Order> orders;
    @Enumerated(EnumType.STRING)
    private NotifierType notifierType;

    public Customer(String name, NotifierType notifierType, String email){
        this.name = name;
        this.notifierType = notifierType;
        this.email = email;
    }

    public Customer(){}

    public void notifyNewProductStock(String productName){
        try{
            notifierType.getNotifier().stockNotification(this, productName);
        }catch (Exception e){
            throw new ClientNotNotifiedException();
        }
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public String getEmail(){
        return email;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    private void setNotifierType(NotifierType notifierType) {
        this.notifierType = notifierType;
    }
}
