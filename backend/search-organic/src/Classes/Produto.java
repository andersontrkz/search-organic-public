package Classes;

import utils.UnidadeMedida;

import java.math.BigDecimal;
import java.util.UUID;

public class Produto {
    private final UUID id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private BigDecimal quantidade;
    private Categoria categoria;
    private double taxa;
    private UnidadeMedida unidadeMedida;

    public Produto(String nome, String descricao, BigDecimal preco, BigDecimal quantidade, Categoria categoria, double taxa, UnidadeMedida unidadeMedida) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.taxa = taxa;
        this.unidadeMedida = unidadeMedida;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }
}