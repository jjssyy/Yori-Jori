package com.web.curation.controller.account;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.UserVO;
import com.web.curation.model.service.JwtService;
import com.web.curation.model.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    	System.out.println(map.get("nickname"));
    	try {
    		
    		String findnickname = userservice.checknickname(map);
    		System.out.println(findnickname);
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
	 

   

}