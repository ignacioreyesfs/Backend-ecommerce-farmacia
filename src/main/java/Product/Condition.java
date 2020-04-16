package Product;

public enum Condition {
    NORMAL {
        @Override
        public double discountRate() {
            return 1;
        }
    },
    TWENTYPERCENT {
        @Override
        public double discountRate() {
            return 0.8;
        }
    };

    public abstract double discountRate();
}