package com.example.genestore.repository.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.genestore.model.Cloths;
import com.example.genestore.repository.ClothsRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "cloths")
public class ClothsController {

private byte[] bytes;
	
	@Autowired
	private ClothsRepository clothsRepository;
	
	@GetMapping("/get")
	public List<Cloths> getCloths() {
		return clothsRepository.findAll();
	}
	
	@PostMapping("/upload")
	public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		this.bytes = file.getBytes();
	}

	@PostMapping("/add")
	public void createCloths(@RequestBody Cloths cloths) throws IOException {
		cloths.setPicByte(this.bytes);
		clothsRepository.save(cloths);
		this.bytes = null;
	}
	
	@PutMapping("/update")
	public void updateCloths(@RequestBody Cloths cloths) {
		clothsRepository.save(cloths);
	}

	@DeleteMapping(path = { "/{id}" })
	public Cloths deleteCloths(@PathVariable("id") long id) {
		Cloths book = clothsRepository.getOne(id);
		clothsRepository.deleteById(id);
		return book;
	}
	
}
