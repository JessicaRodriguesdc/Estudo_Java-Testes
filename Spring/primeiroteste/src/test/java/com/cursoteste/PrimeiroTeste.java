package com.cursoteste;

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
}
