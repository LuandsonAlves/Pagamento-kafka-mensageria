package br.com.validacao_pagamento.dto;

import br.com.validacao_pagamento.entity.enuns.StatusPagamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoDTO implements Serializable {
    private Long id;
    private StatusPagamento status;
    @NotBlank
    @NotNull(message = "Atributo barCode não pode ser nulo")
    private String barCode;
    private LocalDateTime createData;
    private LocalDateTime updateData;

}
