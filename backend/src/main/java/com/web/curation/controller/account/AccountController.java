package com.web.curation.controller.account;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import com.web.curation.dao.user.UserDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.UserVO;
import com.web.curation.model.service.UserService;
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
    
    @Autowired
    UserService userservice;

    @ApiOperation(value = "로그인")
    @GetMapping("/login")
	public ResponseEntity<UserVO> login(@RequestParam Map map){
    	
    	System.out.println(map.get("email"));
    	System.out.println(map);
    	UserVO user = userservice.getuser(map);
    	
    	if(user != null) {
 
    		return new ResponseEntity<UserVO>(user,HttpStatus.OK); 
    	}
    	return new ResponseEntity<UserVO>(HttpStatus.NO_CONTENT); 
		

	}

    @PostMapping("/account/signup")
    @ApiOperation(value = "가입하기")

    public Object signup(@Valid @RequestBody SignupRequest request) {
        // 이메일, 닉네임 중복처리 필수
        // 회원가입단을 생성해 보세요.

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}