package com.example.cetelem.service;

import java.util.List;

import com.example.cetelem.model.SallesSell;

public interface ClientService {
	
	List<SallesSell> getAllSallesSellByClient(Long clientID);

}
