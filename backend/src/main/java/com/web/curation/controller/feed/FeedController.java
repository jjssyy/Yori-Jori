package com.web.curation.controller.feed;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.RecipeSingleContent;
import com.web.curation.model.service.FeedService;
import com.web.curation.model.service.JwtService;
import com.web.curation.model.service.UserService;

@CrossOrigin("*")
@RequestMapping("/feed")
@RestController
public class FeedController {
	@Autowired
	UserService userservice;

	@Autowired
	FeedService feedService;
	
	@Autowired
	JwtService jwtservice;
	
	@Autowired
	PasswordEncoder passwordencoder;
	
	@GetMapping("/content")
	public ResponseEntity<Map<String, Object>> feedList(@RequestParam String recipe_idx) {
		String result = "SUCCESS";
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		try {
			List<RecipeSingleContent> recipeContents = feedService.getRecipeContents(recipe_idx);
			
			resultMap.put("recipeContent", recipeContents);
			
			if(recipeContents == null) {
				result = "FAIL";
			} else {
				result = "SUCCESS";
			}
			
			resultMap.put("message", result);
			status = HttpStatus.ACCEPTED;
			
		} catch(Exception e) {
			e.printStackTrace();
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
}
