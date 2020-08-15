package Order.Exceptions;

public class ZeroUnitsException extends RuntimeException {
    public ZeroUnitsException(){
        super("An ItemProduct cannot has zero units");
    }
}
