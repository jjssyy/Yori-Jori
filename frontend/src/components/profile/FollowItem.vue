<template lang="">
  <div>
    <p>{{ follower.nickname }}</p>
    <button class="delete" @click="deleteFollower(follower, idx)"> remove </button>
  </div>
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
      let data = {
        loginId : this.profileId,
        followerId : follower.id
      }
      UserApi.deleteFollower(
      data,
      res => {
        console.log(res)
        console.log(data)
        console.log('삭제성공')
      },
      error=>{
        console.log(error)
      }
    )
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