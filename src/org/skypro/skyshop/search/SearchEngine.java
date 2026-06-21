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

    public Searchable findMostRelevant(String search) throws BestResultNotFound {
        if (search == null || search.isBlank()) {
            throw new BestResultNotFound("Поисковый запрос пуст или равен null.");
        }
        Searchable bestMath = null;
        int maxCount = -1;

        for (int i = 0; i < count; i++) {
            Searchable item = searchableItems[i];

            if (item == null) {
                continue;
            }

            String term = item.getSearchTerm();
            if (term == null) {
                continue;
            }

            int currentCount = countOccurrences(term, search);

            if (currentCount > maxCount) {
                maxCount = currentCount;
                bestMath = item;
            }
        }

        if (bestMath == null) {
            throw new BestResultNotFound("Не удалось найти наиболее подходящий элемент для запроса: \"" + search + "\"");
        }

        return bestMath;
    }

    private int countOccurrences(String str, String substring) {
        if (substring == null || substring.isEmpty() || str == null) {
            return 0;
        }

        String lowerStr = str.toLowerCase();
        String lowerSub = substring.toLowerCase();

        int count = 0;
        int index = 0;

        int substringIndex = lowerStr.indexOf(lowerSub, index);

        while (substringIndex != - 1) {
            count++;

            index = substringIndex + lowerSub.length();
            substringIndex = lowerStr.indexOf(lowerSub, index);
        }

        return count;
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
