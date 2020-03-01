package com.example.cetelem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.cetelem.model.SallesSell;

@RepositoryRestResource(collectionResourceRel = "sallesSell", path = "sallesSell")
public interface SallesSellRepository  extends CrudRepository<SallesSell, Long> {

}
