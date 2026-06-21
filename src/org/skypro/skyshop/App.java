package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Тестирование проверки данных и обработки ошибок ===\n");

        // --- БЛОК 1: Тест конструктора SimpleProduct (цена <= 0) ---
        System.out.println("1. Тест конструктора SimpleProduct (цена <= 0):");

        try {
            Product badProduct1 = new SimpleProduct("Дефектный товар", 0);
            System.out.println("Ошибка. Товар был создан, хотя не должен был");
        } catch (IllegalArgumentException e) {
            System.out.println("Успех. Перехвачено исключение: " + e.getMessage());
        }

        try {
            Product badProduct2 = new SimpleProduct("Убыточный товар", -500);
            System.out.println("Ошибка. Товар был создан, хотя не должен был");
        } catch (IllegalArgumentException e) {
            // Исправлена опечатка: было \" + e.getMessage()
            System.out.println("Успех. Перехвачено исключение: " + e.getMessage());
        }

        System.out.println();

        // --- БЛОК 2: Тест конструктора DiscountedProduct (ошибки ввода) ---
        System.out.println("2. Тест конструктора DiscountedProduct (ошибки ввода):");

        try {
            new DiscountedProduct("Товар с плохой базой", 0, 10);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка цены: " + e.getMessage());
        }

        try {
            new DiscountedProduct("Товар с отрицательной скидкой", 1000, -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Успех. Ошибка скидки (<0): " + e.getMessage());
        }

        try {
            new DiscountedProduct("Сверхскидка", 1000, 150);
        } catch (IllegalArgumentException e) {
            System.out.println("Успех. Ошибка скидки (>100): " + e.getMessage());
        }

        System.out.println();

        // --- БЛОК 3: Работа с корзиной ---
        Product p1 = new SimpleProduct("Apple", 100);
        Product p2 = new SimpleProduct("Banana", 50);
        Product p3 = new SimpleProduct("Milk", 120);
        Product p4 = new SimpleProduct("Bread", 80);
        Product p5 = new SimpleProduct("Tea", 200);
        Product p6 = new SimpleProduct("Coffee", 300);

        ProductBasket basket = new ProductBasket();

        System.out.println("3. Добавляем 5 товаров:");
        basket.addProduct(p1);
        basket.addProduct(p2);
        basket.addProduct(p3);
        basket.addProduct(p4);
        basket.addProduct(p5);

        System.out.println("\n4. Попытка добавить 6-й товар:");
        try {
            basket.addProduct(p6);
        } catch (IllegalArgumentException e) {
            System.out.println("Успех. Корзина не переполнена, ошибка обработана: " + e.getMessage());
        }

        System.out.println("\n5. Печать корзины:");
        basket.print();

        System.out.println("\n6. Стоимость корзины:");
        System.out.println(basket.totalCost() + " руб.");

        System.out.println("\n7. Поиск существующего товара (Milk):");
        System.out.println(basket.checkProductByName("Milk") ? "Товар найден" : "Товар не найден");

        System.out.println("\n8. Поиск НЕсуществующего товара (Water):");
        System.out.println(basket.checkProductByName("Water") ? "Товар найден" : "Товар не найден");

        System.out.println("\n9. Очистка корзины:");
        basket.clearCart();

        System.out.println("\n10. Печать пустой корзины:");
        basket.print();

        System.out.println("\n11. Стоимость пустой корзины:");
        System.out.println(basket.totalCost() + " руб.");

        System.out.println("\n12. Поиск в пустой корзине (Apple):");
        System.out.println(basket.checkProductByName("Apple") ? "Товар найден" : "Товар не найден");


        // ============================================================
        // НОВЫЙ БЛОК: Демонстрация метода findMostRelevant
        // ============================================================
        System.out.println("\n=== Тестирование поиска самого подходящего элемента ===");

        SearchEngine engine = new SearchEngine(10);

        engine.add(new SimpleProduct("Old Phone", 100));                 // "phone" - 1 раз
        engine.add(new SimpleProduct("New Super Phone Phone", 250));     // "phone" - 2 раза
        engine.add(new SimpleProduct("Tablet", 300));                    // "phone" - 0 раз
        engine.add(new SimpleProduct("PhonePhonePhone", 400));           // "phone" - 3 раза
        engine.add(new SimpleProduct("Cheap Headphones", 150));          // "phone" - 1 раз

        String searchTerm = "phone";

        System.out.println("--- Сценарий А: Объект существует (поиск '" + searchTerm + "') ---");

        // 2. ОБЯЗАТЕЛЬНО: Оборачиваем в try-catch, так как метод выбрасывает BestResultNotFound
        try {
            Searchable result = engine.findMostRelevant(searchTerm);

            System.out.println("[УСПЕХ] Найден самый подходящий товар:");
            System.out.println("   Имя: " + result.getName());
            System.out.println("   Поисковый термин: " + result.getSearchTerm());

        } catch (BestResultNotFound e) {
            // Этот блок НЕ выполнится, так как товар есть
            System.out.println("[ОШИБКА] Неожиданное исключение: " + e.getMessage());
        }

        System.out.println();

        String nonExistentTerm = "unicorn";
        System.out.println("--- Сценарий Б: Объект не найден (поиск '" + nonExistentTerm + "') ---");

        try {
            engine.findMostRelevant(nonExistentTerm);
            System.out.println("[ОШИБКА] Метод не выбросил исключение, хотя должен был!");
        } catch (BestResultNotFound e) {
            System.out.println("[ПЕРЕХВАЧЕНО ИСКЛЮЧЕНИЕ]");
            System.out.println("   Сообщение об ошибке: " + e.getMessage());
        }

        System.out.println();

        String emptyTerm = "";
        System.out.println("--- Сценарий В: Пустой запрос (поиск '" + emptyTerm + "') ---");
        try {
            engine.findMostRelevant(emptyTerm);
        } catch (BestResultNotFound e) {
            System.out.println("[ПЕРЕХВАЧЕНО ИСКЛЮЧЕНИЕ ДЛЯ ПУСТОГО ЗАПРОСА]");
            System.out.println("   Сообщение: " + e.getMessage());
        }

        System.out.println("\n=== ВСЕ ТЕСТЫ ЗАВЕРШЕНЫ ===");
    }
}
