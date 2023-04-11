package dev.joao.projeto.api.emprestimo.repository;

import dev.joao.projeto.api.emprestimo.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {

    List<Emprestimo> findAllByCpfCliente (String cpf);
    List<Emprestimo> findByCpfClienteAndId (String cpf, Integer id);
    void deleteByCpfClienteAndId (String cpf, Integer id);
}
