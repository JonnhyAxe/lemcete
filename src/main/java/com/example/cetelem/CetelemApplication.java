package com.example.cetelem;

import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

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

	@Bean
	@Profile("dev")
	public CommandLineRunner clientSalesDemo(ClientRepository clientRepository,
			SallesSellRepository sallesSellRepository) {
		return (args) -> {
			System.out.println("Demo");

			createClientsAndSells(clientRepository, sallesSellRepository);

			// fetch all books
			System.out.println("Client found with findAll():");
			System.out.println("---------------------------");
			for (Client clientLoaded : clientRepository.findAll()) {

				System.out.println(clientLoaded);
			}
			System.out.println();

		};
	}

	private void createClientsAndSells(ClientRepository clientRepository, SallesSellRepository sallesSellRepository) {
		Client client = Client.builder().firstName("Joao").lastName("Machado").age((short) 35)
				.risKProfile(ClientRiskProfile.LOW).geographicalArea(GeographicalArea.NORTH).build();
		Client client3 = Client.builder().firstName("Luisa").lastName("Sousa").age((short) 33)
				.risKProfile(ClientRiskProfile.LOW).geographicalArea(GeographicalArea.NORTH).build();

		Client client2 = Client.builder().firstName("Joao").lastName("Miguel").age((short) 45)
				.risKProfile(ClientRiskProfile.AVERAGE).geographicalArea(GeographicalArea.CENTER).build();

		SallesSell activeWorkerComercialSell = SallesSell.builder().name("Active worker")
				.comercialSellAgeRanges(FORTHIES).geographicalArea(GeographicalArea.NORTH)
				.risKProfile(SallesSellRiskProfile.LOW).build();
		SallesSell passiveNorthWorkerComercialSell = SallesSell.builder().name("Passive worker")
				.comercialSellAgeRanges(MID_AGE).geographicalArea(GeographicalArea.NORTH)
				.risKProfile(SallesSellRiskProfile.HIGH).build();
		SallesSell passiveCenterWorkerComercialSell = SallesSell.builder().name("Passive worker")
				.comercialSellAgeRanges(MID_AGE).geographicalArea(GeographicalArea.NORTH)
				.risKProfile(SallesSellRiskProfile.AVERAGE).build();
		SallesSell under40ComercialSell = SallesSell.builder().name("Salles under 40")
				.comercialSellAgeRanges(FORTHIES).geographicalArea(GeographicalArea.NORTH)
				.risKProfile(SallesSellRiskProfile.LOW).build();
		SallesSell middleAgeComercialSell = SallesSell.builder().name("Middle age").comercialSellAgeRanges(MID_AGE)
				.geographicalArea(GeographicalArea.NORTH).risKProfile(SallesSellRiskProfile.AVERAGE).build();
		SallesSell extremeAgeComercialSell = SallesSell.builder().name("Extreme age")
				.comercialSellAgeRanges(EXTREME_AGE).geographicalArea(GeographicalArea.NORTH)
				.risKProfile(SallesSellRiskProfile.AVERAGE).build();

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
	}
	
	@Bean
	@Profile("PROD")
	public CommandLineRunner clientSalesPROD(ClientRepository clientRepository,
			SallesSellRepository sallesSellRepository) {
		return (args) -> {
			System.out.println("PROD");

			createClientsAndSells(clientRepository, sallesSellRepository);

			// fetch all books
			System.out.println("Client found with findAll():");
			System.out.println("---------------------------");
			for (Client clientLoaded : clientRepository.findAll()) {

				System.out.println(clientLoaded);
			}
			System.out.println();
			createOneMillionClients(clientRepository);
			System.out.println("Client generation Comnpleted !!!");

		};
	}

	private void createOneMillionClients(ClientRepository clientRepository) {

		for (int i = 0; i < 1000000; i++) {
			clientRepository.save(createRandonmClient());
		}

	}

	private Client createRandonmClient() {
		String firstName = createRandomAlphabetic();
		String lastName = createRandomAlphabetic();
		int age = getRandomNumberInRange(18, 70);
		ClientRiskProfile clientRiskProfile = randomEnum(ClientRiskProfile.class);
		GeographicalArea geographicalArea = randomEnum(GeographicalArea.class);

		return Client.builder().firstName(firstName).lastName(lastName).age((short)age).risKProfile(clientRiskProfile)
				.geographicalArea(geographicalArea).build();
	}

	private String createRandomAlphabetic() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedString;
	}

	public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
		Random random = new Random();
		
		int x = random.nextInt(clazz.getEnumConstants().length);
		return clazz.getEnumConstants()[x];
	}
	
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
