package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    // properties
    //date|time|description|vendor|amount
    LocalDate date;
    LocalTime time;
    String description;
    String vendor;
    double amount;
    // constructors
    public Transaction(LocalDate date, LocalTime time,String description, String vendor, double amount){
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }
    //getters and setters- Jeni had us use the generator

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }


    // methods (    //date|time|description|vendor|amount
    public String toString(){
        return String.format("%s|%s|%s|%s|%.2f",date, time, description, vendor, amount);

    }
}
