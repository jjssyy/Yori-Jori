<template lang="">
  <tr>
    <td><router-link :to="{name:'Profile', query: {user_id: following.id}}" style="text-decoration:none; color:black;" >{{following.nickname}}</router-link></td>
    <td><button class="btn btn-secondary" @click="deleteFollowing(following)"> remove </button></td>
  </tr>
</template>

<script>
import UserApi from '../../api/UserApi';
import { mapState } from 'vuex'

export default {
  props: {
    following: {
      type: [Array, Object],
    },
    idx: Number,
    followings: {
      type: [Array, Object],
    },
    profileId: String,
  },

  methods: {
    deleteFollowing(following) {
      const newFollowings = this.followings
      let data = {
        loginId : this.profileId,
        followingId : following.id
      }
      UserApi.deleteFollowing(
      data,
      res => {
        
        this.$router.go();
        
      },
      error=>{
        console.log(error)
      }
    )
    this.followings = newFollowings
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