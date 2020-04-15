package APIUtility.Exceptions;

public class ParseJsonException extends RuntimeException {
    public ParseJsonException(){
        super("Problem parsing in APIUtility - getJSONObjectResponse");
    }
}
