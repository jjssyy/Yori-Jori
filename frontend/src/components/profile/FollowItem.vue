<template lang="">
  <tr>
    <td><router-link :to="{name:'Profile', params: {user_id: follower.id}}" style="text-decoration:none; color:black;" >{{follower.nickname}}</router-link></td>
    <td><button class="btn btn-secondary" @click="deleteFollower(follower, idx)"> remove </button></td>
  </tr>
</template>

<script>
import UserApi from '../../api/UserApi';
import { mapState } from 'vuex'

export default {
  props: {
    follower: {
      type: [Array, Object],
    },
    idx: Number,
    followers: {
      type: [Array, Object],
    },
    profileId: String
  },

  methods: {
    deleteFollower: function(follower, idx) {
      const newFollowers = this.followers
      let data = {
        loginId : this.profileId,
        followerId : follower.id
      }
      UserApi.deleteFollower(
      data,
      res => {
        console.log(res)
        console.log(data)
        this.$router.go();
        console.log('삭제성공')
      },
      error=>{
        console.log(error)
      }
      )
      this.followers = newFollowers
    }
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