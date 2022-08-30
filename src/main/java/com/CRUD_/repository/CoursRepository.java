package com.CRUD_.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CRUD_.model.Cours;

public interface CoursRepository extends JpaRepository<Cours, Integer> {

	public List<Cours> findByTitle (String title); 
	public List<Cours> findByTitleContaining (String title); 
	public List<Cours> findByFinished (boolean choix); 
	
}  
