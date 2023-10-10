package com.example.demo.entities;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Categorie {

	public Categorie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Categorie(String libelle) {
		super();
		this.libelle = libelle;
	}

	public Categorie(long id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}

	@Id
	@GeneratedValue
	long id;
	String libelle;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "categorie", fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	List<Produit> produits;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, libelle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categorie other = (Categorie) obj;
		return id == other.id && Objects.equals(libelle, other.libelle);
	}
	
	
	
}
