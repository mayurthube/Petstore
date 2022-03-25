package com.petstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.petstore.model.User;
import com.petstore.service.StoreUserDetailsService;
import com.petstore.service.UserService;
import com.petstore.model.ApiResponse;
import com.petstore.model.AuthenticationRequest;
import com.petstore.model.AuthenticationResponse;
import com.petstore.model.Pet;
import com.petstore.util.JWTUtil;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	StoreUserDetailsService userDetailsService;
	@Autowired
	JWTUtil jwtUtil;
	@Autowired
	UserService userService;
	
	@PostMapping("/createUser")
	public ResponseEntity<User> createUser(@RequestBody User user){
		ResponseEntity<User> respEnt = new ResponseEntity<User>(userService.save(user),HttpStatus.OK);
		return respEnt;
		
	}
	@PostMapping("/createWithArray")
	public ResponseEntity<String> createUserWithArray(@RequestBody User [] users){
		userService.saveAll(users);
		ResponseEntity<String> respEnt = new ResponseEntity<String>("Succesfull",HttpStatus.OK);
		return respEnt;
		
	}
	@PostMapping("/createWithList")
	public ResponseEntity<String> createWithList(@RequestBody List <User> users){
		userService.saveAll(users);
		ResponseEntity<String> respEnt = new ResponseEntity<String>("Succesfull",HttpStatus.OK);
		return respEnt;
		
	}
	@RequestMapping(value ="/login", method=RequestMethod.POST)
	public  ResponseEntity<?> createAuthToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (Exception e) {
			throw new Exception("Incorrect username and password");
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@RequestMapping(value ="/logout", method=RequestMethod.POST)
	public  ResponseEntity<?> logOut(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		final UserDetails userDetails = null;
		final String jwt = "";
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<User> findByUserName(@PathVariable String username) {
		User user = userService.finByUsername(username);
		return ResponseEntity.ok(user);
	}
	@DeleteMapping("/{username}")
	public  ResponseEntity<ApiResponse> deleteById(@PathVariable String username) {
		System.out.println("--deleteById");
		userService.deleteByName(username);
		ApiResponse apiResponse = new ApiResponse();
		ResponseEntity<ApiResponse> respEnt = new ResponseEntity(apiResponse,HttpStatus.OK);
	    return respEnt;
	}
	
	@RequestMapping(value ="/updateUser", method=RequestMethod.PUT)
	public ResponseEntity<User> updatePet(@RequestBody User user) {
		System.out.println("--updateUser");
		ResponseEntity<User> respEnt = new ResponseEntity(userService.update(user),HttpStatus.OK);
	    return respEnt;
	}
}
