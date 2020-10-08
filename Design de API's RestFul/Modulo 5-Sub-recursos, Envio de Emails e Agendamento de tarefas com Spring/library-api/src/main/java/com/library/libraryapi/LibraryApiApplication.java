package com.library.libraryapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Scanner;

@SpringBootApplication
//@EnableScheduling
public class LibraryApiApplication {

	//cria uma instancia para servir a toda a aplicacao
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

//	@Scheduled(cron = "0 21 14 1/1 * ?")
//	public void testeAgendamentoTarefas(){
//		System.out.println("AGENDAMENTO DE TAREFAS FUNCIONADO COM SUCESSO");
//	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryApiApplication.class, args);
	}
}
