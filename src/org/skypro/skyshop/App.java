package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;

public class App {
    public static void main(String[] args) {

        Product p1 = new Product("Apple", 100);
        Product p2 = new Product("Banana", 50);
        Product p3 = new Product("Milk", 120);
        Product p4 = new Product("Bread", 80);
        Product p5 = new Product("Tea", 200);
        Product p6 = new Product("Coffee", 300);

        ProductBasket basket = new ProductBasket();

        System.out.println("1. Добавляем 5 товаров:");
        basket.addProduct(p1);
        basket.addProduct(p2);
        basket.addProduct(p3);
        basket.addProduct(p4);
        basket.addProduct(p5);

        System.out.println("\n2. Попытка добавить 6-й товар:");
        basket.addProduct(p6);

        System.out.println("\n3. Печать корзины:");
        basket.print();

        System.out.println("\n4. Стоимость корзины:");
        System.out.println(basket.totalCost());

        System.out.println("\n5. Поиск существующего товара (Milk):");
        System.out.println(basket.checkProductByName("Milk"));

        System.out.println("\n6. Поиск НЕсуществующего товара (Water):");
        System.out.println(basket.checkProductByName("Water"));

        System.out.println("\n7. Очистка корзины:");
        basket.clearCart();

        System.out.println("\n8. Печать пустой корзины:");
        basket.print();

        System.out.println("\n9. Стоимость пустой корзины:");
        System.out.println(basket.totalCost());

        System.out.println("\n10. Поиск в пустой корзине (Apple):");
        System.out.println(basket.checkProductByName("Apple"));
    }
}