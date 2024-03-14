package br.com.validacao_pagamento.controller;

import br.com.validacao_pagamento.dto.PagamentoDTO;
import br.com.validacao_pagamento.service.PagamentoService;
import br.com.validacao_pagamento.service.ValidacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/valiadacao")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<PagamentoDTO> postProcessamento(@Valid @RequestBody PagamentoDTO pagamentoDTO){
        var validatedPagamento = pagamentoService.processPagamento(pagamentoDTO);
        return new ResponseEntity<PagamentoDTO>(validatedPagamento, HttpStatus.CREATED);
    }
}
