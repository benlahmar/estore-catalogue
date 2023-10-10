package com.example.demo.metier;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProduitDto;
import com.example.demo.entities.Categorie;
import com.example.demo.entities.Produit;
import com.example.demo.repos.ICategorie;
import com.example.demo.repos.IProduit;
@Service
public class CatalogueService implements ICatalogue{

	/**
	 * repository de categorie
	 */
	@Autowired
	ICategorie crepo;
	@Autowired
	IProduit prepo;
	
	
	private static final Logger log = LoggerFactory.getLogger(CatalogueService.class);

	/**
	 * pour ajouter une categorie
	 */
	@Override
	public Categorie addcategorie(Categorie c) {
		
		return crepo.save(c);
	}

	@Override
	
	public Produit addProduit(Produit p, long idc) {
		
		Categorie c = crepo.findById(idc)
				.orElseThrow(()-> new NoSuchElementException("no element"));
		p.setCategorie(c);
		 p=prepo.save(p);
		
		return p;
		 
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

	@Override
	public Page<Categorie> allcat(Pageable p) {
		
		return crepo.findAll(p);
	}

	@Override
	public Optional<Produit> findProduitById(long id) {
		return  prepo.findById(id);
		
	}

	@Override
	public Optional<Categorie> findCategorieById(long id) {
		
		return crepo.findById(id);
	}

	@Override
	public Optional<Categorie> findbylib(String l) {
		// TODO Auto-generated method stub
		return crepo.findByLibelle(l);
	}

	@Override
	public ProduitDto getbylib(String l) {
		// TODO Auto-generated method stub
		return prepo.findByDesg(l);
	}

}
