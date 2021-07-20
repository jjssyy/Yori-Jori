package com.web.curation.controller.account;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.web.bind.annotation.RestController;


import com.web.curation.model.Changepw;
import com.web.curation.model.UserVO;
import com.web.curation.model.service.JwtService;
import com.web.curation.model.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@CrossOrigin("*")
@RequestMapping("/user")
@RestController
public class UserController {
	
	@Autowired
	private UserService userservice;

	
	@Autowired
	JwtService jwtservice;

	
	@GetMapping("/login")
	public ResponseEntity<?> login(@RequestParam Map map){
		
		String result = "";

		Map resultmap = new HashMap<>();
		 
		System.out.println("!!!");
		
		try { 
			
			UserVO user = userservice.login(map);
	
			if(user.getId() != null && user.getPw() != null) {
				String token = jwtservice.create("user_id", user.getId(), "access-token");
				resultmap.put("access-token", token);
				
				result = "success";
				resultmap.put("result", result);

				
			}else {
				result = "fail";
				resultmap.put("result", result);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
			resultmap.put("result", result);
		}
		
		
		return new ResponseEntity<Map>(resultmap,HttpStatus.OK);
	}

	
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserVO user){
    	
    	String result = "";
    	
    	try {
    		
    		boolean join = userservice.join(user);
    		if(join == true) {
    			result = "success";
    			
    		}else {
    			result = "fail";
    		}
    		
			
		} catch (Exception e) {
			result = "error";
			e.printStackTrace();
		} 
    	
		return new ResponseEntity<String>(result,HttpStatus.OK);
    }
    
    @GetMapping("/checkid")
    public ResponseEntity<String> checkid(@RequestParam Map map){
    	
    	String result = "success";
    	
    	try {
    		
    		String findid = userservice.checkid(map);
    		
    		if(findid == null) {
    			
    		}else {
    			
    			result = "fail";
    			
    			
    		}
    		
    		
		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
			
		}
    	
    	return new ResponseEntity<String>(result,HttpStatus.OK);
    }
    
    @GetMapping("/checknickname")
    public ResponseEntity<String> checknickname(@RequestParam Map map){
    	
    	String result = "success";
    	//System.out.println(map.get("nickname"));
    	try {
    		
    		String findnickname = userservice.checknickname(map);
   
    		if(findnickname == null) {
    			
    			result = "success";
    			
    		}else {
    			
    			result = "fail";
    			
    			
    		}
    		
    		
		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
			
		}
    	
    	return new ResponseEntity<String>(result,HttpStatus.OK);
    }
	 

   @PutMapping("/updateuser")
   public ResponseEntity<String> updatename(@RequestBody UserVO user){
	   
	   String result = "";
	   
	try {
		
		if(userservice.updateuser(user) == true) {
			result = "success";
		}else {
			result = "fail";
		}
		
	} catch (Exception e) {
		e.printStackTrace();
		result = "error";
	}
	   
	   return new ResponseEntity<String>(HttpStatus.OK);
   }
   

   @PutMapping("/updatepw")
   public ResponseEntity<String> updatepw(@RequestParam Map map){
	   
	   String result = "";
	   
	try {
		
		String old = userservice.oldpw(map);
		boolean newpw = false;
		
		if(old == map.get("oldpw")) {
			newpw = userservice.updatepw(map);
			result = "success";
		}else {
			result = "fail";
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	

	   return new ResponseEntity<String>(HttpStatus.OK);
	}
   @PostMapping("/updatepw")
   public ResponseEntity<String> updatepw(@RequestBody Changepw changepw){
	   
	   String result = "";
	  
	try {
		
		String old = userservice.oldpw(changepw);
		
		if(old.equals(changepw.getOldpw())) {
			
			if(userservice.updatepw(changepw) == true) {
				result = "success";
				System.out.println("??");
			}else {
				result = "fail";
			}
			
		}else {
			result = "fail";
			

		}
		
	} catch (Exception e) {
		e.printStackTrace();
		result = "error";
	}
	   


	   return new ResponseEntity<String>(result,HttpStatus.OK);
   }


}