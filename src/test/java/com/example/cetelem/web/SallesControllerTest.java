package com.example.cetelem.web;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.HashSet;
import java.util.Set;

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

import com.example.cetelem.model.Client;
import com.example.cetelem.model.ClientRiskProfile;
import com.example.cetelem.model.GeographicalArea;
import com.example.cetelem.model.SallesSell;
import com.example.cetelem.model.SallesSellRiskProfile;
import com.example.cetelem.service.SallesSellService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class SallesControllerTest {

	private static final String RANGE_18_45 = "18,45";

	private static final String CHECK_THAT_CLIENTS_LIST_IS_RETREIVED = "Check that salles match is retrieved";
	private static final String CHECK_THAT_LIST_CONTENT_IS_CORRECT = "Check that list content is correct";

	private MockMvc mvc;

	@InjectMocks
	private SallesController sallesController;

	@Mock
	private SallesSellService sallesSellService;

	// These object will be magically initialized by the initFields method below.
	private JacksonTester<Set<Client>> jsonClientSet;

	@Before
	public void setup() {
		// Initializes the JacksonTester
		JacksonTester.initFields(this, new ObjectMapper());

		// MockMvc standalone approach
		mvc = MockMvcBuilders.standaloneSetup(sallesController)
//	              .setControllerAdvice(new RestResponseEntityExceptionHandler(),
//	            		  new PerfectMatchControllerExceptionHandler()
//	            		  )
				.build();
	}

	@Test
//	@DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
	public void getClientSallesById() throws Exception {

		Set<Client> expectedlistClients = new HashSet<Client>();

		Client joao = Client.builder().firstName("Joao").lastName("Machado").age((short) 35)
				.risKProfile(ClientRiskProfile.LOW).geographicalArea(GeographicalArea.NORTH).build();
		Client luisa = Client.builder().firstName("Luisa").lastName("Sousa").age((short) 33)
				.risKProfile(ClientRiskProfile.LOW).geographicalArea(GeographicalArea.NORTH).build();

		expectedlistClients.add(joao);
		expectedlistClients.add(luisa);

		SallesSell comercialSell = SallesSell.builder()
				.geographicalArea(GeographicalArea.NORTH)
				.risKProfile(SallesSellRiskProfile.LOW).build();

		given(sallesSellService.getAllClientBySallesSell(comercialSell)).willReturn(expectedlistClients);

		// when
		MockHttpServletResponse response = mvc.perform(
				get("/salles/clients/criteria?risKProfile=LOW&geographicalArea=NORTH&comercialSellAgeRanges=18,45")
						.contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		then(response.getStatus()).as(CHECK_THAT_CLIENTS_LIST_IS_RETREIVED).isNotNull()
				.isEqualTo(HttpStatus.OK.value());

		then(response.getContentAsString()).as(CHECK_THAT_LIST_CONTENT_IS_CORRECT)
				.isEqualTo(jsonClientSet.write(expectedlistClients).getJson());
	}

}
