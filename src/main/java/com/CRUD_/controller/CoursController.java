package com.CRUD_.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CRUD_.model.Cours;
import com.CRUD_.repository.CoursRepository;
import com.CRUD_.service.CoursService;

/*****************************************************************************************************
******************************************************************************************************
**********************************************   Class Service   *************************************
******************************************************************************************************
*****************************************************************************************************/

@RestController
@RequestMapping("/Controller")
public class CoursController {

	// instance of ClassService 
		@Autowired
		private CoursService coursService ; 
		
		
		
		             /********************Post methods**********************/
		@PostMapping("/post1")// returns a String message
		public ResponseEntity<String> saveDataC1 (@RequestBody  Cours c )
		{return coursService.saveData1(c); }
		

		@PostMapping("/post2")// returns the object send in the body
		public ResponseEntity<Cours> saveDataC2 (@RequestBody  Cours c )
		{return coursService.saveData2(c); }
		
		

                     /********************Get methods**********************/
		@GetMapping("/getByid1/{id}")// returns the object as a String
		public ResponseEntity<String> getByid1 (@PathVariable("id") int  id )
		{return coursService.getById1(id); }
		         
		@GetMapping("/getByid2/{id}")// returns the object in Json 
		public ResponseEntity<Cours> getByid2 (@PathVariable("id") int  id )
		{return coursService.getById2(id); }
		
		@GetMapping("/getAll")// returns All 
		public ResponseEntity<List<Cours>> getAllC (  )
		{return coursService.getAll(); }
		
		@GetMapping("/getByTitle1")// returns All with a specific title 
		public ResponseEntity<List<Cours>> getByTitle ( @RequestParam(required = false)  String title )
		{return coursService.getByTitle(title);  }
		
		@GetMapping("/getByTitle2")// returns All witch  contain a specific title 
		public ResponseEntity<List<Cours>> getByTitleContaingC( @RequestParam(required = false)  String title )
		{return coursService.getByTitleContaining(title);  }
		
		@GetMapping("/getFinished") // returns finished Courses
		public ResponseEntity<List<Cours>> getFinishedC ()
		{return coursService.getFinishedCours(); }
		
		@GetMapping("/getNotFinished") // returns  Not finished Courses
		public ResponseEntity<List<Cours>> getNotFinishedC ()
		{return coursService.getNotFinishedCours(); }
		
		
		
		        /********************     Update method   **********************/
		@PutMapping("UpdateData1/{id}") // update an object in the database , by it's ID 
		public ResponseEntity<String> updateData1C ( @PathVariable("id")  int id,@RequestBody  Cours c )
		
		{return coursService.updateData(id, c); }
		
		
		
	       	/********************     Delete methods   **********************/
		@DeleteMapping("/deleteById/{id}")  // delete by Id 
		public ResponseEntity<String> deleteDataById (@PathVariable("id") int id)
		{return coursService.deleteByIdS(id); }
		
		@DeleteMapping("/deleteByObject")  // delete by Object  
		public ResponseEntity<String> deleteByData (@RequestBody Cours c )
		{return coursService.deleteDataByObject(c); }
		
		@DeleteMapping("/deleteByObject2")  // delete by Object verifing all attributs 
		public ResponseEntity<String> deleteByData2 (@RequestBody Cours c )
		{   return coursService.deleteDataByObject2(c); }
		
		@DeleteMapping("/deleteAll")  // delete all objects in the database
		public ResponseEntity<String> deletAllC ( )
		{  return coursService.deleteAll();  }
}
