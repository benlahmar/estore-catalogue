package com.example.demo.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Categorie;

public interface ICategorie extends JpaRepository<Categorie, Long>{

	public Optional<Categorie> findByLibelle(String l);
}
