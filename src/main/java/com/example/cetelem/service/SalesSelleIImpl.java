package com.example.cetelem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.cetelem.model.Client;
import com.example.cetelem.model.SallesSell;
import com.example.cetelem.repository.SallesSellRepository;

public class SalesSelleIImpl implements SallesSellService {

	private SallesSellRepository sallesSellRepository;
	
	public SalesSelleIImpl(SallesSellRepository sallesSellRepository) {
		this.sallesSellRepository = sallesSellRepository;
	}


	@Override
	public List<Client> getAllClientBySallesSell(SallesSell sallesSell) {
		List<SallesSell> sallesSells = sallesSellRepository.findByRisKProfileAndGeographicalAreaAndComercialSellAgeRanges(
				sallesSell.getRisKProfile(), 
				sallesSell.getGeographicalArea(), 
				sallesSell.getComercialSellAgeRanges());
		
		Set<Client> sallesSellStreamClientDistinct = sallesSells.stream()
				.map((sallesSellStream) -> sallesSellStream.getClients())
				.flatMap(x -> x.stream())
				.collect(Collectors.toSet()); 
		
		return new ArrayList<>(sallesSellStreamClientDistinct);		
	}

}
