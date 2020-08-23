package com.cursoteste;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class PrimeiroJUnit5Teste {

    @Mock
    Calculadora calculadora;
    int numero1 = 10, numero2 = 5;

    @BeforeEach
    public void setUp(){
        calculadora = new Calculadora();
    }

    @Test
    public void deveSomar2Numeros(){
        //cenario
        int numero1 = 10, numero2 = 5;

        //execucao
        int resultado = calculadora.somar(numero1 , numero2);

        //verificacoes
        Assertions.assertThat(resultado).isEqualTo(15);
    }

    @Test
    public void naoDeveSomar2NumerosNegativos(){
        //cenario
        int num1 = -10, num2 = 5;

        //execucao
        org.junit.jupiter.api.Assertions
                .assertThrows(RuntimeException.class,
                        ()-> calculadora.somar(num1 , num2) );
    }

    @Test
    public void deveSubtrair2Numeros(){
        //cenario

        //execucao
        int resultado = calculadora.subtrair(numero1 , numero2);

        //verificacoes
        Assertions.assertThat(resultado).isEqualTo(5);
    }

    @Test
    public void deveMultiplicar2Numeros(){
        //cenario

        //execucao
        int resultado = calculadora.multiplicar(numero1 , numero2);

        //verificacoes
        Assertions.assertThat(resultado).isEqualTo(50);
    }

    @Test
    public void deveDividir2Numeros(){
        //cenario

        //execucao
        float resultado = calculadora.dividir(numero1 , numero2);

        //verificacoes
        Assertions.assertThat(resultado).isEqualTo(2);
    }

    @Test
    public void naoDeveDividirPorZero(){
        //cenario
        int numero1 = 10, numero2 = 0;

        //execucao
        org.junit.jupiter.api.Assertions
                .assertThrows(ArithmeticException.class,
                        ()-> calculadora.dividir(numero1 , numero2));

    }
}


class Calculadora{

    int somar(int num, int num2){
        if(num < 0 || num2 < 0){
            throw new RuntimeException("Nao e permitido somar numeros negativos.");
        }
        return num + num2;
    }

    int subtrair(int num, int num2){
        return num - num2;
    }

    int multiplicar(int num, int num2){
        return num * num2;
    }

    float dividir(int num, int num2){
        return num / num2;
    }
}
