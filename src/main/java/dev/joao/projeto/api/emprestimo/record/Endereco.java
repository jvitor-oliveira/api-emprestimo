package dev.joao.projeto.api.emprestimo.record;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Endereco {
    @NotBlank(message = "LOGRADOURO NULL/VAZIO")
    private String logradouro;
    @NotBlank(message = "NUMERO NULL/VAZIO")
    private String numero;
    @NotBlank(message = "BAIRRO NULL/VAZIO")
    private String bairro;
    @NotBlank(message = "CEP NULL/VAZIO")
    private String cep;

    public Endereco (){

    }

    public Endereco(Endereco endereco){
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
        this.bairro = endereco.getBairro();
        this.cep = endereco.getCep();
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
