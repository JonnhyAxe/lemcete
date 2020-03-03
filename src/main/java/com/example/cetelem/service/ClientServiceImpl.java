package com.example.cetelem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.cetelem.model.Client;
import com.example.cetelem.model.SallesSell;
import com.example.cetelem.repository.ClientRepository;
import com.example.cetelem.web.exceptions.EntityNotFoundException;

@Component
public class ClientServiceImpl implements ClientService {

	private ClientRepository clientRepository;

	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public List<SallesSell> getAllSallesSellByClient(Long clientID) {
		 Client clientResult = clientRepository.findById(clientID)
				.orElseThrow(() -> new EntityNotFoundException("Client id not found :" + clientID));
	    
		return new ArrayList<SallesSell>(clientResult.getSallesSell());
	}

}
