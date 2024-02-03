package com.vemser.dbc.searchorganic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vemser.dbc.searchorganic.dto.produto.ProdutoCreateDTO;
import com.vemser.dbc.searchorganic.dto.produto.ProdutoDTO;
import com.vemser.dbc.searchorganic.dto.produto.ProdutoUpdateDTO;
import com.vemser.dbc.searchorganic.exceptions.RegraDeNegocioException;
import com.vemser.dbc.searchorganic.model.Produto;
import com.vemser.dbc.searchorganic.model.PedidoXProduto;
import com.vemser.dbc.searchorganic.repository.ProdutoRepository;
import com.vemser.dbc.searchorganic.service.interfaces.IProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProdutoService implements IProdutoService {
    private final ProdutoRepository produtoRepository;
    private final EmpresaService empresaService;
    private final ObjectMapper objectMapper;

    public List<ProdutoDTO> findAll() throws Exception {
        return produtoRepository.findAll().stream()
                .map(this::retornarDto)
                .collect(Collectors.toList());
    }

    public ProdutoDTO findById(Integer id) throws Exception {
        return retornarDto(produtoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Produto não encontrado")));
    }

    public ProdutoDTO save(Integer idEmpresa, ProdutoCreateDTO produtoDto) throws Exception {
        empresaService.findById(idEmpresa);

        Produto produto = convertDto(produtoDto);
        produto.setIdEmpresa(idEmpresa);

        return retornarDto(produtoRepository.save(produto));
    }

    public ProdutoDTO update(Integer idProduto, ProdutoUpdateDTO produtoDto) throws Exception {
        findById(idProduto);

        Produto produto = objectMapper.convertValue(produtoDto, Produto.class);
        produto.setIdProduto(idProduto);

        return retornarDto(produtoRepository.save(produto));
    }

    public void delete(Integer idProduto) throws Exception {
        findById(idProduto);
        produtoRepository.deleteById(idProduto);
    }

    public List<ProdutoDTO> findAllByIdEmpresa(Integer idEmpresa) throws Exception {
        empresaService.findById(idEmpresa);

        List<Produto> produtos = produtoRepository.findAllByIdEmpresa(idEmpresa);
        return produtos.stream()
                .map(this::retornarDto)
                .collect(Collectors.toList());
    }

    public List<ProdutoDTO> findAllByIdCategoria(Integer idCategoria) throws Exception {
        List<Produto> produtos = produtoRepository.findAllByIdCategoria(idCategoria);
        return produtos.stream()
                .map(this::retornarDto)
                .collect(Collectors.toList());
    }

    public String getMensagemProdutoEmail(List<PedidoXProduto> produtos) {
        StringBuilder mensagemFinal = new StringBuilder();
        for (PedidoXProduto pedidoXProduto : produtos) {
            String mensagemProduto = String.format("""
                            Nome: %s, Quantidade:  %s, Valor por cada quantidade: R$ %s  <br>
                            """, pedidoXProduto.getProduto().getNome(),
                    pedidoXProduto.getQuantidade(),
                    pedidoXProduto.getProduto().getPreco());
            mensagemFinal.append(mensagemProduto);
        }
        return mensagemFinal.toString();
    }

    public Produto getById(Integer idProduto) throws Exception {
        ProdutoDTO produto = findById(idProduto);
        return convertDto(produto);
    }

    public Produto convertDto(ProdutoCreateDTO dto) {
        return objectMapper.convertValue(dto, Produto.class);
    }

    public ProdutoDTO retornarDto(Produto entity) {
        return objectMapper.convertValue(entity, ProdutoDTO.class);
    }
}



