package com.example.demo.api;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProduitDto;
import com.example.demo.entities.Categorie;
import com.example.demo.entities.Produit;
import com.example.demo.exceptions.AlreadyExitlement;
import com.example.demo.exceptions.ElementNotExist;
import com.example.demo.metier.ICatalogue;

@RestController
//@RequestMapping("api/catalogue")
public class CatalogueApi {

	@Autowired
	ICatalogue service;
	
	@PostMapping("/categories")
	public Categorie addcat(@RequestBody Categorie c)
	{
		 Optional<Categorie> cc = service.findbylib(c.getLibelle());
		 if(cc.isEmpty())
			 return service.addcategorie(c);
		 else
			 throw new AlreadyExitlement("deja exist");
		
	}
	
	@GetMapping("/categories")
	public List<Categorie> categories()
	{
		return service.allcategories();
	}
	
	@GetMapping("/categories/page")
	public Page<Categorie> categoriesbypage(@RequestParam int page,@RequestParam int size)
	{
		Pageable p= PageRequest.of(page, size);
		return service.allcat(p);
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
	@GetMapping("/produits/seuil")
	public List<Produit> getProduitQuantityLessThan(@RequestParam int qte)
	{
		return service.findProduitsByCondition(x->x.getQte()<qte);
		
	}
	@GetMapping("/produits/price")
	public List<Produit> getProduitPrixBetween(@RequestParam double p1,@RequestParam double p2)
	{
		return service.findProduitsByCondition(x->x.getPrix()>p1 && x.getPrix()>p2);
	}
	
	@GetMapping("/categories/{id}")
	public ResponseEntity<Categorie> findcatbyid(@PathVariable long id)
	{
		Optional<Categorie> co = service.findCategorieById(id);
		if(co.isPresent())
		{
			return new ResponseEntity<Categorie>(co.get(),HttpStatus.FOUND);
		}
		else
			return new ResponseEntity<Categorie>(HttpStatus.NO_CONTENT);
	}
	@GetMapping("/categories2/{id}")
	public Categorie findcatbyid2(@PathVariable long id)
	{
		return service.findCategorieById(id).orElseThrow(()->new ElementNotExist("pas d element"));
	}
	
	
	@GetMapping("/produits/{id}")
	public Produit getprd(@PathVariable long id)
	{
		return service.findProduitById(id).orElseThrow(()->new ElementNotExist("pas de produit"));
	}
	@GetMapping("/produits/search/{l}")
	public ProduitDto getbydesg(@PathVariable String l)
	{
		return service.getbylib(l);
	}
}
