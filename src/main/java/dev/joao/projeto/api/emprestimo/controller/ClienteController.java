package dev.joao.projeto.api.emprestimo.controller;

import dev.joao.projeto.api.emprestimo.dto.request.ClienteRequestDTO;
import dev.joao.projeto.api.emprestimo.dto.response.ClienteResponseDTO;
import dev.joao.projeto.api.emprestimo.entity.Cliente;
import dev.joao.projeto.api.emprestimo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public void cadastrarCliente(@RequestBody @Valid ClienteRequestDTO clienteRequestDTO) {
        service.cadastrarCliente(clienteRequestDTO.build());
    }

    @GetMapping("/{cpf}")
    public ClienteResponseDTO buscarByCpf(@PathVariable String cpf){
        return new ClienteResponseDTO(service.buscarByCpf(cpf));
    }

    @GetMapping
    public List<ClienteResponseDTO> buscarTodosClientes(){
        List<Cliente> clientes = service.buscarTodosClientes();
        return ClienteResponseDTO.convert(clientes);
    }

    @DeleteMapping("/{cpf}")
    public void deletarCliente(@PathVariable String cpf){
        service.deletarCliente(cpf);
    }

    @PutMapping("/{cpf}")
    public void atualizarCliente(@RequestBody ClienteRequestDTO novosDadosCliente, @PathVariable String cpf){
        service.atualizarCliente(novosDadosCliente.build(), cpf);
    }

}

