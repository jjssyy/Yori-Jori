<template>
  <div class="wrapC">
    <div class="box">
    <div class="row" id="join_box0">
      <img class="food_line" src="@/assets/images/food_line.png" />
      <img :src="require(`@/assets/images/roundlogo.png`)" />
      <img class="food_line" src="@/assets/images/food_line2.png" />
    </div>
    <div class="d-flex justify-content-around" id="register">
      <div class=" row card mb-4 border-0 shadow" id="normal">
        <div class="col-xs-3">
          <label>일반 가입</label>
          <div style="margin-top:10px;">
          <p>아이디, 비밀번호 등을 활용한 <br>회원가입</p>
          <router-link to="/user/join" class="btn--text">
          <img :src="require(`@/assets/images/user.png`)" />
        </router-link>
        </div>
        </div>
      </div>

        <div class="row card mb-4 border-0 shadow" id="kakao">
          <div class="col-xs-3">
            <label>카카오 가입</label>
            <div style="margin-top:10px;">
            <p>카카오 API를 이용한 회원가입</p>
              <kakaoregister></kakaoregister>
          </div>
          </div>

      </div>
      </div>
    </div>
    <hr>
    </div>


</template>

<script>
import UserApi from '../../api/UserApi';
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import Kakaoregister from "../../components/user/snsLogin/KakaoRegister.vue";

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
*{
   font-family: 'BBTreeGB';
}
@font-face {
    font-family: 'BBTreeGB';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_nine_@1.1/BBTreeGB.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
.wrapC{
  float: none;
  margin: 0 auto ;
}

#normal, #kakao{
  text-align: center;
  margin:0 3% 0 3%;
  
}

#normal label, #kakao label{
  padding-top: 10px;
  font-size: 30px;
  margin: 0 2% 0 2%;
}

#normal img{
  width:120px;
  height:120px;
  margin: 5px 2% 50px 2%;
}

#kakao img{
  width:110px;
  margin: 35px 2% 40px 2%;
}

#register p{
  color: #838383;
}

#join_box0 img{
  width:180px;
  float: none;
  margin:-80px auto 60px;
}
.card{
  max-width: 300px;
  width: 300px;
  margin: 0 3%;
  margin-bottom: 3%;
  
}
.food_line{
  position:relative;
  height: 20px;
}

</style>