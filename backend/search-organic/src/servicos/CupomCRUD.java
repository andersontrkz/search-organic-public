package servicos;
import modelo.Cupom;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CupomCRUD {

    private ArrayList<Cupom> cupons;

    public CupomCRUD() {
        this.cupons = new ArrayList<>();
    }

    public void adicionarCupom(Cupom cupom) {
        cupons.add(cupom);
    }

    public void listarCupons() {
        for (Cupom cupom : cupons) {
            cupom.imprimir();
        }
    }
    public Cupom buscarCupomPorId(int id) {
        for (Cupom cupom : cupons) {
            System.out.println("Verificando Cupom por Id:" + cupom.getCupomId());
            if (cupom.getCupomId() == id) {
                System.out.println("Cupom encontrado:" + cupom.getCupomId());
                cupom.imprimir();
                return cupom;
            }
        }
        System.out.println("Cupom com ID " + id + " não encontrado");
        return null;
    }

    public void atualizarCupom(int id, String novoNomeProduto, String novaDescricao, double novaTaxaDeDesconto) {
        for (Cupom cupom : cupons) {
            if (cupom.getCupomId() == id) {
                System.out.println("Cupom encontrado, atualize as informações: " + cupom.getCupomId());
                cupom.setNomeProduto(novoNomeProduto);
                cupom.setDescricao(novaDescricao);
                cupom.setTaxaDeDesconto(BigDecimal.valueOf(novaTaxaDeDesconto));
                System.out.println("Cupom atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Cupom não pode ser atualizado");

    }

    public void deletarCupom(int id) {
        for (Cupom cupom : cupons) {
            if (cupom.getCupomId() == id) {
                cupons.remove(cupom);
                return;
            }
        }
        System.out.println("Cupom não pode ser encontrado em nosso Sistema");
    }

}


