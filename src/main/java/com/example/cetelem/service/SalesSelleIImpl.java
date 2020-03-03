package com.example.cetelem.service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.cetelem.model.Client;
import com.example.cetelem.model.SallesSell;
import com.example.cetelem.repository.SallesSellRepository;
import com.example.cetelem.web.exceptions.EntityNotFoundException;

@Component
public class SalesSelleIImpl implements SallesSellService {

	private SallesSellRepository sallesSellRepository;

	public SalesSelleIImpl(SallesSellRepository sallesSellRepository) {
		this.sallesSellRepository = sallesSellRepository;
	}

	@Override
	public Set<Client> getAllClientBySallesSell(SallesSell sallesSell) {
		List<SallesSell> sallesSells = sallesSellRepository
				.findByRisKProfileAndGeographicalAreaAndComercialSellAgeRanges(sallesSell.getRisKProfile(),
						sallesSell.getGeographicalArea(), sallesSell.getComercialSellAgeRanges());

		Set<Client> sallesSellStreamClientDistinct = sallesSells.stream()
				.map((sallesSellStream) -> sallesSellStream.getClients()).flatMap(x -> x.stream())
				.collect(Collectors.toSet());

		return sallesSellStreamClientDistinct;
	}

	@Override
	public SallesSell createSallesSell(SallesSell sallesSell) {
		SallesSell sallesSellSearchresult = sallesSellRepository.findByNameAndRisKProfileAndGeographicalAreaAndComercialSellAgeRanges(
				sallesSell.getName(), sallesSell.getRisKProfile(), sallesSell.getGeographicalArea(),
				sallesSell.getComercialSellAgeRanges());
		
		if (Objects.isNull(sallesSellSearchresult)) {
			return sallesSellRepository.save(sallesSell);
		}
		return sallesSellSearchresult;
	}

	@Override
	public void upateSallesSell(SallesSell sallesSell) {
		SallesSell sales = sallesSellRepository.findById(sallesSell.getId())
				.orElseThrow(() -> new EntityNotFoundException("No Sells Sell found with id: " + sallesSell.getId()));

		if (Objects.nonNull(sales)){
			sallesSellRepository.save(sallesSell);		
		}
	}

	@Override
	public void deleteSallesSell(Long sallesSellID) {
		//Limitation with H2 FK cascading with REMOVE
		SallesSell sales =  sallesSellRepository.findById(sallesSellID)
				.orElseThrow(() -> new EntityNotFoundException("No Sells Sell found with id: " + sallesSellID));
		if (Objects.nonNull(sales)){
			
			sales.getClients().stream().forEach((client) -> client.getSallesSell().remove(sales));
			sallesSellRepository.deleteById(sallesSellID);
		}
	}
}
