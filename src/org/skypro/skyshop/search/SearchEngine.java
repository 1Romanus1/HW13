package org.skypro.skyshop.search;

import org.skypro.skyshop.search.Searchable;

public class SearchEngine {
    private Searchable[] searchableItems;
    private int count;


    public SearchEngine(int capacity) {
        this.searchableItems = new Searchable[capacity];
        this.count = 0;
    }


    public void add(Searchable item) {
        if (item == null) {
            System.out.println("Нельзя добавить null-элемент.");
            return;
        }

        if (count < searchableItems.length) {
            searchableItems[count] = item;
            count++;
        } else {
            System.out.println("Массив поиска заполнен! Элемент не добавлен.");
        }
    }


    public Searchable[] search(String query) {
        // Проверяем, что запрос не null
        if (query == null) {
            return new Searchable[5];
        }

        Searchable[] results = new Searchable[5];
        int resultCount = 0;

        for (int i = 0; i < count; i++) {
            Searchable item = searchableItems[i];


            if (item == null) {
                continue;
            }


            if (item.getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                results[resultCount] = item;
                resultCount++;


                if (resultCount == 5) {
                    break;
                }
            }
        }

        return results;
    }
}
