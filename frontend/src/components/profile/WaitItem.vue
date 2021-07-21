<template lang="">
  <div>
    <p>{{ waiting.nickname }}</p>
    <button class="enroll" @click="enrollfollow(waiting, idx)"> 수락 </button>
    <button class="delete" @click="deletefollower(waiting, idx)"> 삭제 </button>
  </div>
</template>

<script>
import UserApi from '../../api/UserApi';
import { mapState } from 'vuex'

export default {
  props: {
    waiting: {
      type: [Array, Object],
    },
    idx: Number,
    waitings: {
      type: [Array, Object],
    },
    profileId: String
  },

  methods: {

    enrollfollow: function(waiting, idx) {

     

      let data = {
        loginid : this.profileId,
        waitingid : waiting.id
      }
      
      UserApi.enrollWaiting(
      data,
      res => {
        if(res.data == "success"){
          alert("팔로워에 추가하였습니다.")
        }else if(res.data == "fail"){
          alert("팔로우 수락에 문제가 생겼습니다.")
        }else{
          alert("에러발생");
        }
      },
      error=>{
         alert("에러발생");
      }
    )
    },

    deletefollower: function(waiting, idx) {

      let data = {
        loginid : this.profileId,
        waitingid : waiting.id
      }
      
      UserApi.deleteWaiting(
      data,
      res => {
        if(res.data == "success"){
          alert("팔로워 요청을 거절하였습니다.")
        }else if(res.data == "fail"){
          alert("팔로우 거절에 문제가 생겼습니다.")
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
    computed: {
    ...mapState([
      'userId',
    ]),
  }
}
</script>

<style lang="">
  
</style>