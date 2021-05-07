package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.User;
import com.app.repository.UserData;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired(required=true)
	private UserData dataDao;	
	
	///http://localhost:8090/api/get/{id}
	 @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	 public User readUserData(@PathVariable int id) {
		 System.out.println("UserController.readUserData()");
		 System.out.println("The JPA implemnetation class generated is "
		 		+ ""+dataDao.getClass().getName());
		 Optional<User> userWrapper =  dataDao.findById(id);
		 User obj = userWrapper.get();
		 if(obj==null)
		 System.out.println("The User record could not be found!! ");
		 else
			 System.out.println("The User record  found for "+obj.getId() +" and "+obj.getName());
		 return obj;
	 }
	 
	// http://localhost:8090/api/list
	 @RequestMapping(value = "list", method = RequestMethod.GET)
	 public List<User> readAllUserData() {
		 System.out.println("UserController.readAllUserData()");
		List<User> userList = dataDao.findAll();
		System.out.println("The number of User records found are "+userList.size());
		  
		 return userList;
	 }
	 //http://localhost:8090/api/test
	 @RequestMapping(value = "test", method = RequestMethod.GET)
	 public User testUserData() {
		 System.out.println("UserController.testUserData()");  
		 
		 User obj = new User();
		 obj.setEmployed(true);
		 obj.setId(45);
		 obj.setName("Baba Jadhav");
		 obj.setIncome(10000);		 
		 return obj;
	 }
	 
	 //http://localhost:8090/api/add
	 @RequestMapping(value = "add", method = RequestMethod.POST)
	 public User addUserData(@RequestBody User newUser) {
		 System.out.println("UserController.addUserData() name :"+newUser.getName());
		 System.out.println("UserController.addUserData() income :"+newUser.getIncome());
		 System.out.println("UserController.addUserData() employed is :"+ newUser.isEmployed());
			 
		 User obj = new User();
		 obj.setEmployed(newUser.isEmployed());
		// obj.setId(id);
		 obj.setName(newUser.getName());
		 obj.setIncome(newUser.getIncome());
		 dataDao.save(obj);
		 return obj;
	 }
	 //http://localhost:8090/api/update/{id}
	 @RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	 public ResponseEntity<User> updateUserData(@PathVariable int id,@RequestBody User currentUser) {
		 System.out.println("UserController.updateUserData() name :"+currentUser.getName());
		 System.out.println("UserController.updateUserData() income :"+currentUser.getIncome());
		 System.out.println("UserController.updateUserData() employed is :"+ currentUser.isEmployed());
		 Optional<User> current = dataDao.findById(id);
		 if(current==null)
		 {
			 System.out.println("User object with id="+id+" not found");
			 return new ResponseEntity(null, HttpStatus.NOT_FOUND);

		 }  
			 
		 User result = dataDao.save(currentUser);
		  //Who updates the existingUser in database
		 return new ResponseEntity<User>(result, HttpStatus.ACCEPTED);
	 }
	 
	//http://localhost:8090/api/remove/{id}
	 @RequestMapping(value = "remove/{id}", method = RequestMethod.DELETE)
	 public boolean removeUserData(@PathVariable int id) {
		 System.out.println("UserController.removeUserData() :"+id);
		 Optional<User> current = dataDao.findById(id);
		 if(current==null)
		 {
			 System.out.println("User object with id="+id+" not found to delete");
			 return false;
		 }
		 
		 User existingUser = current.get();
		 dataDao.delete(existingUser);
		 return true;
	 }

}
