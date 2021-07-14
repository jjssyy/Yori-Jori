

<template>
  <div class="wrap">
    <div class="user" id="head">
      회원가입 인증 메일을 발송되었습니다. 이메일을 확인해 주세요
    </div>
    <div id="buttons">
      <span>
        <button @click="mailresend">메일 재발송</button>
      </span>
      <span>
        <button @click="gotomailbox">메일함 이동</button>
      </span>
    </div>
  </div>
  
</template>

<script>
import "../../components/css/user.scss";

export default {
  methods: {
    mailresend(){
      alert("메일을 재발송 하였습니다.");
    },
    gotomailbox(){
      alert("메일 보관함으로 이동합니다.")
    }
  },
  

};
</script>

<style>
#head{
  font-size: 40px;
  text-align: center;
  margin-top: 200px;
}
#buttons{
  text-align: center;
  font-size: 30px;
  margin-left: -50px;
}

#buttons button{
  margin-top: 100px;
  margin-left: 50px;
}
</style>




