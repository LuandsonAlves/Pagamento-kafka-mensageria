package br.com.pagamento.controller;

import br.com.pagamento.dto.PagamentoDTO;
import br.com.pagamento.service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping("/{barCode}")
    public ResponseEntity<PagamentoDTO> getPagamento(@PathVariable("barCode") String barCod){
        return ResponseEntity.ok(this.pagamentoService.get(barCod));
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> postPagamento(@Valid @RequestBody PagamentoDTO pagamentoDTO){
        var savedPagamento = pagamentoService.create(pagamentoDTO.getBarCode());
        return new ResponseEntity<PagamentoDTO>(savedPagamento, HttpStatus.CREATED);
    }

    @PostMapping("/validacao")
    public ResponseEntity<PagamentoDTO> postPagamentoValidacao(@Valid @RequestBody PagamentoDTO pagamentoDTO){
        var savedPagamentoDTO = pagamentoService.get(pagamentoDTO.getBarCode());
        var validatedPagamento = pagamentoService.valid(savedPagamentoDTO);
        var updatePagamento = pagamentoService.update(validatedPagamento);
        return new ResponseEntity<PagamentoDTO>(updatePagamento, HttpStatus.CREATED);
    }
}