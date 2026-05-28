package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
