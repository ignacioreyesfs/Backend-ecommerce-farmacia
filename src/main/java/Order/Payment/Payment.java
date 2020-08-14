package Order.Payment;

public interface Payment {
    void pay(double value, int cardNumber, int expire, int cvv);
}
