package dev.joao.projeto.api.emprestimo.dto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import dev.joao.projeto.api.emprestimo.record.Endereco;
import dev.joao.projeto.api.emprestimo.entity.Cliente;
import dev.joao.projeto.api.emprestimo.entity.Emprestimo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ClienteResponseDTO {

    private String nome;
    private String telefone;
    private Endereco endereco;
    private BigDecimal rendimentoMensal;
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public ClienteResponseDTO() {

    }

    public ClienteResponseDTO(String nome, String telefone, Endereco endereco, BigDecimal rendimentoMensal, List<Emprestimo> emprestimos) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.rendimentoMensal = rendimentoMensal;
        this.emprestimos = emprestimos;
    }

    public ClienteResponseDTO(Cliente cliente) {
        this.nome = cliente.getNome();
        this.telefone = cliente.getTelefone();
        this.endereco = cliente.getEndereco();
        this.rendimentoMensal = cliente.getRendimentoMensal();
        this.emprestimos = cliente.getEmprestimos();
    }

    public static List<ClienteResponseDTO> convert(List<Cliente> clientes) {
        return clientes.stream().map(ClienteResponseDTO::new).collect(Collectors.toList());
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public BigDecimal getRendimentoMensal() {
        return rendimentoMensal;
    }
}