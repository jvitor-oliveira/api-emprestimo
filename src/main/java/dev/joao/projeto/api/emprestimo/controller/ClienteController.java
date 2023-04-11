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
public class ClienteController {

    @Autowired
    private ClienteService service;

    @RequestMapping(method = RequestMethod.POST, value = "/clientes")
    public void cadastrarCliente(@RequestBody @Valid ClienteRequestDTO clienteDTO) {
        service.cadastrarCliente(clienteDTO.build());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/clientes/{cpf}")
    public ClienteResponseDTO buscarByCpf(@PathVariable String cpf){
        return new ClienteResponseDTO(service.buscarByCpf(cpf));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/clientes")
    public List<ClienteResponseDTO> buscarTodosClientes(){
        List<Cliente> clientes = service.buscarTodosClientes();
        return ClienteResponseDTO.convert(clientes);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/clientes/{cpf}")
    public void deletarCliente(@PathVariable String cpf){
        service.deletarCliente(cpf);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/clientes/{cpf}")
    public void atualizarCliente(@RequestBody ClienteRequestDTO novosDadosCliente, @PathVariable String cpf){
        service.atualizarCliente(novosDadosCliente.build(), cpf);
    }

}

