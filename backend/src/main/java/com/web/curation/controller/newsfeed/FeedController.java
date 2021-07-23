package com.web.curation.controller.newsfeed;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.model.Recipeitem;

@CrossOrigin("*")
@RequestMapping("/feed")
@RestController
public class FeedController {
   //글쓰기 기능 (제목, 아이디, 사진, 설명, 썸네일여부) - post
   //임시 - 제목, 아이디, 설명
   @PostMapping("/write")
   public ResponseEntity<String> writeRecipe(Recipeitem data){
      String id = data.getId();
      String title = data.getTitle();
      
      List<MultipartFile> imgList = data.getImg();
      List<String> desList = data.getDes();
      List<String> thumbnailList = data.getThumbnail();
      
      System.out.println("--글작성--");
      System.out.println(id);
      System.out.println(title);
      for(int i=0; i<desList.size(); i++) {
         System.out.println(imgList.get(i).getOriginalFilename());
         System.out.println(desList.get(i));
         System.out.println(thumbnailList.get(i));
      }
      System.out.println("--------");

      return new ResponseEntity<String>("Success", HttpStatus.OK);
   }
}