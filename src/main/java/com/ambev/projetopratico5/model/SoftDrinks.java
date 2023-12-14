package com.ambev.projetopratico5.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "softDrinks")
public class SoftDrinks extends Product{

    private boolean zero;

    public SoftDrinks(boolean zero) {
        this.zero = zero;
    }

    public boolean isZero() {
        return zero;
    }

    public void setZero(boolean zero) {
        this.zero = zero;
    }
}
