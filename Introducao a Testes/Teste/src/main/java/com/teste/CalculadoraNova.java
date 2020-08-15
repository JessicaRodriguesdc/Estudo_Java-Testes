package com.teste;

public class CalculadoraNova {
	/*
	public int somar(int valorA, int valorB) {
		return valorA+valorB;
	}
	*/
	public int somar(int ...valores) {
		int soma = 0;
		for(int valor: valores) {
			soma+=valor;
		}
		return soma;
	}
}
