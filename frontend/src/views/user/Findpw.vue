
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
          @keyup.enter="findpw"
          id="id"
          placeholder="아이디를 입력하세요."
          type="text"
        />
        <label for="id">아이디</label>
      </div> 

       <div class="input-with-label">
        <input
          v-model="email"
          @keyup.enter="findpw"
          id="email"
          placeholder="이메일을 입력하세요."
          type="text"
        />
        <label for="id">이메일</label>
      </div> 

      <button
        class="btn btn--back btn--login"
        @click="findpw"
        :disabled="isSubmit"
        :class="{disabled : !isSubmit}"
      >비밀번호 찾기</button>

    </div>
  </div>
</template>

<script>
import "../../components/css/user.scss";
import UserApi from "../../api/UserApi";
import { mapState } from 'vuex'
import { mapActions } from 'vuex'


export default {
  components: {
   
  },
  created() {
    this.component = this;

    
  },
  watch: {
    
  },
  methods: {
    
    findpw() {
      //alert(this.isSubmit);
      //if (this.isSubmit) {
      
        let data = {
          id : this.id,
          email : this.email
        };

       UserApi.emailfindpw(
          data,
          res => {
            console.log(res);
             if(res.data == "success"){
            alert("이메일이 발송되었습니다.");
           this.$router.push("/");
           console.log(res.data.resultmap);
         


          }else if(res.data == "fail"){
            alert("아이디 혹은 이메일이 틀렸습니다.");
          
          }else{

            alert("오류발생.");

            this.$router.push("/error");
          
          }
          },
          error => {
            //요청이 끝나면 버튼 활성화
            alert("에러발생");

            this.$router.push("/error");
          }
        );
      //}
    }
  },
  data: () => {
    return {

      id: "",
      email: "",
      isSubmit: false,
    };
  }
};
</script>


