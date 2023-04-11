package dev.joao.projeto.api.emprestimo.entity;

import dev.joao.projeto.api.emprestimo.record.Endereco;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity //Anotacao determina que vai ser criado uma tabela no banco de dados
@Table(name = "TBL_CLIENTE")
public class Cliente {

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
    @Transient
    @OneToMany(mappedBy = "cliente")
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public Cliente() {

    }

    public Cliente(String cpf, String nome, String telefone, Endereco endereco, BigDecimal rendimentoMensal) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = new Endereco(endereco);
        this.rendimentoMensal = rendimentoMensal;
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

    public static Cliente validaDados(Cliente cliente, Cliente novosDadosCliente) {
        if (novosDadosCliente.getNome() == null || novosDadosCliente.getNome().isEmpty()) {
            novosDadosCliente.setNome(cliente.getNome());
        }
        if (novosDadosCliente.getTelefone() == null || novosDadosCliente.getTelefone().isEmpty()) {
            novosDadosCliente.setTelefone(cliente.getTelefone());
        }
        Endereco endereco = new Endereco();
        if (novosDadosCliente.getEndereco().getLogradouro() == null || novosDadosCliente.getEndereco().getLogradouro().isEmpty()) {
            endereco.setLogradouro(cliente.getEndereco().getLogradouro());
        }else{
            endereco.setLogradouro(novosDadosCliente.getEndereco().getLogradouro());
        }
        if (novosDadosCliente.getEndereco().getNumero() == null || novosDadosCliente.getEndereco().getNumero().isEmpty()) {
            endereco.setNumero(cliente.getEndereco().getNumero());
        }else{
            endereco.setNumero(novosDadosCliente.getEndereco().getNumero());
        }
        if (novosDadosCliente.getEndereco().getCep() == null || novosDadosCliente.getEndereco().getCep().isEmpty()) {
            endereco.setCep(cliente.getEndereco().getCep());
        }else{
            endereco.setCep(novosDadosCliente.getEndereco().getCep());
        }
        if (novosDadosCliente.getEndereco().getBairro() == null || novosDadosCliente.getEndereco().getBairro().isEmpty()) {
            endereco.setBairro(cliente.getEndereco().getBairro());
        }else{
            endereco.setBairro(novosDadosCliente.getEndereco().getBairro());
        }
        novosDadosCliente.setEndereco(endereco);
        if (novosDadosCliente.getRendimentoMensal() == null) {
            novosDadosCliente.setRendimentoMensal(cliente.getRendimentoMensal());
        }
        return novosDadosCliente;
    }
}
