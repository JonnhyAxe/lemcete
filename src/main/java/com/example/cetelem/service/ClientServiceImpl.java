package com.example.cetelem.service;

import java.util.List;

import com.example.cetelem.model.Client;
import com.example.cetelem.model.SallesSell;
import com.example.cetelem.repository.ClientRepository;

public class ClientServiceImpl implements ClientService {

	private ClientRepository clientRepository;

	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public List<SallesSell> getAllSallesSellByClient(Client client) {
//		return clientRepository.getAllByClient(client);
		return null;
	}

}
