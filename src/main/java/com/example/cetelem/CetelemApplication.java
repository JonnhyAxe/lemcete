package com.example.cetelem;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.cetelem.model.Client;
import com.example.cetelem.model.ClientRiskProfile;
import com.example.cetelem.model.GeographicalArea;
import com.example.cetelem.model.SallesSell;
import com.example.cetelem.model.SallesSellRiskProfile;
import com.example.cetelem.repository.ClientRepository;
import com.example.cetelem.repository.SallesSellRepository;

@SpringBootApplication
public class CetelemApplication {

	private static final String UNDER_FORTHY = "18,40";

	public static void main(String[] args) {
		SpringApplication.run(CetelemApplication.class, args);
	}

//	@Bean
	public CommandLineRunner clientDemo(ClientRepository clientRepository) {
		return (args) -> {

			Client client = Client.builder().firstName("Joao").lastName("Machado").age((short) 35)
					.risKProfile(ClientRiskProfile.LOW).geographicalArea(GeographicalArea.NORTH).build();

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

//	@Bean
	public CommandLineRunner salesDemo(SallesSellRepository sallesSellRepository) {
		return (args) -> {

			SallesSell comercialSell = SallesSell.builder().name("Kids under 20").comercialSellAgeRanges(UNDER_FORTHY)
					.geographicalArea(GeographicalArea.NORTH).risKProfile(SallesSellRiskProfile.LOW).build();

			// create books
			sallesSellRepository.save(comercialSell);

			// fetch all books
			System.out.println("SallesSell found with findAll():");
			System.out.println("---------------------------");
			for (SallesSell comercialSellLoaded : sallesSellRepository.findAll()) {
				System.out.println(comercialSellLoaded);
			}
			System.out.println();
			System.out
					.println("SallesSell found with findByRisKProfileAndGeographicalAreaAndComercialSellAgeRanges():");

			List<SallesSell> sallesSellByCriteria = sallesSellRepository
					.findByRisKProfileAndGeographicalAreaAndComercialSellAgeRanges(SallesSellRiskProfile.LOW,
							GeographicalArea.NORTH, UNDER_FORTHY);

			System.out.println(sallesSellByCriteria);

		};
	}

	@Bean
	public CommandLineRunner clientSalesDemo(ClientRepository clientRepository,
			SallesSellRepository sallesSellRepository) {
		return (args) -> {

			Client client = Client.builder().firstName("Joao").lastName("Machado").age((short) 35)
					.risKProfile(ClientRiskProfile.LOW).geographicalArea(GeographicalArea.NORTH).build();

			Client client2 = Client.builder().firstName("Joao").lastName("Miguel").age((short) 35)
					.risKProfile(ClientRiskProfile.LOW).geographicalArea(GeographicalArea.NORTH).build();

			

			SallesSell comercialSell = SallesSell.builder().name("Salles under 40").comercialSellAgeRanges(UNDER_FORTHY)
					.geographicalArea(GeographicalArea.NORTH).risKProfile(SallesSellRiskProfile.LOW).build();


			sallesSellRepository.save(comercialSell);
			
			client.getSallesSell().add(comercialSell);
			client2.getSallesSell().add(comercialSell);

			clientRepository.save(client);
			clientRepository.save(client2);

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
