package com.ambev.projetopratico5.dto;

import com.ambev.projetopratico5.dto.ProdutoDTO;
import com.ambev.projetopratico5.service.ProdutoService;
import org.modelmapper.ModelMapper;

public class ProdutoDTOConverter {
    private ModelMapper modelMapper = new ModelMapper();
    public ProdutoDTO convertToDTO(ProdutoService produto) {
        return modelMapper.map(produto, ProdutoDTO.class);
    }
}