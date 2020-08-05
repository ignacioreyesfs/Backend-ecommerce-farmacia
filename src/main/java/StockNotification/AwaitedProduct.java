package StockNotification;

import Product.Product;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AwaitedProduct {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Column(name="product_id")
    private Product product;
    @ElementCollection
    @CollectionTable(name="awaitedProduct_emails",
            joinColumns = {@JoinColumn(name="awaitedProduct_id", referencedColumnName = "id")})
    @Column(name="email")
    private Set<String> clientsEmails = new HashSet<>();

    public AwaitedProduct(Product product){
        this.product = product;
    }

    public void addClientEmail(String email){
        clientsEmails.add(email);
    }

    public void cleanClientEmails(){
        clientsEmails.clear();
    }

    public Set<String> getClientsEmails(){
        return clientsEmails;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getId(){
        return id;
    }
}
