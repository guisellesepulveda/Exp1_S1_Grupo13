
public final class DiscountManager {

    private static DiscountManager instance;

    private DiscountManager() {
    }

    public static DiscountManager getInstance() {
        if (instance == null) {
            instance = new DiscountManager();
        }
        return instance;
    }

    public double aplicarDescuento(double amount, double percent) {
        if (amount <= 0)
            return 0;
        if (percent <= 0)
            return amount;
        double result = amount * (1 - (percent / 100.0));
        return Math.max(0, roundTwo(result));
    }

    public double aplicarDescuentoFijo(double amount, double fixed) {
        if (amount <= 0)
            return 0;
        if (fixed <= 0)
            return amount;
        double result = amount - fixed;
        return Math.max(0, roundTwo(result));
    }

    private double roundTwo(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
