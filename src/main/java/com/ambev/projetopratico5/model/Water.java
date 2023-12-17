package com.ambev.projetopratico5.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "water")
public class Water extends Product{

    private boolean sparkling;

    public Water(boolean sparkling) {
        this.sparkling = sparkling;
    }

    public boolean isSparkling() {
        return sparkling;
    }

    public void setSparkling(boolean sparkling) {
        this.sparkling = sparkling;
    }
}
