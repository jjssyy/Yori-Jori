
<!--
    가입하기는 기본적인 폼만 제공됩니다
    기능명세에 따라 개발을 진행하세요.
    Sub PJT I에서는 UX, 디자인 등을 포함하여 백엔드를 제외하여 개발합니다.
 -->
<template>
  <div class="user join wrapC">
    <h1>가입하기</h1>
    <div class="form-wrap">
      <div class="input-with-label">
        <input v-model="id" id="id" placeholder="닉네임을 입력하세요." type="text" />
        <label for="id">아이디</label>
        <button @click="checkid">중복체크</button>
        <div class="error-text" v-if="error.id"></div>
      </div>
      <div class="input-with-label">
        <input v-model="nickname" id="nickname" placeholder="닉네임을 입력하세요." type="text" />
        <label for="nickname">닉네임</label>
        <button @click="checknickname">중복체크</button>
        <div class="error-text" v-if="error.nickname"></div>
      </div>

      <div class="input-with-label">
        <input v-model="email" 
        v-bind:class="{error : error.email, complete:!error.email&&email.length!==0}"
        id="email" 
        placeholder="이메일을 입력하세요." 
        type="text" 
        />
        <label for="email">이메일</label>
        <div class="error-text" v-if="error.email">{{error.email}}</div>
      </div>

      <div class="input-with-label">
        <input 
        v-model="password" 
        id="password" 
        :type="passwordType" 
        v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}"
        placeholder="비밀번호를 입력하세요." />
        <label for="password">비밀번호</label>
        <div class="error-text" v-if="error.password">{{error.password}}</div>
      </div>

      <div class="input-with-label">
        <input
          v-model="passwordConfirm"
          :type="passwordConfirmType"
          v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}"
          id="password-confirm"
          placeholder="비밀번호를 다시한번 입력하세요."
        />
        <label for="password-confirm">비밀번호 확인</label>
        <div class="error-text" v-if="error.passwordConfirm">{{error.passwordConfirm}}</div>
      </div>
    </div>

    <label>
      <input v-model="isTerm" type="checkbox" id="term" />
      <span>약관을 동의합니다.</span>
      <div class="error-text" v-if="error.rule"></div>
    </label>

    <span @click="termPopup=true">약관보기</span>

    <button class="btn-bottom"
    @click="onJoin"
    :disabled="!isSubmit"
    :class="{disabled : !isSubmit}"
    >가입하기</button>
  </div>
</template>

<script>
import UserApi from '../../api/UserApi';
import PV from "password-validator";
import * as EmailValidator from "email-validator";
export default {
  created(){
    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();    
  },
  data: () => {
    return {
      id:"",
      email: "",
      password: "",
      passwordConfirm: "",
      passwordSchema: new PV(),
      nickname: "",
      isTerm: false,
      isLoading: false,
      error: {
        email: false,
        password: false,
        nickname: false,
        passwordConfirm: false,
        term: false
      },
      isSubmit: false,
      passwordType: "password",
      passwordConfirmType: "password",
      termPopup: false
    };
  },
  watch:{ 

    password: function(v) {
      this.checkForm();
    },
    email: function(v) {
      this.checkForm();
      this.lowercase();
    },
    passwordConfirm:function(v) {
      this.checkForm();
    }
  },
  methods:{
    lowercase(){
      this.email= this.email.toLowerCase();
    },

    checkid(){

         let data = {
        id:this.id,
     
      };
         
        UserApi.checkid(
        data,
        res=>{
          console.log(res);
          if(res.data == "success"){
            alert("사용가능합니다.");
            this.error.id = false;
          }else if(res.data == "fail"){
            alert("중복된 아이디입니다.");
            this.error.id = true;
          }else{
            alert("에러가 발생하였습니다.");
            this.error.id = true;
          }
        },
        error=>{
          alert("사용불가");
          this.error.id = true;
          
        }
      );
    },

    checknickname(){

         let data = {
        nickname:this.nickname,
     
      };
         
        UserApi.checknickname(
        data,
        res=>{
          console.log(res);
          if(res.data == "success"){
            alert("사용가능합니다.");
            this.error.nickname = false;
          }else if(res.data == "fail"){
            alert("중복된 닉네임입니다.");
            this.error.nickname = true;
          }else{
            alert("에러가 발생하였습니다.");
            this.error.nickname = true;
          }
        },
        error=>{
          alert("사용불가");
          this.error.nickname = true;
          
        }
      );
    },

    checkForm() {
      
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else this.error.email = false;

      if (
        this.password.length >= 0 &&
        !this.passwordSchema.validate(this.password)
      )
        this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.password = false;

      if(
        this.password!=this.passwordConfirm
      )this.error.passwordConfirm="두 비밀번호가 일치하지 않습니다.";
      else this.error.passwordConfirm=false;

      let isSubmit = true;
      Object.values(this.error).map(v => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    onJoin(){
      if(this.isSubmit){
        let data = {
        id:this.id,
        nickname: this.nickname,
        email:this.email,
        pw:this.password,
        passwordConfirm:this.passwordConfirm,
      };
      this.isSubmit=false;

      UserApi.requestJoin(
        data,
        res=>{
          console.log(res);
          if(res.data =="success"){
            alert("회원가입에 성공하였습니다!");
            this.$router.push("/user/join/complete");
          }else if(res.data =="fail"){
            alert("회원가입에 실패하셨습니다.!");
          }else{
            alert("에러발생");
         
          this.$router.push("/error");
          }

          
        },
        error=>{
          alert("에러발생");
          this.isSubmit = true;
          this.$router.push("/error");
        }
      );
      }
      
     
    }

  }
};
</script>