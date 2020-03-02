package com.example.cetelem.service;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.example.cetelem.repository.SallesSellRepository;


@RunWith(MockitoJUnitRunner.class)
public class SalesSelleIImplTest {

    private static final String RANGE_18_45 = "18,45";

	@InjectMocks 
    private SalesSelleIImpl salesSell;
	
	@Mock
	private SallesSellRepository mockRepository;

	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testEmptySallesSell() {
		// Given
		List<SallesSell> list = createEmptySallesSellList();

		when(mockRepository.findByRisKProfileAndGeographicalAreaAndComercialSellAgeRanges(SallesSellRiskProfile.LOW,
				GeographicalArea.NORTH, RANGE_18_45)).thenReturn(list);

		// when
		List<SallesSell> listResult = mockRepository.findByRisKProfileAndGeographicalAreaAndComercialSellAgeRanges(SallesSellRiskProfile.LOW, GeographicalArea.NORTH, RANGE_18_45);
		
		// Then
		then(list)
      		.as("List is not Empty")
      		.isEqualTo(listResult);
	}

	private List<SallesSell> createEmptySallesSellList() {
		List<SallesSell> list = new ArrayList<SallesSell>();

		SallesSell comercialSell = SallesSell.builder().name("Kids under 20").comercialSellAgeRanges("18,19")
				.geographicalArea(GeographicalArea.NORTH).risKProfile(SallesSellRiskProfile.LOW).build();

		list.add(comercialSell);
		return list;
	}
	
	@Test
	public void testSallesSellDistinctClients() {
		// Given
		List<SallesSell> list = new ArrayList<SallesSell>();
		Set<Client> expectedlistClients = new HashSet<Client>();

		Client joao = Client.builder().firstName("Joao").lastName("Machado").age((short) 35)
				.risKProfile(ClientRiskProfile.LOW).geographicalArea(GeographicalArea.NORTH).build();
		Client luisa = Client.builder().firstName("Luisa").lastName("Sousa").age((short) 33)
				.risKProfile(ClientRiskProfile.LOW).geographicalArea(GeographicalArea.NORTH).build();
		
		expectedlistClients.add(joao);
		expectedlistClients.add(luisa);

		
		SallesSell comercialSell = SallesSell.builder().name("People under Forthies").comercialSellAgeRanges(RANGE_18_45)
				.geographicalArea(GeographicalArea.NORTH).risKProfile(SallesSellRiskProfile.LOW).build();
		comercialSell.getClients().add(joao);
		comercialSell.getClients().add(luisa);
		
		SallesSell sameComercialSell2 = SallesSell.builder().name("Active Workers").comercialSellAgeRanges(RANGE_18_45)
				.geographicalArea(GeographicalArea.NORTH).risKProfile(SallesSellRiskProfile.LOW).build();
		sameComercialSell2.getClients().add(joao);

		list.add(comercialSell);
		list.add(sameComercialSell2);

		when(mockRepository.findByRisKProfileAndGeographicalAreaAndComercialSellAgeRanges(SallesSellRiskProfile.LOW,
				GeographicalArea.NORTH, RANGE_18_45)).thenReturn(list);

		// when
		SallesSell sallesSell = SallesSell.builder()
		.name("Kids under 20").comercialSellAgeRanges(RANGE_18_45)
		.geographicalArea(GeographicalArea.NORTH).risKProfile(SallesSellRiskProfile.LOW).build();
		
		Set<Client> listResult = salesSell.getAllClientBySallesSell(sallesSell);
		
		// Then
		then(listResult)
      		.as("Collections are not the same")
      		.isEqualTo(expectedlistClients);
	}
	

}
