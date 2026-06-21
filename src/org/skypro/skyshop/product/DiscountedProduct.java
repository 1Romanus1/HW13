package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {
    private double basePrice;
    private int discountPercent;

    public DiscountedProduct(String name, double basePrice, int discountPercent) {
        super(name);

        if (basePrice <= 0) {
            throw new IllegalArgumentException("Базовая цена должна быть строго больше 0. Переданное значение: " + basePrice);
        }

        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Процент скидки должен быть в диапазоне от 0 до 100 включительно. Переданное значение: " + discountPercent);
        }
        this.basePrice = basePrice;
        this.discountPercent = discountPercent;
    }

    @Override
   public double getPrice() {
        double discountFactor = discountPercent / 100.0;
        return basePrice * (1 - discountFactor);
    }

    @Override
    public boolean isSpecial() {
        return true; // Все товары со скидкой считаются специальными
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f (%d%%)", name, getPrice(), discountPercent);
    }
}
//