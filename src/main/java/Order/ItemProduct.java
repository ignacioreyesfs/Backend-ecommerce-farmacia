package Order;

import Order.Exceptions.NotPaymentSettedException;
import Order.Payment.Payment;
import Product.Product;

public class ItemProduct {

    public ItemProduct(Product product, int units, String remarks){
        this.product = product;
        this.units = units;
        this.remarks = remarks;
    }
    private Product product;
    private int units;
    private String remarks;

    public void incrementUnits(int units){
        this.units += units;
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
}
