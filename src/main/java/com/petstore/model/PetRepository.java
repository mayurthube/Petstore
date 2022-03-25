package com.petstore.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {

	List<Pet> findByStatus(String name);

}
