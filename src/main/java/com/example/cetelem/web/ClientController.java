package com.example.cetelem.web;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cetelem.model.SallesSell;
import com.example.cetelem.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

	private static final String CLIENT_NOT_FOUND_FOR_THE_GIVEN_ID = "Client not found for the given id : ";
	private ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	  @GetMapping(path = "/{id}/salles")
	  public List<SallesSell> getClientSallesById(@PathVariable("id") Long id) {
	    return Optional.ofNullable(clientService.getAllSallesSellByClient(id)).orElseThrow(
	        () -> new RuntimeException(CLIENT_NOT_FOUND_FOR_THE_GIVEN_ID + id));
	  }

	
}
