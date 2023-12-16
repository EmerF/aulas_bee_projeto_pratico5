package com.ambev.projetopratico5.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "refrigerantes")
public class Refrigerante extends Produto {

    private boolean temAcucar;

    public boolean isTemAcucar() {
        return temAcucar;
    }

    public void setTemAcucar(boolean temAcucar) {
        this.temAcucar = temAcucar;
    }
}