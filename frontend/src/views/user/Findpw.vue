<!--
    가입하기는 기본적인 폼만 제공됩니다
    기능명세에 따라 개발을 진행하세요.
    Sub PJT I에서는 UX, 디자인 등을 포함하여 백엔드를 제외하여 개발합니다.
 -->
<template>
  <div class="user join wrapC">
    <h1>비밀번호 변경</h1>
    <div class="form-wrap">
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

    <button class="btn-bottom"

    @click="join">저장</button>
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
  
    password: function(v) {
      this.check();
    },
    passwordConfirm: function(v) {
      this.check();
    },

  },
  methods: {
    check(){
     
     
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
      
      if(this.isSubmit){
        alert("비밀번호 변경 완료!");
        this.$router.push("/"); 
      }else{
        alert("양식을 지켜주세요");
      }
    }
  },
};
</script>
