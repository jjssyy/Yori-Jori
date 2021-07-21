<template lang="">
  <div>
    <p @click="backBack">뒤로가기</p>
    <h1> 팔로잉 리스트 </h1>
    <FollowingItem v-for="(following, idx) in followings" :key="idx" :following="following" :idx="idx" :followings="followings" :profileId="profileId"/>
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