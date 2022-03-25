package com.petstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.petstore.model.Pet;
import com.petstore.model.PetRepository;

@Service
public class PetService {

	@Autowired
	private PetRepository petRepo;

	public Pet save(Pet pet) {
		return petRepo.save(pet);
		
	}

	public Pet update(Pet pet) {
		return petRepo.save(pet);
	}

	public void deleteById(int petId) {
		petRepo.deleteById(petId);
		
	}
	
}
