
<!--
    가입하기는 기본적인 폼만 제공됩니다
    기능명세에 따라 개발을 진행하세요.
    Sub PJT I에서는 UX, 디자인 등을 포함하여 백엔드를 제외하여 개발합니다.
 -->
<template>
  <div class="user join wrapC">
    <h1>비밀번호 변경</h1>
   
      <div class="input-with-label">
        <input v-model="oldpw" 
        v-bind:class="{error : error.oldpw, complete:!error.oldpw&&oldpw.length!==0}"
        id="oldpw" 
        placeholder="현재 비밀번호를 입력하세요." 
        :type="passwordType"  
        />
        <label for="oldpw">현재 비밀번호</label>
        <div class="error-text" v-if="error.oldpw">{{error.oldpw}}</div>
      </div>

      <div class="input-with-label">
        <input 
        v-model="newpw" 
        id="newpw" 
        :type="passwordType" 
        v-bind:class="{error : error.newpw, complete:!error.newpw&&newpw.length!==0}"
        placeholder="비밀번호를 입력하세요." />
        <label for="newpw">비밀번호</label>
        <div class="error-text" v-if="error.newpw">{{error.newpw}}</div>
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
    


    <button class="btn-bottom"
    @click="changepw"
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
      id:"",
      oldpw:"",
      newpw: "",
      passwordConfirm: "",
      passwordSchema: new PV(),
      nickname: "",
      isTerm: false,
      isLoading: false,
      error: {
        newpw: false,
        oldpw: false,
        passwordConfirm: false,
      
      },
      isSubmit: false,
      passwordType: "password",
      passwordConfirmType: "password",
      termPopup: false
    };
  },
  watch:{ 

    oldpw: function(v) {
      this.checkForm();
    },

    newpw: function(v) {
      this.checkForm();
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

   

    checkForm() {
      
      if (
        this.oldpw.length >= 0 &&
        !this.passwordSchema.validate(this.oldpw)
      )
        this.error.oldpw = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.oldpw = false;
     
      if (
        this.newpw.length >= 0 &&
        !this.passwordSchema.validate(this.newpw)
      )
        this.error.newpw = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.newpw = false;

      if(
        this.newpw!=this.passwordConfirm
      )this.error.passwordConfirm="두 비밀번호가 일치하지 않습니다.";
      else this.error.passwordConfirm=false;

      let isSubmit = true;
      Object.values(this.error).map(v => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    changepw(){
      if(this.isSubmit){
        let data = {
        id:"1",
        oldpw:this.oldpw,
        newpw:this.newpw,
        passwordConfirm:this.passwordConfirm,
      };
      this.isSubmit=false;
  
      UserApi.changepw(
        data,
        res=>{
          
          if(res.data =="success"){
            alert("비밀번호 변경에 성공하였습니다!");
            this.$router.push("/");
          }else if(res.data =="fail2"){
            alert("비밀번호 변경에 실패하셨습니다!");
          }
          else if(res.data =="fail1"){
            alert("현재 비밀번호가 아닙니다.");
          }
          else{
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