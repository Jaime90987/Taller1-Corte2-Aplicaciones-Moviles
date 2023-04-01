package com.example.parcial;

import java.math.BigInteger;

public class PriceCalculator extends Thread {
    private final int value;
    private BigInteger result;

    public PriceCalculator(int value) {
        this.value = value;
    }

    @Override
    public void run() {
        result = calculate(BigInteger.valueOf(value));
    }

    public BigInteger getResult() {
        return result;
    }

    /*private BigInteger calculate(int a) {
        if (a > 1)
            return a * calculate(a - 1);

        return BigInteger.ONE;
    }*/

    private BigInteger calculate(BigInteger a) {
        if (a.compareTo(BigInteger.ONE) > 0) {
            return a.multiply(calculate(a.subtract(BigInteger.ONE)));
        }
        return BigInteger.ONE;
    }
}
