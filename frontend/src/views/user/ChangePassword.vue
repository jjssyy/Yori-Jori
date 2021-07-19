
<template>
  <div class="user changePassword wrapC">
    <h1>비밀번호 찾기</h1>
    <div class="form-wrap">

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
        placeholder="새로운 비밀번호를 입력하세요." />
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

    <button class="btn-bottom"
    @click="onJoin"
    :disabled="!isSubmit"
    :class="{disabled : !isSubmit}"
    >변경하기</button>
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
        email:this.email,
        password:this.password,
        passwordConfirm:this.passwordConfirm,
        nickname:this.nickName
      };
      this.isSubmit=false;

      UserApi.requestChangePassword(
        data,
        res=>{
          console.log(res);
          if(res.data.data=="success")alert("비밀번호 변경 완료!");
          
          this.isSubmit=true;
          this.$router.push("/");
        },
        error=>{
          alert("비밀번호 변경에 실패하였습니다.");
          this.isSubmit = true;
          this.$router.push("/error");
        }
      );
      }
      
     
    }

  }
};
</script>