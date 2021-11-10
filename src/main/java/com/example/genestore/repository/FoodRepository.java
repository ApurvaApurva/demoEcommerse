package com.example.genestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.genestore.model.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {

}
