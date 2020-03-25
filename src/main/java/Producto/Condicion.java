package Producto;

public enum Condicion {
    NORMAL {
        @Override
        public double porcentajeDescuento() {
            return 1;
        }
    },
    VEINTEPORCIENTO {
        @Override
        public double porcentajeDescuento() {
            return 0.8;
        }
    };

    public abstract double porcentajeDescuento();
}