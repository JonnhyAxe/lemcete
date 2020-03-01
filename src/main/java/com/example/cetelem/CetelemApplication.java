package com.example.cetelem;

import java.awt.print.Book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.cetelem.model.Client;
import com.example.cetelem.model.ClientRiskProfile;
import com.example.cetelem.model.GeographicalArea;
import com.example.cetelem.repository.ClientRepository;

@SpringBootApplication
public class CetelemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CetelemApplication.class, args);
	}

	 @Bean
	    public CommandLineRunner bookDemo(ClientRepository clientRepository) {
	        return (args) -> {

	        	Client client = Client.builder()
						.firstName("Joao")
						.lastName("Machado")
						.age((short)35)
						.risKProfile(ClientRiskProfile.LOW)
						.geographicalArea(GeographicalArea.NORTH)
						.build();
	        	
	            // create books
	        	clientRepository.save(client);
	        	
	            // fetch all books
	            System.out.println("Client found with findAll():");
	            System.out.println("---------------------------");
	            for (Client clientLoaded : clientRepository.findAll()) {
	                System.out.println(clientLoaded);
	            }
	            System.out.println();

	        };
	    }
	 
}
