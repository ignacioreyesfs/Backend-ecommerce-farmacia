package Utilities.APIUtility.Exceptions;

public class ParseJsonException extends RuntimeException {
    public ParseJsonException(){
        super("Problem parsing in Utilities.APIUtility - getJSONObjectResponse");
    }
}
