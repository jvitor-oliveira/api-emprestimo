package dev.joao.projeto.api.emprestimo.controller;

import dev.joao.projeto.api.emprestimo.dto.request.EmprestimoRequestDTO;
import dev.joao.projeto.api.emprestimo.dto.response.ClienteResponseDTO;
import dev.joao.projeto.api.emprestimo.dto.response.EmprestimoResponseDTO;
import dev.joao.projeto.api.emprestimo.entity.Emprestimo;
import dev.joao.projeto.api.emprestimo.enums.Relacionamento;
import dev.joao.projeto.api.emprestimo.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class EmprestimoController {

    @Autowired
    private EmprestimoService service;
    @Autowired
    private ClienteController clienteController;

    @PostMapping("/{cpf}/emprestimos")
    public void cadastrarEmprestimo(@RequestBody @Valid EmprestimoRequestDTO emprestimo, @PathVariable String cpf) {
        ClienteResponseDTO cliente = clienteController.buscarByCpf(cpf);

        if (cliente != null) {

            // Set cpf do cliente no emprestimo
            emprestimo.setCpfCliente(cpf);

            // Calcula valor final do emprestimo
            List<Emprestimo> emprestimos = service.buscarEmprestimosByCpf(cpf);
            Relacionamento relacionamento = emprestimo.getRelacionamento();
            BigDecimal valorFinal = relacionamento.calculaValorFinal(emprestimo.getValorInicial(), emprestimos);
            emprestimo.setValorFinal(valorFinal);

            // Adicionar o novo emprestimo a lista de emprestimo do cliente
            emprestimos.add(emprestimo.build());
            cliente.setEmprestimos(emprestimos);

            //Calcula o valor total de todos os emprestimos
            BigDecimal total = BigDecimal.ZERO;
            for (Emprestimo emp : emprestimos) {
                total = total.add(emp.getValorFinal());
            }

            //Valida se o cliente pode abrir emprestimo de acordo com o seu rendimento mensal
            BigDecimal rendimentoMensal = cliente.getRendimentoMensal();
            if (rendimentoMensal.multiply(BigDecimal.TEN).compareTo(total) < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "VALOR TOTAL DOS EMPRESTIMOS 10X MAIOR QUE O RENDIMENTO MENSAL DO CLIENTE");
            } else {
                service.cadastrarEmprestimo(emprestimo.build());
            }

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CLIENTE NAO ENCONTRADO");
        }
    }

    @GetMapping ("/{cpf}/emprestimos")
    public List<EmprestimoResponseDTO> buscarEmprestimosByCpf(@PathVariable String cpf){
        List<Emprestimo> emprestimos = service.buscarEmprestimosByCpf(cpf);
        return EmprestimoResponseDTO.convert(emprestimos);
    }

    @GetMapping ("/{cpf}/emprestimos/{id}")
    public List<EmprestimoResponseDTO> buscarEmprestimosById(@PathVariable String cpf, @PathVariable Integer id){
        List<Emprestimo> emprestimos = service.buscarEmprestimosById(cpf, id);
        return EmprestimoResponseDTO.convert(emprestimos);
    }

    @Transactional
    @DeleteMapping("/{cpf}/emprestimos/{id}")
    public void deletarEmprestimosById(@PathVariable String cpf, @PathVariable Integer id){
        service.deletarEmprestimosById(cpf, id);
    }


}
