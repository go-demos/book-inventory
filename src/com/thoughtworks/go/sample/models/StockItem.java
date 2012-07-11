package com.thoughtworks.go.sample.models;

public class StockItem {

    private long id = -1;

    private String isbn;
    private int bookCount;

    StockItem() {
    }

    public StockItem(String isbn, int bookCount) {
        this.isbn = isbn;
        this.bookCount = bookCount;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setId(long id) {
        this.id = id;
    }
}
