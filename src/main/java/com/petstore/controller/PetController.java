package com.petstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petstore.model.ApiResponse;
import com.petstore.model.Pet;
import com.petstore.model.PetRepository;
import com.petstore.service.PetService;


@RestController
@RequestMapping("/pet")
public class PetController {

	@Autowired
	private PetRepository petRepo;
	@Autowired
	private PetService petService;
	@GetMapping("/findByStatus")
	public ResponseEntity<ApiResponse> findByStatus(@RequestParam String[] status) {
		System.out.println("--findByStatus");
		List<Pet> petList = petRepo.findByStatus(status.toString());
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage(petList.toString());
		ResponseEntity<ApiResponse> respEnt = new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.FOUND);
	    return respEnt;
	}
	
	@GetMapping("/{petId}")
	public ResponseEntity<ApiResponse> findById(@PathVariable int petId) {
		System.out.println("--findById");
		Optional<Pet> petList = petRepo.findById(petId);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage(petList.get().toString());
		ResponseEntity<ApiResponse> respEnt = new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.FOUND);
	    return respEnt;
	}
	@DeleteMapping("/{petId}")
	public  ResponseEntity<ApiResponse> deleteById(@PathVariable int petId) {
		System.out.println("--deleteById");
		petService.deleteById(petId);
		ApiResponse apiResponse = new ApiResponse();
		ResponseEntity<ApiResponse> respEnt = new ResponseEntity(apiResponse,HttpStatus.OK);
	    return respEnt;
	}
	@RequestMapping(value ="/addNewPet", method=RequestMethod.POST)
	public ResponseEntity<Pet> addNewPet(@RequestBody Pet pet) {
		System.out.println("--addNewPet");
		ResponseEntity<Pet> respEnt = new ResponseEntity(petService.save(pet),HttpStatus.OK);
	    return respEnt;
	}
	
	@RequestMapping(value ="/updatePet", method=RequestMethod.PUT)
	public ResponseEntity<Pet> updatePet(@RequestBody Pet pet) {
		System.out.println("--updatePet");
		ResponseEntity<Pet> respEnt = new ResponseEntity(petService.update(pet),HttpStatus.OK);
	    return respEnt;
	}
}
