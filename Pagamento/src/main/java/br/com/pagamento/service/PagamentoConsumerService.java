package br.com.pagamento.service;

import br.com.pagamento.dto.PagamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PagamentoConsumerService {

    @Autowired
    public PagamentoService pagamentoService;

    @KafkaListener(topics = "${spring.kafka.validado-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void receiveMessage(@Payload PagamentoDTO pagamentoDTO){
        this.pagamentoService.save(pagamentoDTO);
    }
}
