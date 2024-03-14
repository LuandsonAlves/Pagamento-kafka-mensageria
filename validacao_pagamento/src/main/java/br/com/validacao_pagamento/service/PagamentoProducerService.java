package br.com.validacao_pagamento.service;

import br.com.validacao_pagamento.dto.PagamentoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PagamentoProducerService {
    private KafkaTemplate<String, PagamentoDTO> kafkaTemplate;

    @Value("${spring.kafka.validado-topic}")
    public String validadoTopic;


    public PagamentoProducerService(KafkaTemplate<String, PagamentoDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(PagamentoDTO pagamentoDTO){

        kafkaTemplate.send(validadoTopic, pagamentoDTO);
    }

}
