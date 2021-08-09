

<template>
  <div>
     <div class="wrapC" v-if="profileUser">
      <h1>프로필</h1>
      <div>
        <img class="userImg" :src="profileUser.img || defaultProfile" :alt="profileUser.nickname">
        <p>아이디 : {{profileUser.id}}</p>
        <p>닉네임 : {{ profileUser.nickname }}</p>
        <p>한줄 소개 : {{ profileUser.des }}</p>
        <p>등급 : {{ profileUser.role }}</p>
      </div>
      <p>회원가입일 : {{ profileUser.regdate }}</p>
      <div v-if="profileUser.id != userId">
        <span><button class="btn btn-danger" v-if="follow_already.includes(profileUser.id) " @click="senddeletefollow(profileUser)">이미 등록됨</button></span>
        <span><button class="btn btn-primary" v-if="!follow_already.includes(profileUser.id)  " @click="sendrequest(profileUser)">신청</button></span>
      </div>
      <div v-if="profileUser.id != userId">
        <div >팔로워 : {{ profileUser.follower }}</div>
        <div >팔로잉 : {{ profileUser.following }}</div>
      </div>
      <div v-if="profileUser.id == userId">
        <div @click="showFollowerList">팔로워 : {{ profileUser.follower }}</div>
        <div @click="showFollowingList">팔로잉 : {{ profileUser.following }}</div>
      </div>
       <div v-if="profileUser.id == userId">
        <my-recipe-item v-for="(myRecipe, idx) in myRecipes" :key="idx" :myRecipe="myRecipe" :idx="idx">
        </my-recipe-item>
      </div>
      <div>
        <div class="wrap">
          <p>비밀번호를 변경하시겠습니까</p>
          <router-link to="/user/changepassword" class="btn--text">비밀번호 변경</router-link>
        </div>
        <div class="wrap">
          <p>회원정보 변경</p>
          <router-link to="/user/update" class="btn--text">변경하기</router-link>
        </div>
      </div>
    </div>
      <button @click="Logout">로그아웃</button>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import UserApi from '../../api/UserApi';
import MyRecipeItem from '../../components/profile/MyRecipeItem.vue';
import defaultProfile from "@/assets/images/profile_default.png";

export default {
  components: { 
    MyRecipeItem,
  },
  data: () => {
    return {
      profileUser: null,
      profileId: null,
      follower: null,
      following: null,
      myRecipes: null,
      waiting: null,
      follow_wait:[],
      follow_already:[],
      defaultProfile
    }
  },
  mounted: function() {
    this.profileId = this.$route.params.user_id

    let data = {
      id: this.profileId
    }
    
    const config =  this.$store.state.token;
    
    UserApi.getUser(config,
      data,
      res => {
        this.profileUser = res.data
        this.follower = res.data.follower
        this.following = res.data.following
      },
      error=>{
        console.log(error)
      }
    )

    UserApi.myAllRecipes(
      data,
      res => {
        this.myRecipes = res.data.latestFeed
      },
      error=>{
        console.log(error)
      }
    )
    data = {
      id: this.userId
    }

    UserApi.follow_already(
      data,
      res => {
        this.follow_already = res.data;
      },
      error=>{
        console.log(error)
      }
    )
  },
  methods: {
    sendrequest(member){
      let data = {
        loginid : this.userId,
        memberid : member.id,
        token : this.$store.state.token,
      }
      UserApi.sendfollowrequest(
      data,
      res => {
        if(res.data == "success"){
          alert("팔로우 신청을 보냈습니다.")
        }else if(res.data == "fail"){
          alert("팔로우 신청이 보내지지 않았습니다.")
        }else{
          alert("에러발생");
        }
      },
      error=>{
         alert("에러발생");
      }
    )
     
    },


    senddeletefollow(member){
      let data = {
        loginid : this.userId,
        memberid : member.id,
        token : this.$store.state.token,
      }
      UserApi.sendfollowdelete(
      data,
      res => {
        if(res.data == "success"){
          alert("팔로우를 취소했습니다..")
        }else if(res.data == "fail"){
          alert("팔로우 취소신청이 보내지지 않았습니다.")
        }else{
          alert("에러발생");
        }
      },
      error=>{
         alert("에러발생");
      }
    )
     
    },
    showFollowerList: function() {
      this.$router.push({ name: 'FollowerList' , params: {profileId: this.profileId}})
    },
    showFollowingList: function() {
      this.$router.push({ name: 'FollowingList' , params: {profileId: this.profileId}})
    },
    Logout: function() {
      console.log('logout 됨')
      // 로그아웃 처리
      this.$store.dispatch('logout')
      this.$router.push({ name: 'Login' })
    },
  },
  computed: {
    ...mapState([
      'userId',
    ]),
  }
}
</script>

<style scoped>
.userImg{
  height: 50px;
  width: 50px;
  border-radius: 25px;
}
</style>