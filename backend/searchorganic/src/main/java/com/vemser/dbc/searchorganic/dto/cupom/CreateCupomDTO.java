package com.vemser.dbc.searchorganic.dto.cupom;

import com.vemser.dbc.searchorganic.utils.TipoAtivo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateCupomDTO {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    @Schema(description = "Nome do cupom",required = true, example = "cupom 10%")
    private String nomeCupom;

    @NotNull
    @NotBlank
    @Schema(description = "atividade do cupom",required = true, example = "cupom inativo")
    private TipoAtivo ativo;

    @NotBlank
    @NotNull
    @Schema(description = "Descrição do cupom",required = true, example = "cupom de 10% de desconto para uso em uma nova loja")
    @Size(min = 1, max = 255)
    private String descricao;

    @NotBlank
    @NotNull
    @Schema(description = "Taxa de desconto do cupom",required = true, example = "10")
    private BigDecimal taxaDeDesconto = new BigDecimal(0);

    @NotBlank
    @NotNull
    @Schema(description = "Id daa empresa a ser aplicado o cupom",required = true, example = "Fazendo do Wlad")
    @Size(min = 1,max = 38)
    private Integer idEmpresa;
}
