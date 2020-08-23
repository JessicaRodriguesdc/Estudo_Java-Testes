package com.cursoteste;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class MockitoTests {

    @Test
    public void primeiroTesteMockito(){
        //cenario
        List<String> lista = Mockito.mock(ArrayList.class);
        Mockito.when(lista.size()).thenReturn(20);

        //execulcao
        int size = lista.size();

        //verificacao
        Assertions.assertThat(size).isEqualTo(20);
    }
}
