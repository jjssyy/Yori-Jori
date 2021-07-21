<template lang="">
  <div>
    <p @click="backBack">뒤로가기</p>
    <h1> 팔로워 리스트 </h1>
    <FollowItem v-for="(follower, idx) in followers" :key="idx" :follower="follower" :idx="idx" :followers="followers" :profileId="profileId"/>
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
    this.profileId = this.$route.params.profileId
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