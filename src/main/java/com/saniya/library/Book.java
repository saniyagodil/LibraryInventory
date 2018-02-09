package com.saniya.library;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 4)
    private String title;

    @NotNull
    @Size(min = 4)
    private String author;

    @NotNull
    private int yearPub;


    private String isbnNum;


    private String image = "https://clipartion.com/wp-content/uploads/2016/03/closed-book-clipart-2.png";

    private String available ="Available";

    private int numTimes =0;

    private String lastBorrow = "N/A";



    public Book(){

    }
    public Book(String t, String a, int y){
        this.title = t;
        this.author = a;
        this.yearPub = y;

    }

    public Book(String t, String a, int y, String i, String im){
        this.title = t;
        this.author = a;
        this.yearPub = y;
        this.isbnNum = i;
        this.image = im;

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getYearPub() {
        return yearPub;
    }

    public void setYearPub(int yearPub) {
        this.yearPub = yearPub;
    }

    public String getIsbnNum() {
        return isbnNum;
    }

    public void setIsbnNum(String isbnNum) {
        this.isbnNum = isbnNum;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public int getNumTimes() {
        return numTimes;
    }

    public void setNumTimes(int numTimes) {
        this.numTimes = numTimes;
    }

    public String getLastBorrow() {
        return lastBorrow;
    }

    public void setLastBorrow(String lastBorrow) {
        this.lastBorrow = lastBorrow;
    }


}
