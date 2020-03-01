package com.example.cetelem.model;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.Test;

public class ClientTest {

	private static final String JOAO_LAST_NAME = "Machado";
	private String JOAO_FIRST_NAME = "João";
	
	@Test
	public void testClientEquality() {
		
		Client client = Client.builder()
					.firstName(JOAO_FIRST_NAME)
					.lastName(JOAO_LAST_NAME)
					.age((short)35)
					.risKProfile(ClientRiskProfile.LOW)
					.geographicalArea(GeographicalArea.NORTH)
					.build();
		
		Client sameclient = Client.builder()
				.firstName(JOAO_FIRST_NAME)
				.lastName(JOAO_LAST_NAME)
				.age((short)35)
				.risKProfile(ClientRiskProfile.LOW)
				.geographicalArea(GeographicalArea.NORTH)
				.build();

		then(client)
			.as("Is the same object")
			.isEqualTo(sameclient);
		then(client.hashCode())
			.as("Has the same hashcode")
			.isEqualTo(sameclient.hashCode());
	}
	
	@Test(expected = RuntimeException.class)
	public void testClientAgeNullCheck() {
		 Client.builder().build();
	}
	
	@Test(expected = RuntimeException.class)
	public void testClientFirstNameNullCheck() {
		 Client.builder().age((short)34).build();
	}
	
	@Test(expected = RuntimeException.class)
	public void testClientRiskProfileNullCheck() {
		 Client.builder().age((short)34).firstName(JOAO_FIRST_NAME).lastName(JOAO_LAST_NAME).build();
	}

	@Test(expected = RuntimeException.class)
	public void testClientGeographicallAreaNullCheck() {
		 Client.builder().age((short)34).firstName(JOAO_FIRST_NAME).lastName(JOAO_LAST_NAME).risKProfile(ClientRiskProfile.LOW).build();
	}
	
}
