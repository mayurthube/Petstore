package com.petstore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/pet")
public class PetController {

	@GetMapping("/findByStatus")
	public String findByStatus() {
		System.out.println("--findByStatus");
	    return "findByStatus";
	}
	@RequestMapping(value ="/addNewPet", method=RequestMethod.POST)
	public String addNewPet() {
		System.out.println("--addNewPet");
	    return "addNewPet";
	}
	
	@RequestMapping(value ="/updatePet", method=RequestMethod.PUT)
	public String updatePet() {
		System.out.println("--updatePet");
	    return "updatePet";
	}
}
