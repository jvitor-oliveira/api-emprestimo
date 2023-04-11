package dev.joao.projeto.api.emprestimo.dto.request;

import dev.joao.projeto.api.emprestimo.entity.Cliente;
import dev.joao.projeto.api.emprestimo.entity.Emprestimo;
import dev.joao.projeto.api.emprestimo.record.Endereco;
import org.springframework.data.annotation.Id;

import javax.persistence.Embedded;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ClienteRequestDTO {
    @Id
    @NotBlank(message = "CPF NULL/VAZIO")
    @Pattern(regexp = "[0-9]{11}", message = "CPF INVALIDO")
    private String cpf;
    @NotBlank(message = "NOME NULL/VAZIO")
    private String nome;
    @NotBlank(message = "TELEFONE NULL/VAZIO")
    private String telefone;
    @Embedded
    @NotNull(message = "ENDERECO NULL")
    @Valid
    private Endereco endereco;
    @NotNull(message = "RENDIMENTO MENSAL NULL")
    private BigDecimal rendimentoMensal;
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public ClienteRequestDTO(){

    }

    public ClienteRequestDTO(String cpf, String nome, String telefone, Endereco endereco, BigDecimal rendimentoMensal, List<Emprestimo> emprestimos) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.rendimentoMensal = rendimentoMensal;
        this.emprestimos = emprestimos;
    }

    public Cliente build() {
        Cliente cliente = new Cliente();
        cliente.setCpf(this.cpf);
        cliente.setNome(this.nome);
        cliente.setTelefone(this.telefone);
        cliente.setEndereco(this.endereco);
        cliente.setRendimentoMensal(this.rendimentoMensal);
        cliente.setEmprestimos(this.emprestimos);
        return cliente;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public BigDecimal getRendimentoMensal() {
        return rendimentoMensal;
    }

    public void setRendimentoMensal(BigDecimal rendimentoMensal) {
        this.rendimentoMensal = rendimentoMensal;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }
}
