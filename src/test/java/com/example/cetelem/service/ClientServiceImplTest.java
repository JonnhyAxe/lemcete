package com.example.cetelem.service;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.cetelem.model.Client;
import com.example.cetelem.model.ClientRiskProfile;
import com.example.cetelem.model.GeographicalArea;
import com.example.cetelem.model.SallesSell;
import com.example.cetelem.model.SallesSellRiskProfile;
import com.example.cetelem.repository.ClientRepository;

@RunWith(MockitoJUnitRunner.class)

public class ClientServiceImplTest {

	private static final String RANGE_18_45 = "18,45";

	@InjectMocks
	private ClientServiceImpl clientService;

	@Mock
	private ClientRepository clientRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testEmptyListOfSallesSellByClientId() {
		List<SallesSell> expectedListResult = new ArrayList<SallesSell>();

		Client joao = Client.builder().firstName("Joao").lastName("Machado").age((short) 35)
				.risKProfile(ClientRiskProfile.LOW).geographicalArea(GeographicalArea.NORTH).build();

		when(clientRepository.findById(joao.getId())).thenReturn(Optional.of(joao));

		// When
		List<SallesSell> actualResult = clientService.getAllSallesSellByClient(joao);

		// Then
		then(actualResult).as("List is not Empty").isEqualTo(expectedListResult);
	}

	@Test
	public void testFindListOfSallesSellByClientId() {
		List<SallesSell> expectedListResult = new ArrayList<SallesSell>();

		Client joao = Client.builder().firstName("Joao").lastName("Machado").age((short) 35)
				.risKProfile(ClientRiskProfile.LOW).geographicalArea(GeographicalArea.NORTH).build();

		SallesSell comercialSell = SallesSell.builder().name("People under Forthies")
				.comercialSellAgeRanges(RANGE_18_45).geographicalArea(GeographicalArea.NORTH)
				.risKProfile(SallesSellRiskProfile.LOW).build();

		SallesSell sameComercialSell2 = SallesSell.builder().name("Active Workers").comercialSellAgeRanges(RANGE_18_45)
				.geographicalArea(GeographicalArea.NORTH).risKProfile(SallesSellRiskProfile.LOW).build();

		expectedListResult.add(comercialSell);
		expectedListResult.add(sameComercialSell2);

		joao.getSallesSell().add(comercialSell);
		joao.getSallesSell().add(sameComercialSell2);

		when(clientRepository.findById(joao.getId())).thenReturn(Optional.of(joao));

		// When
		List<SallesSell> actualResult = clientService.getAllSallesSellByClient(joao);

		// Then
		then(actualResult).as("List is Empty").isEqualTo(expectedListResult);
	}

	@Test
	public void checkEntityArgumentNotExists() {
		// Given

		Client joao = Client.builder().firstName("Joao").lastName("Machado").age((short) 35)
				.risKProfile(ClientRiskProfile.LOW).geographicalArea(GeographicalArea.NORTH).build();

		when(clientRepository.findById(joao.getId())).thenThrow(new IllegalArgumentException());

		// Then - IllegalArgumentException

		assertThrows(IllegalArgumentException.class, () -> {
			clientService.getAllSallesSellByClient(joao);
		});

	}

}
