package com.example.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Produit;

public interface IProduit extends JpaRepository<Produit,Long>{

	List<Produit> findByQteLessThan(int qte);
	List<Produit> findByPrixBetween(double p1, double p2);
}
