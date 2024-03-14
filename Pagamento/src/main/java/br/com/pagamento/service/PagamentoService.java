package br.com.pagamento.service;

import br.com.pagamento.dto.PagamentoDTO;
import br.com.pagamento.entity.Pagamento;
import br.com.pagamento.entity.enuns.StatusPagamento;
import br.com.pagamento.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private PagamentoProducerService pagamentoProducerService;

    public PagamentoDTO create(String barcode){
        var savedPagamentoDTO = get(barcode);
        if(savedPagamentoDTO == null){
            var pagamento = Pagamento.builder()
                    .barCode(barcode)
                    .status(StatusPagamento.INICIADO)
                    .createData(LocalDateTime.now())
                    .updateData(LocalDateTime.now())
                    .build();

            var updatedPagamento = pagamentoRepository.save(pagamento);

            return convertToDto(pagamento);
        }else{
            //throw new ("");
        }
        return savedPagamentoDTO;
    }

    public PagamentoDTO save(PagamentoDTO pagamentoDTO){
        var pagamento = convertToEntity(pagamentoDTO);
        pagamentoRepository.save(pagamento);
        return pagamentoDTO;
    }

    public PagamentoDTO get(String barCode){
        return pagamentoRepository.findByBarCode(barCode).map(this::convertToDto).orElse(null);
    }

    public PagamentoDTO delete(PagamentoDTO pagamentoDTO){
        var pagamento = convertToEntity(pagamentoDTO);
        pagamentoRepository.delete(pagamento);
        return pagamentoDTO;
    }

    public PagamentoDTO update(PagamentoDTO pagamentoDTO){
        save(pagamentoDTO);
        return pagamentoDTO;
    }

    public PagamentoDTO valid(PagamentoDTO pagamentoDTO){
        pagamentoDTO.setStatus(StatusPagamento.PROCESSANDO);
        save(pagamentoDTO);
        pagamentoProducerService.sendMessage(pagamentoDTO);
        return pagamentoDTO;
    }

    private PagamentoDTO convertToDto(Pagamento pagamento){
        return PagamentoDTO.builder()
                .id(pagamento.getId())
                .status(pagamento.getStatus())
                .barCode(pagamento.getBarCode())
                .createData(pagamento.getCreateData())
                .updateData(pagamento.getUpdateData()).build();
    }
    private Pagamento convertToEntity(PagamentoDTO pagamentoDTO){
        return Pagamento.builder()
                .id(pagamentoDTO.getId())
                .status(pagamentoDTO.getStatus())
                .barCode(pagamentoDTO.getBarCode())
                .createData(pagamentoDTO.getCreateData())
                .updateData(pagamentoDTO.getUpdateData()).build();
    }


}
