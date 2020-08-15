package Order;

import Product.Product;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity(name="Order_table")
public class Order {

    @Id
    @GeneratedValue
    Integer id;
    String street;
    int streetHeight;
    String generalRemarks;
    @OneToMany(fetch= FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "order_id")
    Set<ItemProduct> itemProducts = new HashSet<ItemProduct>();

    public Order(String street, int streetHeight, String generalRemarks){
        this.street = street;
        this.streetHeight = streetHeight;
        this.generalRemarks = generalRemarks;
    }

    public Order(){};

    public void addItemProduct(ItemProduct itemProduct){
        Optional<ItemProduct> optionalSearchedItem = this.searchProduct(itemProduct.getProduct());

        if(optionalSearchedItem.isPresent()){
            optionalSearchedItem.get().incrementUnits(itemProduct.getUnits());
        }else{
            itemProducts.add(itemProduct);
        }
    }

    public void discardItemProduct(ItemProduct itemProduct){
        Optional<ItemProduct> optionalSearchedItem = this.searchProduct(itemProduct.getProduct());

        if(optionalSearchedItem.isPresent()){
            itemProducts.remove(optionalSearchedItem.get());
        }
    }

    private Optional<ItemProduct> searchProduct(Product product){
        return itemProducts.stream().filter(itemProduct -> itemProduct.getProduct() == product)
                .findFirst();
    }

    public double orderPrice(){
        return itemProducts.stream().map(itemProduct -> itemProduct.getPrice()).reduce(0.0, Double::sum);
    }

    public String getStreet() {
        return street;
    }

    public int getStreetHeight() {
        return streetHeight;
    }

    public String getGeneralRemarks() {
        return generalRemarks;
    }

    public Set<ItemProduct> getItemProducts() {
        return itemProducts;
    }

    private void setStreet(String street) {
        this.street = street;
    }

    private void setStreetHeight(int streetHeight) {
        this.streetHeight = streetHeight;
    }

    private void setGeneralRemarks(String generalRemarks) {
        this.generalRemarks = generalRemarks;
    }

    private void setItemProducts(Set<ItemProduct> itemProducts) {
        this.itemProducts = itemProducts;
    }
}
