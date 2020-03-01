package com.example.cetelem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.cetelem.model.Client;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface ClientRepository extends CrudRepository<Client, Long> {

}
