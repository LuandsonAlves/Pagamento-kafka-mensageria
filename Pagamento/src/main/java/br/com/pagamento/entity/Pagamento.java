package br.com.pagamento.entity;

import br.com.pagamento.entity.enuns.StatusPagamento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private StatusPagamento status;
    @Column(name = "bar_code")
    private String barCode;
    @Column(name = "create_data")
    private LocalDateTime createData;
    @Column(name = "update_data")
    private LocalDateTime updateData;

}
