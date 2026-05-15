package org.skypro.skyshop.product;

public class FixPriceProduct extends Product {
    private static final double FIX_PRICE = 99.99;

    public FixPriceProduct(String name) {
        super(name);
    }

    @Override
    public double getPrice() {
        return FIX_PRICE;
    }
}
