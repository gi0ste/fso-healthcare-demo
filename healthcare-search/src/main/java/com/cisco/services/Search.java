package com.cisco.services;

public class Search {
    private String category;

    public Search(String pCategory) {
        this.category = pCategory;
    }
    public void setCategory(String pCategory) {
        this.category = pCategory;
    }

    public String getCategory() {
        return this.category;
    }
}
