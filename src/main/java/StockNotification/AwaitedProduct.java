package StockNotification;

import Product.Product;
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
    @ElementCollection
    @CollectionTable(name="AWAITED_PRODUCT_EMAIL",
            joinColumns = {@JoinColumn(name="AWAITED_PRODUCT_ID", referencedColumnName = "id")})
    @Column(name="EMAIL")
    private Set<String> clientsEmails = new HashSet<>();

    public AwaitedProduct(){}

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

    private void setProduct(Product product) {
        this.product = product;
    }

    public Integer getId(){
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    private void setClientsEmails(Set<String> clientsEmails) {
        this.clientsEmails = clientsEmails;
    }
}
