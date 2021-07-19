package com.web.curation.controller.account;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.service.JwtService;

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

    @PostMapping("/join")
    public ResponseEntity join(@RequestParam Map map){
    	
    	
    	
		return new ResponseEntity(HttpStatus.OK);
    }
	 

   

}