package Order.Exceptions;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(){
        super("Cannot add more units of the product because there is no more stock");
    }
}
