package com.ambev.projetopratico5.dto;

import com.ambev.projetopratico5.model.Product;
import org.modelmapper.ModelMapper;

public class ConverterProductDTO {
    private ModelMapper modelMapper = new ModelMapper();

    public ProductDTO convertDTO(Product product){
        return modelMapper.map(product, ProductDTO.class);
    }
}
