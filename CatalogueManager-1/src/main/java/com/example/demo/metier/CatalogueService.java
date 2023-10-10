package com.example.demo.metier;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Categorie;
import com.example.demo.entities.Produit;
import com.example.demo.repos.ICategorie;
import com.example.demo.repos.IProduit;
@Service
public class CatalogueService implements ICatalogue{

	@Autowired
	ICategorie crepo;
	@Autowired
	IProduit prepo;
	@Override
	public Categorie addcategorie(Categorie c) {
		// TODO Auto-generated method stub
		return crepo.save(c);
	}

	@Override
	public Produit addProduit(Produit p, long idc) {
		
		Categorie c = crepo.findById(idc)
				.orElseThrow(()-> new NoSuchElementException("no element"));
		p.setCategorie(c);
		return prepo.save(p);
		 
	}

	@Override
	public List<Categorie> findCategoriesByCondition(Predicate<Categorie> pred) {
		return crepo.findAll()
		.stream()
		.filter(pred)
		.collect(Collectors.toList());
		
	}

	@Override
	public List<Produit> findProduitsByCondition(Predicate<Produit> pred) {
		return prepo.findAll()
				.stream()
				.filter(pred)
				.collect(Collectors.toList());
				
	}

	@Override
	public Page<Categorie> allcats(Pageable p) {
		return crepo.findAll(p);
		
	}

	@Override
	public List<Categorie> allcategories() {
		// TODO Auto-generated method stub
		return crepo.findAll();
	}

}
