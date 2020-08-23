package com.cursoteste;

import org.assertj.core.api.Assertions;

//jUnit4
import org.junit.Assert;
import org.junit.Test;

public class PrimeiroTeste {

    @Test
    public void estruturaDeUmTeste(){
        //cenario
        int numero1 = 10, numero2 = 5;

        //execucao
        int resultado = numero1 + numero2;

        //verificacoes
            //dar certo
        Assert.assertEquals(15,resultado);
            //dar erro
        Assert.assertEquals(10,resultado);

    }


    @Test
    public void estruturaDeUmTesteComAssertJ(){
        //cenario
        int numero1 = 10, numero2 = 5;

        //execucao
        int resultado = numero1 + numero2;

        //verificacoes
            //resultado entre
        Assertions.assertThat(resultado).isBetween(14,16);
            //resultado igual
        Assertions.assertThat(resultado).isEqualTo(15);
            //resultado maior que numero informado
        Assertions.assertThat(resultado).isGreaterThan(10);
            //resultado e possitivo
        Assertions.assertThat(resultado).isPositive();


            //minha string e maior que a passada
        Assertions.assertThat("").isGreaterThanOrEqualTo("jessica");

    }
}
