package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private static final int MAX_PRODUCTS = 5;
    private Product[] products = new Product[MAX_PRODUCTS];
    private int count = 0;

    public void addProduct(Product product) {
        if (count < MAX_PRODUCTS) {
            products[count] = product;
            count++;
            System.out.println("Товар '" + product.getName() + "' добавлен в корзину");
        } else {
            System.out.println("Корзина заполнена! Товар '" + product.getName() + "' не добавлен");
        }
    }

   public void print() {
        if (count == 0) {
            System.out.println("Корзина пуста");
            return;
        }
        System.out.println("Содержимое корзины:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + products[i].getName() + " - " + products[i].getPrice() + " руб.");
        }
    }

     public double totalCost() {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += products[i].getPrice();
        }
        return total;
    }

   public boolean checkProductByName(String name) {
        for (int i = 0; i < count; i++) {
            if (products[i].getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

   public void clearCart() {
        for (int i = 0; i < count; i++) {
            products[i] = null;
        }
        count = 0;
        System.out.println("Корзина очищена");
    }
}
//
