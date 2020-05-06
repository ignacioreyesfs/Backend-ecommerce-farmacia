package Order;

import Order.Exceptions.NotPaymentSettedException;
import Order.Payment.Payment;
import Product.Product;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Order {

    String street;
    int streetHeight;
    double orderPrice;
    String generalRemarks;
    Set<ItemProduct> itemProducts = new HashSet<ItemProduct>();

    public Order(String street, int streetHeight, String generalRemarks){
        this.street = street;
        this.streetHeight = streetHeight;
        this.generalRemarks = generalRemarks;
    }

    public void addItemProduct(ItemProduct itemProduct){
        Optional<ItemProduct> optionalSearchedItem = searchProduct(itemProduct.getProduct());

        if(optionalSearchedItem.isPresent()){
            optionalSearchedItem.get().incrementUnits(itemProduct.getUnits());
        }else{
            itemProducts.add(itemProduct);
        }
    }

    private Optional<ItemProduct> searchProduct(Product product){
        return itemProducts.stream().filter(prod -> prod.getProduct() == product)
                .findFirst();
    }

    public double price(){
        return itemProducts.stream().map(product -> product.getProduct().calculatePrice()).reduce(0.0, Double::sum);
    }

    public String getStreet() {
        return street;
    }

    public int getStreetHeight() {
        return streetHeight;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public String getGeneralRemarks() {
        return generalRemarks;
    }

    public Set<ItemProduct> getItemProducts() {
        return itemProducts;
    }
}
