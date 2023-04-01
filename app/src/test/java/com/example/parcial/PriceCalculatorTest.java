package com.example.parcial;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigInteger;

@RunWith(JUnit4.class)
public class PriceCalculatorTest extends TestCase {

    PriceCalculator priceCalculator1 = new PriceCalculator(0);
    PriceCalculator priceCalculator2 = new PriceCalculator(1);
    PriceCalculator priceCalculator3 = new PriceCalculator(5);
    PriceCalculator priceCalculator4 = new PriceCalculator(10);
    PriceCalculator priceCalculator5 = new PriceCalculator(15);
    PriceCalculator priceCalculator6 = new PriceCalculator(20);

    @Test
    public void testCalculateWhenTheValueIs0() throws InterruptedException {
        priceCalculator1.start();
        priceCalculator1.join();
        assertEquals(BigInteger.ONE, priceCalculator1.getResult());
    }

    @Test
    public void testCalculateWhenTheValueIs1() throws InterruptedException {
        priceCalculator2.start();
        priceCalculator2.join();
        assertEquals(BigInteger.ONE, priceCalculator2.getResult());
    }

    @Test
    public void testCalculateWhenTheValueIs5() throws InterruptedException {
        priceCalculator3.start();
        priceCalculator3.join();
        assertEquals(BigInteger.valueOf(120), priceCalculator3.getResult());
    }

    @Test
    public void testCalculateWhenTheValueIs10() throws InterruptedException {
        priceCalculator4.start();
        priceCalculator4.join();
        assertEquals(BigInteger.valueOf(3628800), priceCalculator4.getResult());
    }

    @Test
    public void testCalculateWhenTheValueIs15() throws InterruptedException {
        priceCalculator5.start();
        priceCalculator5.join();
        assertEquals(BigInteger.valueOf(1307674368000L), priceCalculator5.getResult());
    }

    @Test
    public void testCalculateWhenTheValueIs20() throws InterruptedException {
        priceCalculator6.start();
        priceCalculator6.join();
        assertEquals(BigInteger.valueOf(2432902008176640000L), priceCalculator6.getResult());
    }

}