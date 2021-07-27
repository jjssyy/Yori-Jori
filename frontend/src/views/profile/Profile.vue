

<template>
  <div>
    <div class="wrapC">
      <h1>프로필</h1>
      <div>
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
  </div>
</template>

<script>
import { mapState } from 'vuex'
import UserApi from '../../api/UserApi';

export default {
  data: () => {
    return {
      profileUser: null,
      profileId: null,
      follower: null,
      following: null,
      waiting: null,
      follow_wait:[],
      follow_already:[],
    }
  },
  created: function() {
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
    data = {
      id: this.userId
    }

    

     UserApi.follow_already(
      
      data,
      res => {
        this.follow_already = res.data;
        console.log(res.data);
     
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
    showWaitList: function() {
      this.$router.push({ name: 'WaitList' , params: {profileId: this.profileId}})
    },
  },
  computed: {
    ...mapState([
      'userId',
    ]),
  }
}
</script>