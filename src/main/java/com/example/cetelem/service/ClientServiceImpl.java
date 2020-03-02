package com.example.cetelem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.cetelem.model.Client;
import com.example.cetelem.model.SallesSell;
import com.example.cetelem.repository.ClientRepository;

public class ClientServiceImpl implements ClientService {

	private ClientRepository clientRepository;

	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public List<SallesSell> getAllSallesSellByClient(Client clientToSearch) {
		Optional<Client> clientResult = clientRepository.findById(clientToSearch.getId());
	    
		Client client = clientResult.orElseThrow(() -> new RuntimeException("Client Id Must not be null"));

		return new ArrayList<SallesSell>(client.getSallesSell());
	}

}
