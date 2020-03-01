package com.example.cetelem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.cetelem.model.GeographicalArea;
import com.example.cetelem.model.SallesSell;
import com.example.cetelem.model.SallesSellRiskProfile;

@RepositoryRestResource(collectionResourceRel = "sallesSell", path = "sallesSell")
public interface SallesSellRepository  extends CrudRepository<SallesSell, Long> {

	List<SallesSell> findByRisKProfileAndGeographicalAreaAndComercialSellAgeRanges(SallesSellRiskProfile risKProfile, GeographicalArea geographicalArea, String comercialSellAgeRanges);

	
}
