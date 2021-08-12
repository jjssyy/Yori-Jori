<template lang="">
    <div class="wrapC"> 
    <h1> 팔로워</h1>
    <table class="table">
      <thead>
        <th>닉네임</th>
        <th>비고</th>
      </thead>
      <tbody>
      <FollowItem v-for="(follower, idx) in followers" :key="idx" :follower="follower" :idx="idx" :followers="followers" :profileId="profileId"/>
      </tbody>

    </table>
    </div>
</template>
<script>
import UserApi from '../../api/UserApi';
import FollowItem from "../../components/profile/FollowItem.vue";

export default {
  data: () => {
    return {
      profileId: null,
      followers: null,
    }
  },
  components: { FollowItem },
  methods: {
    backBack: function() {
      history.back();
    }
  },
  created: function () {
    this.profileId = this.$route.query.profileId
    let data = {
      id: this.profileId
    }
    UserApi.getFollower(
      data,
      res => {
        console.log(res.data)
        this.followers = res.data
      },
      error=>{
        console.log(error)
      }
    )
  }
}
</script>

<style>

table{
  margin-top: 50px;
}
table thead{
    
    height: 50px;
    color: white;
    font-size: 22px;
    line-height: 50px;
    text-align: center;
    background-color: #ffbe76;

}

 td{
    
    height: 100px;
    font-size: 22px;
    line-height: 80px;
    text-align: center;

}
</style>