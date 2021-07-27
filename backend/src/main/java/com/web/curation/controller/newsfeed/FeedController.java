package com.web.curation.controller.newsfeed;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.RecipeContent;
import com.web.curation.model.RecipeSingleContent;
import com.web.curation.model.service.FeedService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.model.SaveRecipeContent;
import com.web.curation.model.SaveRecipeitem;

import ch.qos.logback.core.net.SyslogOutputStream;
import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

@CrossOrigin("*")
@RequestMapping("/feed")
@RestController
public class FeedController {

	@Autowired
	FeedService feedService;

	@Autowired
	ResourceLoader rsLoader;

	// 글쓰기 기능 (제목, 아이디, 사진, 설명, 썸네일여부) - post
	// 임시 - 제목, 아이디, 설명
	@PostMapping("/write")
	public ResponseEntity<String> writeRecipe(SaveRecipeitem data) throws Exception {
		String id = data.getId();
		String title = data.getTitle();
		List<MultipartFile> imgList = data.getImg();
		List<String> desList = data.getDes();
		List<String> thumbnailList = data.getThumbnail();

		Map<String, String> map = new HashMap<>();
		map.put("title", title);
		map.put("id", id);

		// recipe_info 저장 (title, id, regdate)
		feedService.writeRecipeInfo(map);

		// recipe_idx 가져오기
		int recipe_idx = feedService.getRecipe_idx(map);

		// recipe_content 저장 (img, des, thumbnail, recipe_idx)
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String current_date = simpleDateFormat.format(new Date());

		String absolutePath = new File("").getAbsolutePath() + "\\";

		String path = "images/" + current_date;
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}

		for (int i = 0; i < imgList.size(); i++) {
			MultipartFile img = imgList.get(i);
			String contentType = img.getContentType();
			String originalFileExtension;
			if (contentType.contains("image/jpeg")) {
				originalFileExtension = ".jpg";
			} else if (contentType.contains("image/png")) {
				originalFileExtension = ".png";
			} else if (contentType.contains("image/gif")) {
				originalFileExtension = ".gif";
			}
			else {
				break;
			}
			
			String new_file_name = Long.toString(System.nanoTime()) + img.getOriginalFilename()+originalFileExtension;
			String lastpath= absolutePath + path + "/" + new_file_name;
			file = new File(lastpath);
			img.transferTo(file);

			SaveRecipeContent content = new SaveRecipeContent();
			content.setImg(lastpath);
			content.setDes(desList.get(i));
			content.setThumbnail(thumbnailList.get(i));
			content.setRecipe_idx(recipe_idx);

			feedService.writeRecipeContent(content);
		}

		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	
	@GetMapping("/content")
	public ResponseEntity<Map<String, Object>> feedList(@RequestParam String recipe_idx) {
		String result = "SUCCESS";
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		try {
			List<RecipeSingleContent> recipeContents = feedService.getRecipeContents(recipe_idx);

			resultMap.put("recipeContent", recipeContents);

			if (recipeContents == null) {
				result = "FAIL";
			} else {
				result = "SUCCESS";
			}

			resultMap.put("message", result);
			status = HttpStatus.ACCEPTED;

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@GetMapping("/latestfeed")
	public ResponseEntity<Map<String, Object>> latestFeed(@RequestParam String id) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String result = "SUCCESS";
		try {
			List<RecipeContent> recipe = feedService.getLatestFeed(id);

			resultMap.put("latestFeed", recipe);

			if (recipe == null) {
				result = "FAIL";
			} else {
				result = "SUCCESS";
			}

			resultMap.put("message", result);
			status = HttpStatus.ACCEPTED;

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@GetMapping("/allrecipes")
	public ResponseEntity<Map<String, Object>> getAllRecipes(@RequestParam String id) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String result = "SUCCESS";
		try {
			List<RecipeContent> recipe = feedService.getAllRecipes(id);

			resultMap.put("latestFeed", recipe);

			if (recipe == null) {
				result = "FAIL";
			} else {
				result = "SUCCESS";
			}

			resultMap.put("message", result);
			status = HttpStatus.ACCEPTED;

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
}
