package com.example.genestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.genestore.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}