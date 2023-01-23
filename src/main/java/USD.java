package Coins;

public class USD extends Coin {

    private final static double value = 3.52;

    // returns the USD to ILS conversion rate
    @Override
    public double getValue() {
        return value;
    }

    // gets an amount in USD and returns the matching amount in ILS.
    @Override
    public double calculate(double amount) {
        return amount * getValue();
    }
}

