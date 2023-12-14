package com.ambev.projetopratico5.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "waters")
public class Waters extends Product{

    private boolean sparkling;

    public Waters(boolean sparkling) {
        this.sparkling = sparkling;
    }

    public boolean isSparkling() {
        return sparkling;
    }

    public void setSparkling(boolean sparkling) {
        this.sparkling = sparkling;
    }
}
