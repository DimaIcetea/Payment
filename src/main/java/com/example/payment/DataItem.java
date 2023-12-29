package com.example.payment;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DataItem {
    private final StringProperty name;
    private final StringProperty tariff;
    private final StringProperty prev;
    private final StringProperty curr;
    private final StringProperty count;
    private final IntegerProperty difference;

    private final IntegerProperty pay;



    public DataItem(String name, String tariff, String prev, String curr, String count) {
        this.name = new SimpleStringProperty(name);
        this.tariff = new SimpleStringProperty(tariff);
        this.prev = new SimpleStringProperty(prev);
        this.curr = new SimpleStringProperty(curr);
        this.count = new SimpleStringProperty(count);
        this.difference = new SimpleIntegerProperty(Integer.parseInt(curr) - Integer.parseInt(prev));
        this.pay = new SimpleIntegerProperty(getDifference() * Integer.parseInt(tariff));
    }


    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty tariffProperty() {
        return tariff;
    }

    public StringProperty prevProperty() {
        return prev;
    }

    public StringProperty currProperty() {
        return curr;
    }

    public StringProperty countProperty() {
        return count;
    }
    public int getDifference() {
    return difference.get();
    }
    public IntegerProperty differenceProperty() {
        return difference;
    }
    public int getPay() {
        return pay.get();
    }

    public IntegerProperty payProperty() {
        return pay;
    }
    public void setName(String name) {
        this.name.set(name);
    }

    public void setTariff(String tariff) {
        this.tariff.set(tariff);
    }

    public void setPrev(String prev) {
        this.prev.set(prev);
    }

    public void setCurr(String curr) {
        this.curr.set(curr);
    }

    public void setCount(String count) {
        this.count.set(count);
    }

}
