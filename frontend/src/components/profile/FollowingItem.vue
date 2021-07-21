<template lang="">
  <div>
    <p>{{ following.nickname }}</p>
    <button class="delete" @click="deleteFollowing(following, idx)"> remove </button>
  </div>
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