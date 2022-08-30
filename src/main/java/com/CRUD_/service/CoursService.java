package com.CRUD_.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.CRUD_.model.Cours;
import com.CRUD_.repository.CoursRepository;

/*****************************************************************************************************
******************************************************************************************************
**********************************************   Class Service   *************************************
******************************************************************************************************
*****************************************************************************************************/
@Service
public class CoursService implements CoursServiceInterface {

	// instance of Repository for Spring Data 
	@Autowired
	private CoursRepository coursRepository ; 
	
/******************************************************************************************************
**********************************************   POST Methods   **************************************** 
*******************************************************************************************************/

	                /********************Post returns a String message**********************/
	@Override
	public ResponseEntity<String> saveData1(Cours c) {
		String message=null;
		try   {
			
			if((c.getTitle()==null )  && (c.getDescription()==null ) && (c.isFinished()==false))
			{message = "the object send is empty"; 
			return new ResponseEntity<String>(message, HttpStatus.NO_CONTENT); }
			else
			{coursRepository.save(c);
			message="Object Created with Succes "; 
			return new ResponseEntity<String> (message,HttpStatus.CREATED )  ; }
			
		      }
		catch(Exception e )
		    {
			message=" Server Error Object not Created!!!!!!!"; 
			return new ResponseEntity<String>(message,HttpStatus.INTERNAL_SERVER_ERROR); 
		    }
		
	}
	              /********************Post returns the object Created **********************/

	@Override
	public ResponseEntity<Cours> saveData2(Cours c) {
		
		try   {
			       if((c.getTitle()==null )  && (c.getDescription()==null ) && (c.isFinished()==false))
			             {return new ResponseEntity<>(null,HttpStatus.NO_CONTENT); }
			       else
			             {coursRepository.save(c);
			             return new ResponseEntity<Cours> (c,HttpStatus.CREATED )  ; }
			
		      }
		catch(Exception e )
		{return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR); }
	}
	
	
	        /********************Post returns the Status **********************/
	
	   @Override
	   public ResponseEntity<HttpStatus> saceData3(Cours c) {
		   try   {
		       if((c.getTitle()==null )  && (c.getDescription()==null ) && (c.isFinished()==false))
		             {return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT); }
		       else
		             {coursRepository.save(c);
		             return new ResponseEntity<HttpStatus> (HttpStatus.CREATED )  ; }
		
	      }
	catch(Exception e )
	{return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR); }
}
	 

	              
/******************************************************************************************************
**********************************************   GET Methods   **************************************** 
*******************************************************************************************************/
	
	         /********************Get by Id : returns the object as a String **********************/
	

	@Override
	public ResponseEntity<String> getById1(int id) {
		if(true)
		{
      String message=null; 
		try {
			    
			     try {
			    	 Optional<Cours> c =  coursRepository.findById(id);
			     
	                 if ((c.get().getTitle()==null ) && (c.get().getDescription()==null) && ( c.get().isFinished()==false) )  
			        
			                  { message="the object select is empty" + c.toString(); 
			                    return new ResponseEntity<String>(message, HttpStatus.NO_CONTENT); }
			     
			         else if(c.isPresent())
			                  {message="The object existe \n "+ c.toString(); 
			    	            return new ResponseEntity<String> (message,HttpStatus.OK); }
			         }
			     
			     
			     catch(Exception e )
			     { message= "Object not Found , the  id given does not existe"; 
			      return new ResponseEntity<String>(message,HttpStatus.NOT_FOUND); }
			 }
		
		catch(Exception e )
		   { message="Server Error , Object could not be read at this moment "; 
		   return new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR); 
		   }}
		return null;
	}
		/********************Get by Id : returns the object in JSON  **********************/
	  

     @Override
     public ResponseEntity<Cours> getById2(int id) {
	
    	 try {
    		 
    		       try { Optional<Cours> c = coursRepository.findById(id);
    		 
    		               if((c.get().getTitle()==null ) && (c.get().getDescription()==null) && (c.get().isFinished()==false))
    		                   return new ResponseEntity<Cours>(c.get(),HttpStatus.NO_CONTENT); 
    		               else 
    		            	   return new ResponseEntity<Cours>(c.get(), HttpStatus.OK); 
    		      
    		        }catch(Exception e)
    		               {return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); }
    		
    	      }
    	 catch(Exception e)
    	 { return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);}
    	 
	
      }
			
                  /********************        Get All  **********************/            
	
	
	@Override
	public ResponseEntity<List<Cours>> getAll() {
		
		try {
			        List<Cours> lst = coursRepository.findAll();
			              if(lst.isEmpty())
			                       return new ResponseEntity<>(null, HttpStatus.NO_CONTENT); 
			               else 
				                   return new ResponseEntity<List<Cours>>(lst, HttpStatus.OK);
		   }  
		 catch (Exception e)
		       { return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); }
	 
	}
	
	    /********************        Get by a title : Looking for an exact title **********************/
	
	@Override
	public ResponseEntity<List<Cours>> getByTitle(String title) {
		
		if(title.isEmpty())
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		
		else  
		{
			try {
			
			List<Cours> lst = coursRepository.findByTitle(title); 
			if(lst.isEmpty())
			        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);  
			else 
				    return new ResponseEntity<List<Cours>>(lst, HttpStatus.OK); 
			
		    }
		catch(Exception e )
		        {   return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); }
	   }
	
	}
	
	
 /************* Get by a titleContaining: Looking for an exact title that containing a word given *****************/
	
	
	@Override
	public ResponseEntity<List<Cours>> getByTitleContaining(String title) {
		
		if(title.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); 
		else
		{
			try {
				List<Cours> lst = coursRepository.findByTitleContaining(title);
				if(lst.isEmpty())
			        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);  
			else 
				    return new ResponseEntity<List<Cours>>(lst, HttpStatus.OK); 
	     	    }
		catch(Exception e )
		{return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR); }
		}
		

	}
	
	
	   /********************        Get a list of finished Courses    **********************/
	@Override
	public ResponseEntity<List<Cours>> getFinishedCours() {

		try {
			    List<Cours> lst = coursRepository. findByFinished(true);
			    if(lst.isEmpty())
			    	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
			    else 
			    	return new ResponseEntity<List<Cours>>(lst, HttpStatus.OK); 
		    }
		catch(Exception e )
		{return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);}
	
	}
	
	 /********************        Get a list of Not finished Courses    **********************/
	
	@Override
	public ResponseEntity<List<Cours>> getNotFinishedCours() {
		try {
		    List<Cours> lst = coursRepository. findByFinished(false);
		    if(lst.isEmpty())
		    	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
		    else 
		    	return new ResponseEntity<List<Cours>>(lst, HttpStatus.OK); 
	    }
	catch(Exception e )
	           {return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);}
	}
	
	
	
/******************************************************************************************************
**********************************************   PUT Methods   **************************************** 
*******************************************************************************************************/	
	
	
	/******************** Put method : Update an object with it's id : return a message    ***********/
	
	@Override
	public ResponseEntity<String> updateData(int id, Cours c) {
		
   String message=null; 
 
	   try {
               if( ((c.getTitle()==null) && (c.getDescription()==null) && (c.isFinished()==false) ))
                { message=" the object sent is Empty  !!!!!"; 
	            return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST); }
               
               else 
               {
            	    Optional<Cours> c_ = coursRepository.findById(id);
                   if(c_.isEmpty() )
                   {  message="the id given does not existe you should add it First"; 
               
                   return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);}
                   
                   else {
                	   c_.get().setTitle(c.getTitle());
                	   c_.get().setDescription(c.getDescription());
                	   c_.get().setFinished(c.isFinished());
                	   coursRepository.save(c_.get());
                	   message ="Object updated \n" + c_.get().toString(); 
                        return new ResponseEntity<String>(message, HttpStatus.OK);}}
               
               
           }catch(Exception e )
             {message="Server Error , Could not Update Data !!!!!!!";
	          return new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR); }
 }
	
	
	
/******************************************************************************************************
**********************************************   DELETE Methods   **************************************** 
*******************************************************************************************************/
	
	/******************** Delete method : Delete an object with it's id : return a message    ***********/
	
	@Override
	public ResponseEntity<String> deleteByIdS(int id) {
		String message=null; 
		
		try {
		 Optional<Cours> c_ = coursRepository.findById(id);
         if(c_.isEmpty() )
         {  message="the id given does not existe you should add it First "; 
            return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);}
         else {
        	           coursRepository.deleteById(id); 
                       message="Object deleted with succes"; 
                       return new ResponseEntity<String>(message, HttpStatus.OK);
        	  }
         
         }catch(Exception e)
		{message="Error!!!!!!!!! \n Server Problem , Could not delete the object !!!!"; 
		return new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);}
		  }
	
	
	
	
/******************** Delete method : Delete an object with it's features : return a message    ***********/
	
	
		@Override
		public ResponseEntity<String> deleteDataByObject(Cours c ) {
	       String message=null; 
	     
	       if(true)
	    	   {Optional<Cours> c_ = coursRepository.findById(c.getId()); 
           try { 
				if( ((c.getTitle()==null) && (c.getDescription()==null) && (c.isFinished()==false) ))
					     {  message="The Object passed is empty!!!!!!!!! "; 
	                      return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);}
				else {
			    if (c_.isEmpty())
				{  message=" the object you want to delete does not existe "; 
	                return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);}
				
	             else if(c_.isPresent()) 
	             {coursRepository.delete(c_.get()); 
	               message="Object deleted with succes"; 
	               return new ResponseEntity<String>(message, HttpStatus.OK); }
				}
	         }catch(Exception e)
			{message="Error!!!!!!!!! \n Server Problem , Could not delete the object !!!!"; 
			return new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);}}
	       return null; 
		                                                            }
		
/******************** Delete method : Delete an object with it's features : return a message    ***********
 * ******************  Verifying All attributs before deleting not just the ID ****************************/ 
		@Override
		public ResponseEntity<String> deleteDataByObject2(Cours c) {
			
			if(true)
			{String message = null ; 
			try {
				Optional<Cours> temp = coursRepository.findById(c.getId());
				if(( ((c.getTitle()==null) && (c.getDescription()==null) && (c.isFinished()==false) )))
				 {message="The object passed is EMPTY !!!!!!\n you should give values "; 
		          return new ResponseEntity<String>(message,HttpStatus.BAD_REQUEST); }
				
				else if(temp.isEmpty())
				   {message="The object  given with this id "+c.getId()+" does not existe verify again "; 
		             return new ResponseEntity<String>(message,HttpStatus.NOT_FOUND); }
				
				else if(temp.isPresent()){
					String titleC=c.getTitle() ; 
				    String	descpriptionC=c.getDescription(); 
					String titleTemp= temp.get().getTitle(); 
					String descriptionTmp= temp.get().getDescription(); 
					if(   (titleC.compareTo(titleTemp) != 0) || (descpriptionC.compareTo(descriptionTmp) != 0) )
					{ message= " the title and description given with this id " +c.getId()+ "  does not match the values in the database \n Please Chek again "; 
					   return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND); }
					
					else 
					{   coursRepository.delete(c);
						message="The object given was delete with succes"; 
					   return new ResponseEntity<String>(message, HttpStatus.OK);  }
					                    }
			   }catch (Exception e)
			           {message= "Error Server !!!!!!! The object could not be deleted"; 
				        return new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);}}
			return null;
	
		}
		
		
	   /********************        Delete method : Delete All return a message   **********************/		
		
		@Override
		public ResponseEntity<String> deleteAll() {
			String message=null; 
			try {
				List<Cours> lst= new ArrayList<Cours>();
				lst= coursRepository.findAll();
				     if(lst.isEmpty())
				            {message="The database is Empty Nothing to Delete !!!!!";
					          return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);}
				     else { coursRepository.deleteAll();
				    	     message="The Database is deleted with succes";
				    	     return new ResponseEntity<String>(message, HttpStatus.OK); }
				
			     }catch(Exception e )
		          	{message="Server Error !!!!!, nothing is deleted"; 
				    return new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);}	
		}
		
	
	}

/*****************************************************************************************************	
******************************************************************************************************
**********************************************  The END  ********************************************** 
*************************************  Project made By Hasnae SAKHI   ********************************* 
*******************************************************************************************************
*******************************************************************************************************/
	

	
	
	

	
	