
<template>
  <div class="user" id="login">
    <div class="wrapC">
      <h1>
        로그인을 하고 나면
        <br />좋은 일만 있을 거예요.
      </h1>

      <div class="input-with-label">
        <input
          v-model="id"
          @keyup.enter="onLogin"
          id="id"
          placeholder="아이디를 입력하세요."
          type="text"
        />
        <label for="id">아이디</label>
      </div>
      <div class="input-with-label">
        <input
          v-model="password"
          type="password"
          v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}"
          id="password"
          @keyup.enter="onLogin"
          placeholder="비밀번호를 입력하세요."
        />
        <label for="password">비밀번호</label>
        <div class="error-text" v-if="error.password">{{error.password}}</div>
      </div>
      <button
        class="btn btn--back btn--login"
        @click="onLogin"
        :disabled="!isSubmit"
        :class="{disabled : !isSubmit}"
      >로그인</button>

      <div class="sns-login">
        <div class="text">
          <p>SNS 간편 로그인</p>
          <div class="bar"></div>
        </div>

        <kakaoLogin :component="component" />
        <GoogleLogin :component="component" />
      </div>
      <div class="add-option">
        <div class="text">
          <p>혹시</p>
          <div class="bar"></div>
        </div>
        <div class="wrap">
          <p>아직 회원이 아니신가요?</p>
          <router-link to="/user/join" class="btn--text">가입하기</router-link>
        </div>
        <div class="wrap">
          <p>비밀번호 찾기</p>
          <router-link to="/user/findpw" class="btn--text">찾아보기</router-link>
        </div>
        <div class="wrap">
          <p>글쓰기</p>
          <router-link to="/recipe/write" class="btn--text">쓰기</router-link>
        </div>
      </div>
    </div>
  </div>
</template>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.js"></script>
<script>
import "../../components/css/user.scss";
import PV from "password-validator";
import KakaoLogin from "../../components/user/snsLogin/Kakao.vue";
import GoogleLogin from "../../components/user/snsLogin/Google.vue";
import UserApi from "../../api/UserApi";
import { mapState } from 'vuex'
import { mapActions } from 'vuex'


export default {
  components: {
    KakaoLogin, 
    GoogleLogin
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

    // 토큰이 있는 경우(=로그인 된 사용자) 메인페이지로 redirect
    if (this.$store.state.token){
      this.$router.push({name:'FeedMain'})
    }
  },
  watch: {
    password: function(v) {
      this.checkForm();
    },
  },
  methods: {
    checkForm() {

      if (
        this.password.length >= 0 &&
        !this.passwordSchema.validate(this.password)
      )
      this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.password = false;

      let isSubmit = true;
      Object.values(this.error).map(v => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    onLogin() {
      if (this.isSubmit) {
      
        let data = {
          id : this.id,
          pw : this.password
        };

       UserApi.requestLogin(
          data,
          res => {

           
             if(res.data.result == "success"){
            alert("로그인 되었습니다.");
           this.$store.dispatch("login",res);

              this.$store.state.userId = data.id
              console.log(this.$store.state.userId)
              this.$router.push({name:'FeedMain'});
            }else if(res.data.result == "fail"){
              alert("로그인 실패.");
          
            }else{
              alert("아이디가 존재하지 않거나 비밀번호가 틀렸습니다.");
              this.$router.push({name:'ErrorPage'});

            }
          },
          error => {
            //요청이 끝나면 버튼 활성화
            alert("아이디가 존재하지 않거나 비밀번호가 틀렸습니다.");

            this.$router.push({name:'ErrorPage'});
          }
        );
      }
    }
  },
   computed: {
    ...mapState([
      'userId',
    ]),
  },
  data: () => {
    return {
      id: "",
      password: "",
      passwordSchema: new PV(),
      error: {
        passowrd: false
      },
      isSubmit: false,
      component: this
    };
  }
};
</script>


