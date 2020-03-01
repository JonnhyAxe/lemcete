package com.example.cetelem.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.cetelem.model.Client;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SallesSellRepositoryTest {

	@Autowired
	private ClientRepository repository;

	@Test
	public void testAllMusics() throws Exception {

		List<Client> actualList = new ArrayList<Client>();

		repository.findAll().forEach((c) -> actualList.add(c));
		
		assertFalse(actualList.isEmpty());
		assertEquals(1, actualList.size());
	}
}