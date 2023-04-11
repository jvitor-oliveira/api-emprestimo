package dev.joao.projeto.api.emprestimo.service;

import dev.joao.projeto.api.emprestimo.entity.Emprestimo;
import dev.joao.projeto.api.emprestimo.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository repository;

    public void cadastrarEmprestimo(Emprestimo emprestimo) {
        repository.save(emprestimo);
    }

    public List<Emprestimo> buscarEmprestimosByCpf(String cpf) {
        return repository.findAllByCpfCliente(cpf);
    }

    public List<Emprestimo> buscarEmprestimosById(String cpf, Integer id) {
        return repository.findByCpfClienteAndId(cpf, id);
    }

    public void deletarEmprestimosById(String cpf, Integer id) {
         repository.deleteByCpfClienteAndId(cpf, id);
    }
}
