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

	private static final String FORTHIES = "18,45";
	private static final String MID_AGE = "45,59";
	private static final String EXTREME_AGE = "59,70";
	 
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

			SallesSell comercialSell = SallesSell.builder().name("Kids under 20").comercialSellAgeRanges(FORTHIES)
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
							GeographicalArea.NORTH, FORTHIES);

			System.out.println(sallesSellByCriteria);

		};
	}

	@Bean
	public CommandLineRunner clientSalesDemo(ClientRepository clientRepository,
			SallesSellRepository sallesSellRepository) {
		return (args) -> {

			Client client = Client.builder().firstName("Joao").lastName("Machado").age((short) 35)
					.risKProfile(ClientRiskProfile.LOW).geographicalArea(GeographicalArea.NORTH).build();
			Client client3 = Client.builder().firstName("Luisa").lastName("Sousa").age((short) 33)
					.risKProfile(ClientRiskProfile.LOW).geographicalArea(GeographicalArea.NORTH).build();
			
			Client client2 = Client.builder().firstName("Joao").lastName("Miguel").age((short) 45)
					.risKProfile(ClientRiskProfile.AVERAGE).geographicalArea(GeographicalArea.CENTER).build();

			
			SallesSell activeWorkerComercialSell = SallesSell.builder().name("Active worker").comercialSellAgeRanges(FORTHIES)
					.geographicalArea(GeographicalArea.NORTH).risKProfile(SallesSellRiskProfile.LOW).build();		
			SallesSell passiveNorthWorkerComercialSell = SallesSell.builder().name("Passive worker").comercialSellAgeRanges(MID_AGE)
					.geographicalArea(GeographicalArea.NORTH).risKProfile(SallesSellRiskProfile.HIGH).build();
			SallesSell passiveCenterWorkerComercialSell = SallesSell.builder().name("Passive worker").comercialSellAgeRanges(MID_AGE)
					.geographicalArea(GeographicalArea.NORTH).risKProfile(SallesSellRiskProfile.AVERAGE).build();	
			SallesSell under40ComercialSell = SallesSell.builder().name("Salles under 40").comercialSellAgeRanges(FORTHIES)
					.geographicalArea(GeographicalArea.NORTH).risKProfile(SallesSellRiskProfile.LOW).build();
			SallesSell middleAgeComercialSell = SallesSell.builder().name("Middle age").comercialSellAgeRanges(MID_AGE)
					.geographicalArea(GeographicalArea.NORTH).risKProfile(SallesSellRiskProfile.AVERAGE).build();
			SallesSell extremeAgeComercialSell = SallesSell.builder().name("Extreme age").comercialSellAgeRanges(EXTREME_AGE)
					.geographicalArea(GeographicalArea.NORTH).risKProfile(SallesSellRiskProfile.AVERAGE).build();

			sallesSellRepository.save(under40ComercialSell);
			sallesSellRepository.save(middleAgeComercialSell);
			sallesSellRepository.save(extremeAgeComercialSell);		
			sallesSellRepository.save(activeWorkerComercialSell);
			sallesSellRepository.save(passiveNorthWorkerComercialSell);
			sallesSellRepository.save(passiveCenterWorkerComercialSell);
			
			client.getSallesSell().add(under40ComercialSell);
			client.getSallesSell().add(activeWorkerComercialSell);		
			
			client2.getSallesSell().add(middleAgeComercialSell);
			client2.getSallesSell().add(passiveNorthWorkerComercialSell);
			
			
			client3.getSallesSell().add(under40ComercialSell);

			
			clientRepository.save(client);
			clientRepository.save(client2);
			clientRepository.save(client3);

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
