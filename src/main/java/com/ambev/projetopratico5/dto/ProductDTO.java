package com.ambev.projetopratico5.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProductDTO {
    private String id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Size(min = 10, message = "the description must contain at least 4 characters ")
    private String descriptions;

    @DecimalMin(value = "1", message = "the price must be greater than 0")
    private float price;

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
