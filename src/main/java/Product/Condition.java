package Product;

public enum Condition {
    NORMAL {
        @Override
        public double getDiscountRate() {
            return 1;
        }
    },
    TWENTYPERCENT {
        @Override
        public double getDiscountRate() {
            return 0.8;
        }
    };

    public abstract double getDiscountRate();
}