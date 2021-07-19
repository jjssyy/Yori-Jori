package com.web.curation.controller.account;

import java.util.HashMap;
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
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	final String SUCCESS = "success";
	final String FAIL = "fail";
	
    @PostMapping("/join")
    public ResponseEntity join(@RequestParam Map map){
    	
    	
    	
		return new ResponseEntity(HttpStatus.OK);
    }
	 
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map map) throws Exception {
    	Map<String, Object> resultMap = new HashMap<>();
    	
    	UserVO user = userService.login(map);
    	HttpStatus status = null;
    	
    	if(user.getId() != null && user.getPw() != null) {
    		String token = jwtService.create("user_id", user.getId(), "access-token");
    		resultMap.put("access-token", token);
    		resultMap.put("message", SUCCESS);
    		status = HttpStatus.ACCEPTED;
    	} else {
    		resultMap.put("message", FAIL);
    		status = HttpStatus.NOT_FOUND;
    	}
    	return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
   

}