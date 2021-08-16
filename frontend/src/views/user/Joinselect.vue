<template>
  <div class="wrapC">
    <div class="row" id="register">

      <div class="col-md-4" id="normal">
        <div class="row">
           <label>일반 가입</label>
        </div>
        <div class="row" style="margin-top:30px;">
          <p>아이디, 비밀번호 등을 활용한 회원가입</p>
        </div>
       <div class="row">
        <router-link to="/user/join" class="btn--text">
          <img :src="require(`@/assets/images/user.png`)" />
        </router-link>
       </div>
    
      </div>
      <div class="col-md-4"></div>
        <div class="col-md-4" id="kakao">
          <div class="row">
            <label>카카오가입</label>
          </div>
          <div class="row" style="margin-top:30px;">
            <p>카카오 API를 이용한 회원가입</p>
          </div>
          <div class="row">
            <kakaoregister></kakaoregister>
          </div>
      </div>
      </div>
    
    </div>


</template>

<script>
import UserApi from '../../api/UserApi';
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import Kakaoregister from "../../components/user/snsLogin/KakaoRegister.vue";
import swal from 'sweetalert';

export default {
   components: {
    Kakaoregister, 
  },
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
            swal("사용가능합니다.",{icon:'success'});
            this.error.id = false;
          }else if(res.data == "fail"){
            swal("중복된 아이디입니다.",{icon:'warning'});
            this.error.id = true;
          }else{
            swal("에러가 발생하였습니다.",{icon:'error'});
            this.error.id = true;
          }
        },
        error=>{
          swal("에러가 발생하였습니다.",{icon:'error'});
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
            swal("사용가능합니다.",{icon:'success'});
            this.error.nickname = false;
          }else if(res.data == "fail"){
            swal("중복된 닉네임입니다.",{icon:'warning'});
            this.error.nickname = true;
          }else{
            swal("에러가 발생하였습니다.",{icon:'error'});
            this.error.nickname = true;
          }
        },
        error=>{
          swal("에러가 발생하였습니다.",{icon:'error'});
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
            this.$router.push({name:'Login'});
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

<style>

#register{
  margin-top: 200px;
}

#normal, #kakao{
  text-align: center;
  
}

#normal label, #kakao label{
  font-size: 40px;
}

#normal img{
  width:350px;
  height:250px;
  margin: 80px 0px 50px -100px;
}

#kakao img{
  width:200px;
  margin: 120px 0px 50px -20px;
}

#register p{
  color: #ffbe76;
}

</style>