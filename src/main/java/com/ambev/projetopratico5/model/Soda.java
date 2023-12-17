package com.ambev.projetopratico5.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "softDrinks")
public class Soda extends Product{

    private boolean zero;

    public Soda(boolean zero) {
        this.zero = zero;
    }

    public boolean isZero() {
        return zero;
    }

    public void setZero(boolean zero) {
        this.zero = zero;
    }
}
