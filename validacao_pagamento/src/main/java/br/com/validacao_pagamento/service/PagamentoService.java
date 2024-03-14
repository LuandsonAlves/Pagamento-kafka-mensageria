package br.com.validacao_pagamento.service;

import br.com.validacao_pagamento.dto.PagamentoDTO;
import br.com.validacao_pagamento.entity.enuns.StatusPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PagamentoService {

    @Autowired
    private ValidacaoService validacaoService;

    public PagamentoDTO processPagamento(PagamentoDTO pagamentoDTO){
        var validatedPagamentoDTO = validacaoService.valid(pagamentoDTO);
        validatedPagamentoDTO.setStatus(StatusPagamento.FINALIZADO);
        validatedPagamentoDTO.setUpdateData(LocalDateTime.now());
        return validatedPagamentoDTO;
    }


}
