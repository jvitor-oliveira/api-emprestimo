package dev.joao.projeto.api.emprestimo.dto.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import dev.joao.projeto.api.emprestimo.entity.Emprestimo;
import dev.joao.projeto.api.emprestimo.enums.Relacionamento;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class EmprestimoRequestDTO {

    private String cpfCliente;
    @NotNull(message = "VALOR INICIAL NULL")
    private BigDecimal valorInicial;
    private BigDecimal valorFinal;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "RELACIONAMENTO NULL")
    private Relacionamento relacionamento;
    @JsonFormat(pattern="dd/MM/yyyy")
    @NotNull(message = "DATA FINAL NULL")
    private LocalDate dataFinal;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataInicial;


    public EmprestimoRequestDTO(){
        this.dataInicial = LocalDate.now();
    }

    public EmprestimoRequestDTO(BigDecimal valorInicial, Relacionamento relacionamento, LocalDate dataFinal) {
        this.valorInicial = valorInicial;
        this.relacionamento = relacionamento;
        this.dataFinal = dataFinal;
    }

    public Emprestimo build(){
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setCpfCliente(this.cpfCliente);
        emprestimo.setValorInicial(this.valorInicial);
        emprestimo.setValorFinal(this.valorFinal);
        emprestimo.setRelacionamento(this.relacionamento);
        emprestimo.setDataFinal(this.dataFinal);
        emprestimo.setDataInicial(this.dataInicial);
        return emprestimo;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public BigDecimal getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }

    public Relacionamento getRelacionamento() {
        return relacionamento;
    }

    public void setRelacionamento(Relacionamento relacionamento) {
        this.relacionamento = relacionamento;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }
}
