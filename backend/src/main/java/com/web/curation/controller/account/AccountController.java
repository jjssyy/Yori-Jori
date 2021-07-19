package com.web.curation.controller.account;

import java.util.Optional;

import javax.validation.Valid;


import com.web.curation.dao.user.UserDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.service.JwtService;
import com.web.curation.model.user.ChangePassword;
import com.web.curation.model.user.SignupRequest;
import com.web.curation.model.user.User;

import org.springframework.web.bind.annotation.RestController;
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

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin("*")
@RestController
public class AccountController {

    @Autowired
    UserDao userDao;
    JwtService jwtService;
   
	 

    @GetMapping("/account/login")
    @ApiOperation(value = "로그인")
    public Object login(@RequestParam(required = true) final String email,
            @RequestParam(required = true) final String password) {
        Optional<User> userOpt = userDao.findUserByEmailAndPassword(email, password);

        ResponseEntity response = null;

        if (userOpt.isPresent()) {
            final BasicResponse result = new BasicResponse();
            result.status = true;
            result.data = "success";
            result.object = userOpt.get().getEmail();
       
            response = new ResponseEntity<>(result, HttpStatus.OK);
           
        } else {
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            System.out.println("로그인 실패");
        }
        return response;
    }

    @PostMapping("/account/signup")
    @ApiOperation(value = "가입하기")
    public Object signup(@Valid @RequestBody SignupRequest request) {
    	
        // 이메일, 닉네임 중복처리 필수
        // 회원가입단을 생성해 보세요.
       
       final BasicResponse result = new BasicResponse();
       ResponseEntity response = null;
       User user = userDao.getUserByEmail(request.getEmail());
  
       if(user==null) {
    	
          user = new User();
          
          user.setUid(request.getNickname());
          user.setEmail(request.getEmail());
          user.setPassword(request.getPassword());
          userDao.save(user);
          
          result.status = true;
          result.data = "success";
          response = new ResponseEntity<>(result, HttpStatus.OK);
//          System.out.println(result.data);
          
       }else {
   
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }
    
    @PutMapping("/account/changepassword")
    @ApiOperation(value = "비밀번호 변경하기")
    public Object changePassword(@Valid @RequestBody ChangePassword request) {
    	
       final BasicResponse result = new BasicResponse();
       ResponseEntity response = null;
       User user = userDao.getUserByEmail(request.getEmail());
       if(user==null) {
          response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
       }else {
          user.setPassword(request.getNewPassword());
          userDao.save(user);
          
          result.status = true;
          result.data = "success";
          response = new ResponseEntity<>(result, HttpStatus.OK);
//          System.out.println(result.data);
        }
        return response;
    }

}