package com.ambev.projetopratico5.dto;

import org.modelmapper.ModelMapper;


public class ProdutoDTOConverter {
    private ModelMapper modelMapper = new ModelMapper();

    public ProdutoDTO convertToDTO(ProdutoDTO produto) {
        return modelMapper.map(produto, ProdutoDTO.class);
    }
}