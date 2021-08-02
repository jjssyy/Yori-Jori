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
import com.web.curation.model.RecipeInfo;
import com.web.curation.model.RecipeSingleContent;
import com.web.curation.model.SaveComment;
import com.web.curation.model.service.FeedService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	// 글쓰기 기능 (제목, 아이디, 사진, 설명, 썸네일여부) - post
	// 임시 - 제목, 아이디, 설명
	@PostMapping("/write")
	public ResponseEntity<String> writeRecipe(SaveRecipeitem data) throws Exception {
		String id = data.getId();
		String title = data.getTitle();
		String nickname = data.getNickname();
		List<String> imgList = data.getImg();
		List<String> desList = data.getDes();
		List<String> thumbnailList = data.getThumbnail();

		if (id == null) {
			return new ResponseEntity<String>("Fail", HttpStatus.BAD_REQUEST);
		}
		if (title == null || imgList.isEmpty() || desList.isEmpty()) {
			return new ResponseEntity<String>("Fail", HttpStatus.NO_CONTENT);
		}

		RecipeInfo recipeInfo = new RecipeInfo();
		recipeInfo.setId(id);
		recipeInfo.setNickname(nickname);
		recipeInfo.setTitle(title);

		Map<String, String> map = new HashMap<>();
		map.put("title", title);
		map.put("id", id);

		// recipe_info 저장 (title, id, regdate)
		feedService.writeRecipeInfo(recipeInfo);

		// recipe_idx 가져오기
		int recipe_idx = feedService.getRecipe_idx(map);

		// recipe_content 저장
		for (int i = 0; i < imgList.size(); i++) {
			SaveRecipeContent content = new SaveRecipeContent();
			content.setImg(imgList.get(i));
			content.setDes(desList.get(i));
			content.setThumbnail(thumbnailList.get(i));
			content.setRecipe_idx(recipe_idx);
			content.setContent_order(i);

			feedService.writeRecipeContent(content);
		}

		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// 레시피 하나의 내용 보여주기
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

	// 최신피드 (메인)
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

	// 프로필 - 자기 레시피
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

	// 댓글 작성
	@PostMapping("/comment/write")
	public ResponseEntity<String> writeComment(@RequestBody SaveComment comment) {
		try {
			if (feedService.writeComment(comment) == 1) {
				return new ResponseEntity<String>("Success", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Fail", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//댓글 수정
//	@PutMapping("/comment/update")
//	public ResponseEntity<T>
	
	//댓글 삭제
	
	//댓글 좋아요 postmapping(/comment/like)
	
	//댓글 조회
}