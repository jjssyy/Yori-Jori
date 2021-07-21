
<template>
  <div class="user wrapC">
    <h1>회원 수정</h1>
    <div class="form-wrap">

      <div class="input-with-label">
        <input v-model="nickName"
        id="nickName" 
        placeholder="닉네임을 입력하세요." 
        type="text" 
        />
        <label for="nickName">닉네임</label>
      </div>
      <div class="input-with-label">
        <input v-model="des"
        id="des" 
        placeholder="한줄소개를 입력하세요." 
        type="text" 
        />
        <label for="des">한줄소개</label>
      </div>
      <div class="input-with-label">
        <input v-model="cellphone"
        id="cellphone" 
        placeholder="전화번호를 입력하세요." 
        type="text"
        />
        <label for="cellphone">전화번호</label>
      </div>
      <div class="input-with-label">
        <input v-model="birthday"
        id="birthday" 
        placeholder="생일을 입력하세요." 
        type="text" 
        />
        <label for="birthday">생일</label>
      </div>
      <div class="input-with-label">
        <input v-model="address.address1"
        id="address.address1" 
        placeholder="주소를 입력하세요." 
        type="text" 
        />
        <label for="address.address1">주소</label>
      </div>
      <div class="input-with-label">
        <input v-model="email" 
        id="email" 
        type="text" 
        />
        <label for="email">이메일</label>
      </div>
      <div class="input-with-label">
        <input v-model="userId" 
        id="userId" 
        type="text" 
        />
        <label for="userId">아이디</label>
      </div>

    </div>

    <button class="btn-bottom"
    @click="update"
    >변경하기</button>
  </div>
</template>

<script>
import UserApi from '../../api/UserApi';

export default {
  data: () => {
    return {
      email: "",
      nickName: "",
      userId:"",
      des:"",
      birthday:"",
      cellphone:"",
      address:{
        address1:"",
        address2:"",
        address3:"",
      },
      isLoading: false,
      error: {
        email: false,
        nickName: false,
      },
      isSubmit: false,
    };
  },
  created() {
    if (this.$store.state.token === ''){
      this.$router.push({name:'Login'})
    }
    this.getUser()
  },
  watch:{
  },
  methods:{
    getUser(){
      const config = {
        headers:{
          'access-token': this.$store.state.token,
          'id':this.$store.state.userId
        }
      }
      UserApi.requestUser(
        config,
        res=>{
          console.log(res.data.userInfo);
          this.userId = res.data.userInfo.id
          this.nickName = res.data.userInfo.nickname
          this.des = res.data.userInfo.des
          this.cellphone = res.data.userInfo.cellphone
          this.birthday = res.data.userInfo.birthday
          this.email = res.data.userInfo.email
          
        },
        error=>{
          alert('회원정보 요청에 실패하였습니다.')
        }
      )
    },
    update(){
      let data = {
        email:this.email,
        nickname:this.nickName,
        id:this.userId,
        des:this.des,
        birthday:this.birthday,
        cellphone:this.cellphone,
        address1:this.address.address1,
        address2:this.address.address2,
        address3:this.address.address3,
      };
      UserApi.requestUpdate(
        data,
        res=>{
          console.log(res);
          alert('회원수정이 완료 되었습니다.')
          this.$router.push(`/user/profile/${this.$store.state.userId}`);
          
          
        },
        error=>{
          alert("실패");
          this.$router.push("/error");
        }
      );
      
     
    }

  }
};
</script>