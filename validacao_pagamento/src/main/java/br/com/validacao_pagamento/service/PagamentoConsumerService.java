package br.com.validacao_pagamento.service;

import br.com.validacao_pagamento.dto.PagamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PagamentoConsumerService {

    @Autowired
    private ValidacaoService validacaoService;
    @Autowired
    private PagamentoProducerService pagamentoProducerService;

    @KafkaListener(topics = "${spring.kafka.validacao-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void receiveMessage(@Payload PagamentoDTO pagamentoDTO){
        //logger.info(String.format("User created -> %s", user));
        var validatedPagamentoDTO = this.validacaoService.valid(pagamentoDTO);
        this.pagamentoProducerService.sendMessage(validatedPagamentoDTO);
    }
}
