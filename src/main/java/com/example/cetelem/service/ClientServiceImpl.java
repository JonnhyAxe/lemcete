package com.example.cetelem.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.cetelem.model.Client;
import com.example.cetelem.model.SallesSell;
import com.example.cetelem.repository.ClientRepository;

@Component
public class ClientServiceImpl implements ClientService {

	private ClientRepository clientRepository;

	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public List<SallesSell> getAllSallesSellByClient(Long clientID) {
		Optional<Client> clientResult = clientRepository.findById(clientID);
	    
		return new ArrayList<SallesSell>(clientResult.get().getSallesSell());
	}

}
