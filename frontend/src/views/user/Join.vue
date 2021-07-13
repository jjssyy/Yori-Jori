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
        <input
          v-model="nickName"
          id="nickname"
          placeholder="닉네임을 입력하세요."
          v-bind:class="{error : error.nickName, complete:!error.nickName&&nickName.length!==0}"
          type="text"
        />
        <label for="nickname">닉네임</label>
        <div class="error-text" v-if="error.nickName">{{error.nickName}}</div>
      </div>

      <div class="input-with-label">
        <input
          v-model="email"
          id="email"
          placeholder="이메일을 입력하세요."
          v-bind:class="{error : error.email, complete:!error.email&&email.length!==0}"
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
          placeholder="비밀번호를 입력하세요."
        />
        <label for="password">비밀번호</label>
        <div class="error-text" v-if="error.password">{{error.password}}</div>
      </div>

      <div class="input-with-label">
        <input
          v-model="passwordConfirm"
          :type="passwordConfirmType"
          v-bind:class="{error : error.passwordConfirm, complete:!error.passwordConfirm&&passwordConfirm.length!==0}"
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
    </label>

    <span @click="termPopup = true">약관보기</span>

    <button class="btn-bottom"

    @click="join">가입하기</button>
  </div>
</template>

<script>
import * as EmailValidator from "email-validator";
import PV from "password-validator";

export default {
  data: () => {
    return {
      email: "",
      password: "",
      passwordConfirm: "",
      passwordSchema: new PV(),
      nickName: "",
      isTerm: false,
      isLoading: false,
      error: {
        email: false,
        password: false,
        nickName: false,
        passwordConfirm: false,
        term: false,
      },
      isSubmit: false,
      passwordType: "password",
      passwordConfirmType: "password",
      termPopup: false,
    };
  },
  created() {
    this.component = this;

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
  watch: {
     nickName: function(v) {
      this.check();
    },
    password: function(v) {
      this.check();
    },
    passwordConfirm: function(v) {
      this.check();
    },
    email: function(v) {
      this.check();
    }
  },
  methods: {
    check(){
      
      if ((this.nickName.length <= 0 || this.nickName.length < 2))
        this.error.nickName = "닉네임은 두자리 이상이어야 합니다.";
      else this.error.nickName = false;
  
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else this.error.email = false;

      if(this.email.charCodeAt(0) === this.email.toUpperCase().charCodeAt(0)){
        this.error.email = "첫글자가 대문자입니다.";
      }
      
       if (
        this.password.length >= 0 &&
        !this.passwordSchema.validate(this.password)
      )
        this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.password = false;

      if(this.passwordConfirm.length > 0 && this.password != this.passwordConfirm){
        this.error.passwordConfirm = "비밀번호가 서로 다릅니다.";
      }
      else this.error.passwordConfirm = false;

      

      let isSubmit = true;
      Object.values(this.error).map(v => {
        if (v) isSubmit = false;
        
      });
      this.isSubmit = isSubmit;

    },
    join(){
      

      if(this.isTerm == false){
        alert("약관에 동의해주세요");
      }else if(this.isSubmit){
        alert("회원가입 완료!");
        this.$router.push("/");
      }else{
        alert("회원가입 양식을 지켜주세요");
      }
    }
  },
};
</script>
