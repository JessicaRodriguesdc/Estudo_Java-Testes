package com.cursoteste;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CadastroPessoaJUnit5Teste {

    @Test
    @DisplayName("Deve criar o Cadastro de pessoas.")
    public void deveCriarOCadastroDePessoas(){
        //cenario e execulcao
        CadastroPessoa cadastro = new CadastroPessoa();

        //verificacao
        Assertions.assertThat(cadastro.getPessoas()).isEmpty();
    }

    @Test
    @DisplayName("Deve adicionar uma pessoas.")
    public void deveAdcionarUmaPessoa(){
        //cenario
        CadastroPessoa cadastroPessoa = new CadastroPessoa();
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Jessica");

        //execulcao
        cadastroPessoa.adicionar(pessoa);

        //verificacao
        Assertions.assertThat(cadastroPessoa.getPessoas())
                .isNotEmpty()
                .hasSize(1)
                .contains(pessoa);
    }

    @Test
    @DisplayName("Nao deve adicionar uma pessoas com nome vazio.")
    public void naoDeveAdcionarPessoaComNomeVazio(){
        //cenario
        CadastroPessoa cadastroPessoa = new CadastroPessoa();
        Pessoa pessoa = new Pessoa();

        //execulcao
        org.junit.jupiter.api.Assertions
                .assertThrows(PessoaSemNotException.class,
                        ()-> cadastroPessoa.adicionar(pessoa));

    }

    @Test
    @DisplayName("Deve remover uma pessoas.")
    public void deveRemoverUmaPessoa(){
        //cenario
        CadastroPessoa cadastroPessoa = new CadastroPessoa();
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Jessica");
        cadastroPessoa.adicionar(pessoa);

        //execulcao
        cadastroPessoa.remover(pessoa);

        //verificacao
        Assertions.assertThat(cadastroPessoa.getPessoas()).isEmpty();
    }


    @Test
    @DisplayName("Deve lancar um erro ao tentar remover uma pessoas inexistente.")
    public void deveLancarErroAoTentarRemoverUmaPessoaInexistente() {
        //cenario
        CadastroPessoa cadastroPessoa = new CadastroPessoa();
        Pessoa pessoa = new Pessoa();

        //execulcao
        org.junit.jupiter.api.Assertions
                .assertThrows(CadastroVazioException.class,
                        ()-> cadastroPessoa.remover(pessoa));
    }
}
