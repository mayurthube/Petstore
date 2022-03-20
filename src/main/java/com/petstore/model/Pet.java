package com.petstore.model;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Pet {
	@Id
	private  int id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", nullable = false)
	private  Category category;
	private  String name;
	private  Map<String ,Boolean>photoUrls;
	@OneToMany(targetEntity=Tag.class) 
	private  List<Tag> tags;
	@Enumerated(EnumType.ORDINAL)
	private  Status  status;
	
}
