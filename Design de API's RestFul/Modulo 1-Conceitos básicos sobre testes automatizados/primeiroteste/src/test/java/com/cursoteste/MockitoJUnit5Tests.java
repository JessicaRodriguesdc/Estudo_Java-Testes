package com.cursoteste;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MockitoJUnit5Tests {

    @Mock
    List<String> lista;

    @Test
    public void primeiroTesteMockito(){
        //cenario
        //List<String> lista = Mockito.mock(ArrayList.class);
        //Mockito.when(lista.size()).thenReturn(20);

        //execulcao
        // int size = 0;
        // if(1==2){
        //     size = lista.size();
        //     size = lista.size();
        // }
        lista.size();
        lista.add("");

        //verificacao
        InOrder inOrder = Mockito.inOrder(lista);
        inOrder.verify(lista).size();
        inOrder.verify(lista).add("");

        //Assertions.assertThat(size).isEqualTo(20);
            //verifica se chamou o metodo size da lista
        //Mockito.verify(lista,Mockito.times(2)).size();
        //Mockito.verify(lista,Mockito.never()).size();

    }
}
