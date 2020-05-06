package Order.Payment;

import Order.Order;

public enum Payment {
    CREDITCARD{
        @Override
        public void pay(double value, int cardNumber, int expire, int cvv){
            // not implemented, value get from order.
        }
    },
    DEBITCARD{
        @Override
        public void pay(double value, int cardNumber, int expire, int cvv){
            // not implemented, value get from order.
        }
    };
    public abstract void pay(double value, int cardNumber, int expire, int cvv);
}
