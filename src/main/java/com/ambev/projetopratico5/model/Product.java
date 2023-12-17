package com.ambev.projetopratico5.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {
    private String id;

    private String name;

    private String descriptions;

    private double price;

    @Override
    public String toString(){
        return "Product{" +
                "name:'" + name + '\'' +
                ", descriptions:'" + descriptions + '\'' +
                ", price:" + price + '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



}
