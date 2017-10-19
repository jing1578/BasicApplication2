package org.jing1578.basicapplication.model;

import com.google.gson.Gson;

/**
 * Created by jing1578 on 2016/12/22.
 * 建造者模式使用 Book
 */

public class Book {

    private int id;
    private String author;
    private float price;
    private int pages;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static Book convertJsonObjectToBook(String objectStr) {
        Gson gson = new Gson();
        return gson.fromJson(objectStr, Book.class);
    }

   /* private Book(Builder builder){
        this.author=builder.name;
        this.id=builder.id;
        this.name=builder.name;
        this.pages=builder.pages;
        this.price=builder.price;
    }

    static class Builder {
        private int id;
        private String author;
        private float price;
        private int pages;
        private String name;

        private Builder(){}

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(float price) {
            this.price = price;
            return this;
        }

        public Builder pages(int pages) {
            this.pages = pages;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }


        public Book build() {
            return new Book(this);
        }
    }*/

    static class Builder {
        private Book mBook;


        private Builder() {
            mBook = new Book();
        }

        public Builder id(int id) {
            mBook.id = id;
            return this;
        }

        public Builder name(String name) {
            mBook.name = name;
            return this;
        }

        public Builder price(float price) {
            mBook.price = price;
            return this;
        }

        public Builder pages(int pages) {
            mBook.pages = pages;
            return this;
        }

        public Builder author(String author) {
            mBook.author = author;
            return this;
        }

        public Book  build(){
            return mBook;
        }

    }
}
