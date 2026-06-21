package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

class SearchEngineTest {
    public static void main(String[] args) {
        SearchEngine searchEngine = new SearchEngine(10);

        searchEngine.add(new SimpleProduct("Ноутбук Lenovo", 50000));
        searchEngine.add(new SimpleProduct("Смартфон Samsung", 30000));
        searchEngine.add(new SimpleProduct("Планшет Apple", 40000));
        searchEngine.add(new SimpleProduct("Наушники Sony", 15000));
        searchEngine.add(new SimpleProduct("Монитор Dell", 25000));

        searchEngine.add(new Article(
                "Как выбрать ноутбук",
                "Советы по выбору ноутбука: обращайте внимание на процессор, объём оперативной памяти и тип накопителя."
        ));
        searchEngine.add(new Article(
                "Обзор смартфонов 2024",
                "Лучшие смартфоны этого года: сравнение характеристик, камер и автономности."
        ));
        searchEngine.add(new Article(
                "Гайд по планшетам",
                "Что важно знать при выборе планшета: размер экрана, операционная система, производительность."
        ));
        searchEngine.add(new Article(
                "Наушники: проводные vs беспроводные",
                "Сравнение проводных и беспроводных наушников: качество звука, удобство использования, цена."
        ));

        System.out.println("=== ТЕСТИРОВАНИЕ ПОИСКА ===\n");

        testSearch(searchEngine, "ноутбук");
        testSearch(searchEngine, "смартфон");
        testSearch(searchEngine, "планшет");
        testSearch(searchEngine, "наушники");
        testSearch(searchEngine, "sony");
        testSearch(searchEngine, "неизвестный_запрос");
        testSearch(searchEngine, "НОУТБУК");
    }

    static void testSearch(SearchEngine searchEngine, String query) {
        System.out.println("Поиск по запросу: \"" + query + "\"");
        Searchable[] results = searchEngine.search(query);

        if (results.length == 0) {
            System.out.println("Результаты не найдены.");
        } else {
            System.out.println("Найденные элементы:");
            for (int i = 0; i < results.length; i++) {
                if (results[i] != null) {
                    System.out.println("  " + (i + 1) + ". " + results[i].getStringRepresentation());
                }
            }
            System.out.println();
        }
    }
}