<template>
  <div class="feed newsfeed">
    <div class="wrapB">
      
     
      <h1>유저 목록</h1>
      <div v-for="(member, idx) in members" :key="idx">
        <div v-if="member.nickname.includes(searchnickname)">
            <span><router-link :to="{name:'Profile', params: {user_id: member.id}}">닉네임 : {{member.nickname}}</router-link></span>&nbsp;
            <span><button class="btn btn-danger" v-if="follow_already.includes(member.id) " @click="senddeletefollow(member)">이미 등록됨</button></span>
            <span><button class="btn btn-primary" v-if="!follow_already.includes(member.id) " @click="sendrequest(member)">신청</button></span>
           </div>
     </div>

      
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import UserApi from '../../api/UserApi';
import "../../components/css/feed/feed-item.scss";
import "../../components/css/feed/newsfeed.scss";


export default {
  props: ["keyword"],

  data: ()=>{

    return{
      profileId: null,
      members: {
      type: [Array, Object],
    },
  
     member: {
      
    },
   
    searchnickname:'',
    follow_wait:[],
    follow_already:[],
    }
  },

  components: {  },

  computed: {
    ...mapState([
      'userId',
    ]),
  },

  methods: {
    sendrequest(member){

      let data = {
        loginid : this.$route.params.user_id,
        memberid : member.id,
        
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
  },

  created() {

    this.profileId = this.$route.params.user_id
    this.searchnickname = this.$route.params.searchname
    let data = {
      id: this.profileId
    }

    let memberlist = [];
    let follow_waitlist = [];
    let follow_alreadylist = [];

    UserApi.getAllmember(
      
      data,
      res => {
        
        this.members = res.data
        
        for(let i = 0; i < res.data.length; i++){
          memberlist.push(res.data[i].id);
          
        }
        
      },
      error=>{
        console.log(error)
      }
    )
    

     UserApi.follow_already(
      
      data,
      res => {
        this.follow_already = res.data;
    

        follow_alreadylist = res.data;
        console.log(this.follow_already);
            
     
      },
      error=>{
        console.log(error)
      }
    )
    
    
  },
};
</script>

