package com.example.demo.metier;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entities.Categorie;
import com.example.demo.entities.Produit;

public interface ICatalogue {

	public Categorie addcategorie(Categorie c);
	public Produit addProduit(Produit p, long idc);
	
	Page<Categorie> allcats(Pageable p);
	List<Categorie> allcategories();
	List<Categorie> findCategoriesByCondition(Predicate<Categorie> pred);
	List<Produit> findProduitsByCondition(Predicate<Produit> pred);
}
