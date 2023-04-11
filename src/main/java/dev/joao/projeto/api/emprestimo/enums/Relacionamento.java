package dev.joao.projeto.api.emprestimo.enums;

import dev.joao.projeto.api.emprestimo.entity.Emprestimo;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public enum Relacionamento {

        OURO(1) {
            @Override
            public BigDecimal calculaValorFinal(BigDecimal valorInicial, List<Emprestimo> emprestimos) {
                if (emprestimos.size() <= 1){
                    BigDecimal fatorMultiplicador = new BigDecimal(1.2);
                    return valorInicial.multiply(fatorMultiplicador, MathContext.DECIMAL32);
                }else{
                    BigDecimal fatorMultiplicador = new BigDecimal(1.3);
                    return valorInicial.multiply(fatorMultiplicador, MathContext.DECIMAL32);
                }

            }
        },

        PRATA(2) {
            @Override
            public BigDecimal calculaValorFinal(BigDecimal valorInicial, List<Emprestimo> emprestimos) {
                if (valorInicial.compareTo(BigDecimal.valueOf(5000)) > 0){
                    BigDecimal fatorMultiplicador = new BigDecimal(1.4);
                    return valorInicial.multiply(fatorMultiplicador, MathContext.DECIMAL32);
                }else{
                    BigDecimal fatorMultiplicador = new BigDecimal(1.6);
                    return valorInicial.multiply(fatorMultiplicador, MathContext.DECIMAL32);
                }
            }
        },

        BRONZE(3) {
            @Override
            public BigDecimal calculaValorFinal(BigDecimal valorInicial, List<Emprestimo> emprestimos) {
                BigDecimal fatorMultiplicador = new BigDecimal(1.8);
                return valorInicial.multiply(fatorMultiplicador, MathContext.DECIMAL32);
            }
        };

        private int codigo;

        private Relacionamento(int codigo) {

            this.codigo = codigo;
        }

        public int getCodigo() {
            return this.codigo;
        }

        public abstract BigDecimal calculaValorFinal(BigDecimal valorInicial, List<Emprestimo> emprestimos);


}
