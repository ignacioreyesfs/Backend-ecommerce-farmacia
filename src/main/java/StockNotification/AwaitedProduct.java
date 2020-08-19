package StockNotification;

import Customer.Customer;
import Product.Product;
import StockNotification.Exceptions.ClientNotNotifiedException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AwaitedProduct {
    @Id
    @GeneratedValue
    private Integer id;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="PRODUCT_ID")
    private Product product;
    @ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="AWAITED_PRODUCT_CLIENT", joinColumns = @JoinColumn(name="AWAITED_PRODUCT_ID"),
            inverseJoinColumns = @JoinColumn(name="CLIENT_ID"))
    private Set<Customer> customers = new HashSet<>();

    public AwaitedProduct(){}

    public AwaitedProduct(Product product){
        this.product = product;
    }

    public void notifyCustomers(){
        customers.forEach(customer -> this.notifyCustomer(customer));
    }

    private void notifyCustomer(Customer customer){
        try{
            customer.notifyNewProductStock(product.getName());
            customers.remove(customer);
        }catch (ClientNotNotifiedException e){
            // TODO: if notifier is offline (check exception), retry later (schedule a job).
        }
    }

    public Product getProduct() {
        return product;
    }

    private void setProduct(Product product) {
        this.product = product;
    }

    public Integer getId(){
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    private void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}
