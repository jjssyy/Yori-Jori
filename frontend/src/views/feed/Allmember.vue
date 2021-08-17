<template>
  <div class="feed newsfeed">
    <div class="wrapC">
      
      <h1>유저 목록</h1>

      <table class="table" id="searchmember_table">
        <thead>
          <tr>
            <th>닉네임</th>
            <th>팔로우</th>
          </tr>
        </thead>
        <tbody>
          <tr  v-for="(member, idx) in members" :key="idx">
            <td v-if="member && member.nickname.includes(searchnickname)">
              <router-link :to="{name:'Profile', params: {user_id: member.id}}" style="text-decoration:none; color:black;" >{{member.nickname}}</router-link>
            </td>
            <td v-if="member && member.nickname.includes(searchnickname)">
              <button class="btn btn-secondary" v-if="follow_already.includes(member.id)" @click="senddeletefollow(member)">이미 등록됨</button>
              <button class="btn btn-primary" v-if="!follow_already.includes(member.id)" @click="sendrequest(member)">신청</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import UserApi from '../../api/UserApi';
import "../../components/css/feed/feed-item.scss";
import "../../components/css/feed/newsfeed.scss";
import swal from 'sweetalert';

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
        loginid : this.$route.query.user_id,
        memberid : member.id,
        token : this.$store.state.token,
        
      }
      UserApi.sendfollowrequest(
      data,
      res => {
        if(res.data == "success"){
          swal("팔로우 신청을 보냈습니다.",{icon:'success'})
            this.$router.go();
        }else if(res.data == "fail"){
          swal("팔로우 신청이 보내지지 않았습니다.",{icon:'warning'})
        }else{
          swal("에러발생",{icon:'error'});
        }
      },
      error=>{
         swal("에러발생",{icon:'error'});
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
          swal("팔로우를 취소했습니다..",{icon:'success'})
            this.$router.go();
        }else if(res.data == "fail"){
          swal("팔로우 취소신청이 보내지지 않았습니다.",{icon:'warning'})
        }else{
          swal("에러발생",{icon:'error'});
        }
      },
      error=>{
        swal("에러발생",{icon:'error'});
      }
    )
    },
  },

  created() {

    this.profileId = this.$route.query.user_id
    this.searchnickname = this.$route.query.searchname
    let data = {
      id: this.profileId
    }



    UserApi.getAllmember(
      
      data,
      res => {
        this.members = res.data
        
      },
      error=>{
        console.log(error)
      }
    )
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
};
</script>

<style>
  #searchmember_table  thead{
    
    height: 50px;
    color: white;
    font-size: 22px;
    line-height: 50px;
    text-align: center;
    background-color: #ffbe76;

}

#searchmember_table{
  text-align: center;
}

  #searchmember_table  td{
    
    height: 100px;
    font-size: 22px;
    line-height: 80px;
    text-align: center;

}
</style>

