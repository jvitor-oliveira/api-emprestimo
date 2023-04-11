package dev.joao.projeto.api.emprestimo.service;

import dev.joao.projeto.api.emprestimo.entity.Cliente;
import dev.joao.projeto.api.emprestimo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public void cadastrarCliente(Cliente cliente) {
        repository.save(cliente);
    }

    public Cliente buscarByCpf(String cpf) {
        return repository.findById(cpf).orElse(null);
    }

    public List<Cliente> buscarTodosClientes() {
        return repository.findAll();
    }

    public void deletarCliente(String cpf) {
        repository.deleteById(cpf);
    }

    public void atualizarCliente(Cliente novosDadosCliente, String cpf) {
        Cliente cliente = repository.findById(cpf).get();
        Cliente.validaDados(cliente, novosDadosCliente);
        novosDadosCliente.setCpf(cpf);
        repository.save(novosDadosCliente);
    }

}

