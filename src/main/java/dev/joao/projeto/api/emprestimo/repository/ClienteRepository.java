package dev.joao.projeto.api.emprestimo.repository;

import dev.joao.projeto.api.emprestimo.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {


}
