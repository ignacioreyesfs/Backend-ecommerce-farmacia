package Order;

import Order.Exceptions.InsufficientStockException;
import Order.Exceptions.ZeroUnitsException;
import Product.Product;

import javax.persistence.*;

@Entity
public class ItemProduct {

    @Id
    @GeneratedValue
    Integer id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
    private int units;
    private String remarks;
    // persisted for historical price of the order
    private double price;

    public ItemProduct(Product product, int units){
        this.product = product;
        this.units = units;
        this.price = product.getPrice() * units;
    }

    public ItemProduct(){};

    public void incrementUnits(int unitsToAdd){
        if(this.insufficientStock(unitsToAdd))
            throw new InsufficientStockException();

        units += unitsToAdd;
        price = product.getPrice() * units;
    }

    private boolean insufficientStock(int unitsToAdd){
        return product.getStock() < units + unitsToAdd;
    }

    public void decrementUnit(){
        if(units <= 1)
            throw new ZeroUnitsException();

        units--;
        price -= product.getPrice();
    }

    public double getPrice(){
        return price;
    }

    public Product getProduct() {
        return product;
    }

    public int getUnits() {
        return units;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    private void setProduct(Product product) {
        this.product = product;
    }

    private void setUnits(int units) {
        this.units = units;
    }

    private void setPrice(double price) {
        this.price = price;
    }
}
