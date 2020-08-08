package Product.Offer;

public class NotInDatabaseException extends RuntimeException{
    public NotInDatabaseException(){
        super("The element searched is not in the database");
    }
}
