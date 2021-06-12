package com.andreas.Encapsulation;

/**
 * @author 1972015 Andreas Yoseph Liandy
 */

public class Book {
    public String isbn;
    public String title;
    public String author;

    public Book(){

    }

    public Book(String isbn, String title, String author){
        this.title = title;
        this.isbn = isbn;
        this.author = author;

    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
