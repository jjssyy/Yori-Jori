<template>
  <div class="wrapC">
    <!-- <p @click="backBack">뒤로가기</p> -->
    <h1> 팔로잉</h1>
    <table class="table">
      <thead>
        <th>닉네임</th>
        <th>비고</th>
      </thead>
      <tbody>
        <FollowingItem v-for="(following, idx) in followings" :key="idx" :following="following" :idx="idx" :followings="followings" :profileId="profileId"/>
      </tbody>

    </table>
  </div>
</template>
<script>
import UserApi from '../../api/UserApi';
import FollowingItem from "../../components/profile/FollowingItem.vue";

export default {
  data: () => {
    return {
      profileId: null,
      followings: null,
    }
  },
  components: { FollowingItem },
  methods: {
    backBack: function() {
      history.back();
    }
  },
  created: function () {
    this.profileId = this.$route.params.profileId
    let data = {
      id: this.profileId
    }
    UserApi.getFollowing(
      data,
      res => {
        console.log(res.data)
        this.followings = res.data
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