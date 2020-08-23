package com.cursoteste;

//AssertJ
import org.assertj.core.api.Assertions;

//jUnit4
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;

public class PrimeiroTeste {

//    Calculadora calculadora;
//    int numero1 = 10, numero2 = 5;
//
//    @Before
//    public void setUp(){
//        calculadora = new Calculadora();
//    }
//
//    @Test
//    public void deveSomar2NumerosJUnit(){
//        //cenario
//        int numero1 = 10, numero2 = 5;
//
//        //execucao
//        int resultado = numero1 + numero2;
//
//        //verificacoes
//            //dar certo
//        Assert.assertEquals(15,resultado);
//            //dar erro
//        //Assert.assertEquals(10,resultado);
//
//    }
//
//
//    @Test
//    public void deveSomar2NumerosAssertJ(){
//        //cenario
//        int numero1 = 10, numero2 = 5;
//
//        //execucao
//        int resultado = numero1 + numero2;
//
//        //verificacoes
//            //resultado entre
//        Assertions.assertThat(resultado).isBetween(14,16);
//            //resultado igual
//        Assertions.assertThat(resultado).isEqualTo(15);
//            //resultado maior que numero informado
//        Assertions.assertThat(resultado).isGreaterThan(10);
//            //resultado e possitivo
//        Assertions.assertThat(resultado).isPositive();
//
//            //string e maior que a passada
//        //Assertions.assertThat("").isGreaterThanOrEqualTo("jessica");
//    }
//
//    @Test
//    public void deveSomar2Numeros(){
//        //cenario
//        int numero1 = 10, numero2 = 5;
//
//        //execucao
//        int resultado = calculadora.somar(numero1 , numero2);
//
//        //verificacoes
//        Assertions.assertThat(resultado).isEqualTo(15);
//    }
//
//    @Test(expected = RuntimeException.class)
//    public void naoDeveSomar2NumerosNegativos(){
//        //cenario
//        int num1 = -10, num2 = 5;
//
//        //execucao
//        calculadora.somar(num1 , num2);
//    }
//
//    @Test
//    public void deveSubtrair2Numeros(){
//        //cenario
//
//        //execucao
//        int resultado = calculadora.subtrair(numero1 , numero2);
//
//        //verificacoes
//        Assertions.assertThat(resultado).isEqualTo(5);
//    }
//
//    @Test
//    public void deveMultiplicar2Numeros(){
//        //cenario
//
//        //execucao
//        int resultado = calculadora.multiplicar(numero1 , numero2);
//
//        //verificacoes
//        Assertions.assertThat(resultado).isEqualTo(50);
//    }
//
//    @Test
//    public void deveDividir2Numeros(){
//        //cenario
//
//        //execucao
//        float resultado = calculadora.dividir(numero1 , numero2);
//
//        //verificacoes
//        Assertions.assertThat(resultado).isEqualTo(2);
//    }
//
//    @Test(expected = ArithmeticException.class)
//    public void naoDeveDividirPorZero(){
//        //cenario
//        int numero1 = 10, numero2 = 0;
//
//        //execucao
//        calculadora.dividir(numero1 , numero2);
//    }
//}
//
//
//class Calculadora{
//
//    int somar(int num, int num2){
//        if(num < 0 || num2 < 0){
//            throw new RuntimeException("Nao e permitido somar numeros negativos.");
//        }
//        return num + num2;
//    }
//
//    int subtrair(int num, int num2){
//        return num - num2;
//    }
//
//    int multiplicar(int num, int num2){
//        return num * num2;
//    }
//
//    float dividir(int num, int num2){
////        if(num2 < 0){
////            throw new RuntimeException("Nao e permitido divisao com 0.");
////        }
//        return num / num2;
//    }

}
