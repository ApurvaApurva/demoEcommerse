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

import com.example.genestore.model.Food;
import com.example.genestore.repository.FoodRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "food")
public class FoodController {

private byte[] bytes;
	
	@Autowired
	private FoodRepository FoodRepository;
	
	@GetMapping("/get")
	public List<Food> getFood() {
		return FoodRepository.findAll();
	}
	
	@PostMapping("/upload")
	public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		this.bytes = file.getBytes();
	}

	@PostMapping("/add")
	public void createFood(@RequestBody Food food) throws IOException {
		food.setPicByte(this.bytes);
		FoodRepository.save(food);
		this.bytes = null;
	}
	
	@PutMapping("/update")
	public void updateFood(@RequestBody Food food) {
		FoodRepository.save(food);
	}

	@DeleteMapping(path = { "/{id}" })
	public Food deleteFood(@PathVariable("id") long id) {
		Food food = FoodRepository.getOne(id);
		FoodRepository.deleteById(id);
		return food;
	}
}
