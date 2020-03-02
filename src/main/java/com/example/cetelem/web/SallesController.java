package com.example.cetelem.web;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cetelem.model.Client;
import com.example.cetelem.model.GeographicalArea;
import com.example.cetelem.model.SallesSell;
import com.example.cetelem.model.SallesSellRiskProfile;
import com.example.cetelem.service.SallesSellService;

@RestController
@RequestMapping("/salles")
public class SallesController {

	private SallesSellService sallesSellService;

	public SallesController(SallesSellService sallesSellService) {
		this.sallesSellService = sallesSellService;
	}

	@GetMapping(path = "/clients/criteria") // risKProfile=LOW&geographicalArea=NORTH&comercialSellAgeRanges=18,45
	public Set<Client> getClientBySallesCriteria(@RequestParam SallesSellRiskProfile risKProfile, 
			@RequestParam GeographicalArea geographicalArea, @RequestParam String comercialSellAgeRanges) {
		SallesSell sallesSell = SallesSell.builder()
				.comercialSellAgeRanges(comercialSellAgeRanges)
				.geographicalArea(geographicalArea)
				.risKProfile(risKProfile)
				.build();
		
		return Optional.ofNullable(sallesSellService.getAllClientBySallesSell(sallesSell))
				.orElseThrow(() -> new RuntimeException("Criteria not found by RiskProfile : " + risKProfile));
	}
}
