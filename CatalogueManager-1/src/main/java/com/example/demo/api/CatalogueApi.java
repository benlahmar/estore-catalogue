package com.example.demo.api;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Categorie;
import com.example.demo.entities.Produit;
import com.example.demo.metier.ICatalogue;

@RestController
public class CatalogueApi {

	@Autowired
	ICatalogue service;
	
	@PostMapping("/categories")
	public Categorie addcat(@RequestBody Categorie c)
	{
		return service.addcategorie(c);
	}
	
	@GetMapping("/categories")
	public List<Categorie> categories()
	{
		return service.allcategories();
	}
	
	@PostMapping("/categories/{id}/produits")
	public ResponseEntity<Produit> addprdroduit(@RequestBody Produit p,@PathVariable(name="id") long idcat)
	{
		try {
			p= service.addProduit(p, idcat);
			return new ResponseEntity<Produit>(p,HttpStatus.CREATED);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<Produit>(HttpStatus.NO_CONTENT);
		}
		
	}
}
