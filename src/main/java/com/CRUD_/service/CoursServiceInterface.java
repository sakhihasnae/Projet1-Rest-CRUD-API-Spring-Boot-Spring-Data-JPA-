package com.CRUD_.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 
import com.CRUD_.model.Cours;

/********************************************************************************************************
/*****************************************   Interface Service   *****************************************
 * *******************************************************************************************************/


public interface CoursServiceInterface {
	
	//Post methods
	public ResponseEntity<String> saveData1 (Cours c);  
	public ResponseEntity<Cours> saveData2 (Cours c);
	public ResponseEntity<HttpStatus> saceData3 (Cours c ); 
	
	
	//Get methods
	public ResponseEntity<String> getById1(int id); 
	public ResponseEntity<Cours> getById2 (int id);   
	public ResponseEntity<List<Cours>> getAll (); 
	public ResponseEntity<List<Cours>> getByTitle (String title); 
	public ResponseEntity<List<Cours>> getByTitleContaining (String title); 
	public ResponseEntity<List<Cours>> getFinishedCours (); 
	public ResponseEntity<List<Cours>> getNotFinishedCours (); 
	 
	
	// put method
	public ResponseEntity<String> updateData(int id, Cours c); 
	
	//delete methods
	public ResponseEntity<String> deleteByIdS(int id); 
	public ResponseEntity<String> deleteDataByObject(Cours c); 
	public ResponseEntity<String> deleteDataByObject2 (Cours c) ; 
	public ResponseEntity<String> deleteAll(); 

}
