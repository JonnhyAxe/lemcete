package com.example.cetelem.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.cetelem.model.Client;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class SallesSellRepositoryTest {

	@Autowired
	private ClientRepository repository;

	@DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
	@Test
	public void testAllClients() throws Exception {

		List<Client> actualList = new ArrayList<Client>();

		repository.findAll().forEach((c) -> actualList.add(c));

		assertFalse(actualList.isEmpty());
		assertEquals(3, actualList.size());
	}
	
	@DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
	@Test(expected = RuntimeException.class)
	public void testFindSallesWithNonExisitngID() throws Exception {

		Optional<Client> clientOptional = repository.findById(Long.MAX_VALUE);
		Client cls = clientOptional.orElseThrow(() -> new RuntimeException());
	}
	
}
