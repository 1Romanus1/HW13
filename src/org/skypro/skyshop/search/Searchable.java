package org.skypro.skyshop.search;

public interface Searchable {

    public String getSearchTerm();

    public  String getContentType();

    public String getName();

    default public String getStringRepresentation() {
        return getName() + " - " + getContentType();
    }
}
