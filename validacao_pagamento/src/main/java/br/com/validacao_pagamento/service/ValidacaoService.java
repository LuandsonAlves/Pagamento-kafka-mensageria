package br.com.validacao_pagamento.service;

import br.com.validacao_pagamento.dto.PagamentoDTO;
import br.com.validacao_pagamento.entity.enuns.StatusPagamento;
import br.com.validacao_pagamento.exception.ValidationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ValidacaoService {

    public PagamentoDTO valid(PagamentoDTO pagamentoDTO){
        var barCode = pagamentoDTO.getBarCode();

        if(barCode.length() == 6){
            int soma = 0;

            for (int i = 0; i < 4; i++){
                soma += barCode.charAt(i) - '0';
            }

            if(soma == Integer.parseInt(barCode.substring(4,6))){

                pagamentoDTO.setStatus(StatusPagamento.FINALIZADO);
                pagamentoDTO.setUpdateData(LocalDateTime.now());
                return pagamentoDTO;
            }
            throw new ValidationException("Pagamento não validado: Erro na autenticação");
        }

        throw new ValidationException("Pagamento não validado: Tamanho do codigo de barra invalido");
    }

}
