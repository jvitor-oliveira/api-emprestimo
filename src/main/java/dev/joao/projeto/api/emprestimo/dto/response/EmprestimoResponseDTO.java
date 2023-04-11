package dev.joao.projeto.api.emprestimo.dto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import dev.joao.projeto.api.emprestimo.entity.Emprestimo;
import dev.joao.projeto.api.emprestimo.enums.Relacionamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class EmprestimoResponseDTO {

    private String cpfCliente;
    private BigDecimal valorInicial;
    private BigDecimal valorFinal;
    private Relacionamento relacionamento;
    private LocalDate dataFinal;
    private LocalDate dataInicial;

    public EmprestimoResponseDTO(){

    }

    public EmprestimoResponseDTO(String cpfCliente, BigDecimal valorInicial, BigDecimal valorFinal, Relacionamento relacionamento, LocalDate dataFinal, LocalDate dataInicial) {
        this.cpfCliente = cpfCliente;
        this.valorInicial = valorInicial;
        this.valorFinal = valorFinal;
        this.relacionamento = relacionamento;
        this.dataFinal = dataFinal;
        this.dataInicial = dataInicial;
    }

    public EmprestimoResponseDTO(Emprestimo emprestimo){
        this.cpfCliente = emprestimo.getCpfCliente();
        this.valorInicial = emprestimo.getValorInicial();
        this.valorFinal = emprestimo.getValorFinal();
        this.relacionamento = emprestimo.getRelacionamento();
        this.dataFinal = emprestimo.getDataFinal();
        this.dataInicial = emprestimo.getDataInicial();
    }

    public static List<EmprestimoResponseDTO> convert(List<Emprestimo> emprestimos) {
        return emprestimos.stream().map(EmprestimoResponseDTO::new).collect(Collectors.toList());
    }
}
