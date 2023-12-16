package com.ambev.projetopratico5.service;

import com.ambev.projetopratico5.DTO.ProdutoDTO;
import com.ambev.projetopratico5.model.Produto;
import com.ambev.projetopratico5.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoDTO convertToDTO(Produto produto) {
        return modelMapper.map(produto, ProdutoDTO.class);
    }

    public Produto convertToProduto(ProdutoDTO produtoDTO) {
        return modelMapper.map(produtoDTO, Produto.class);
    }


    public Produto cadastrarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public ProdutoDTO cadastrarProduto(ProdutoDTO produtoDTO) {
        Produto produto = convertToProduto(produtoDTO);
        return convertToDTO(produtoRepository.save(produto));
    }

    public ProdutoDTO atualizarProduto(String id, ProdutoDTO produtoDTO) {
        Produto produtoExistente = produtoRepository.findById(id).
                orElse(null);
        if(produtoExistente != null){
            produtoExistente.setNome(produtoDTO.getNome());
            produtoExistente.setDescricao(produtoDTO.getDescricao());
            produtoExistente.setPreco(produtoDTO.getPreco());

            return convertToDTO(produtoRepository.save(produtoExistente));
        } else {
            return null;
        }

    }

    public List<ProdutoDTO> consultarPorNome(String nome) {
        List<Produto> listaProdutosPorNome = produtoRepository.findByNome(nome);
        List<ProdutoDTO> listaDTO = listaProdutosPorNome.stream()
                .map(source -> modelMapper.map(source, ProdutoDTO.class))
                .collect(Collectors.toList());
        return listaDTO;
    }

    public boolean deletarProduto(String id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isPresent()) {
            produtoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }
}