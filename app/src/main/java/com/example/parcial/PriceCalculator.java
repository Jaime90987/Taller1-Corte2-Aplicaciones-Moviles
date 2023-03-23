package com.example.parcial;

public class PriceCalculator extends Thread {
    private final int value;
    private int result;

    public PriceCalculator(int value) {
        this.value = value;
    }

    @Override
    public void run() {
        result = calculate(value);
    }

    public int getResult() {
        return result;
    }

    private int calculate(int a) {
        if (a > 1)
            return a * calculate(a - 1);

        return 1;
    }
}
