package com.example.cetelem.web;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.cetelem.model.GeographicalArea;
import com.example.cetelem.model.SallesSell;
import com.example.cetelem.model.SallesSellRiskProfile;
import com.example.cetelem.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class ClientControllerTest {

	private static final String RANGE_18_45 = "18,45";
	private static final String CHECK_THAT_SALES_MATCH_IS_RETREIVED = "Check that salles match is retrieved";
	private static final String CHECK_THAT_LIST_CONTENT_IS_CORRECT = "Check that list content is correct";

	private MockMvc mvc;

	@InjectMocks
	private ClientController clientController;

	@Mock
	private ClientService clientService;

	// These object will be magically initialized by the initFields method below.
	private JacksonTester<List<SallesSell>> jsonSallesList;

	@Before
	public void setup() {
		// Initializes the JacksonTester
		JacksonTester.initFields(this, new ObjectMapper());

		// MockMvc standalone approach
		mvc = MockMvcBuilders.standaloneSetup(clientController)
//	              .setControllerAdvice(new RestResponseEntityExceptionHandler(),
//	            		  new PerfectMatchControllerExceptionHandler()
//	            		  )
				.build();
	}

	@Test
	public void getClientSallesById() throws Exception {
		Long clientID = (long) 1001;

		List<SallesSell> expectedListResult = new ArrayList<SallesSell>();

		SallesSell comercialSell = SallesSell.builder().name("People under Forthies")
				.comercialSellAgeRanges(RANGE_18_45).geographicalArea(GeographicalArea.NORTH)
				.risKProfile(SallesSellRiskProfile.LOW).build();

		SallesSell sameComercialSell2 = SallesSell.builder().name("Active Workers").comercialSellAgeRanges(RANGE_18_45)
				.geographicalArea(GeographicalArea.NORTH).risKProfile(SallesSellRiskProfile.LOW).build();

		expectedListResult.add(comercialSell);
		expectedListResult.add(sameComercialSell2);

		given(clientService.getAllSallesSellByClient(clientID)).willReturn(expectedListResult);

		// when
		MockHttpServletResponse response = mvc
				.perform(get("/client/" + clientID + "/salles").contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		then(response.getStatus())
			.as(CHECK_THAT_SALES_MATCH_IS_RETREIVED)
				.isNotNull()
				.isEqualTo(HttpStatus.OK.value());

		then(response.getContentAsString())
			.as(CHECK_THAT_LIST_CONTENT_IS_CORRECT)
				.isEqualTo(jsonSallesList.write(expectedListResult).getJson());
	}
}
