package dev.joao.projeto.api.emprestimo.record;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Endereco {
    @NotBlank(message = "LOGRADOURO NULL/VAZIO")
    private String rua;
    @NotBlank(message = "NUMERO NULL/VAZIO")
    private String numero;
    @NotBlank(message = "CEP NULL/VAZIO")
    private String cep;

    public Endereco (){

    }

    public Endereco(Endereco endereco){
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
        this.cep = endereco.getCep();
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String logradouro) {
        this.rua = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
