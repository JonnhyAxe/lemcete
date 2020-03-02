package com.example.cetelem.service;

import java.util.Set;

import com.example.cetelem.model.Client;
import com.example.cetelem.model.SallesSell;

public interface SallesSellService {
	
	Set<Client> getAllClientBySallesSell(SallesSell sallesSell);
}
