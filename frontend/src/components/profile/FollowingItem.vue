<template lang="">
  <tr>
    <td>{{ following.nickname }}</td>
    <td><button class="btn btn-secondary" @click="deleteFollowing(following, idx)"> remove </button></td>
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
    deleteFollowing: function(following, idx) {
      const newFollowings = this.followings
      let data = {
        loginId : this.profileId,
        followingId : following.id
      }
      UserApi.deleteFollowing(
      data,
      res => {
        console.log(res)
        console.log(data)
        newFollowings.splice(idx,1)
        console.log('삭제성공')
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