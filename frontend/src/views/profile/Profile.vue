

<template>
  <div>
    <div class="wrapC">
      <h1>프로필</h1>
      <div>
      <p>닉네임 : {{ profileUser.nickname }}</p>
      <p>한줄 소개 : {{ profileUser.des }}</p>
      <p>등급 : {{ profileUser.role }}</p>
      </div>
      <p>회원가입일 : {{ profileUser.regdate }}</p>
      <div @click="showFollowerList">팔로워 : {{ profileUser.follower }}</div>
      <div @click="showFollowingList">팔로잉 : {{ profileUser.following }}</div>
      <div></div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import UserApi from '../../api/UserApi';

export default {
  data: () => {
    return {
      profileUser: null,
      profileId: null,
      follower: null,
      following: null,
    }
  },
  created: function() {
    this.profileId = this.$route.params.user_id
    let data = {
      id: this.profileId
    }
    UserApi.getUser(
      data,
      res => {
        console.log(res)
        this.profileUser = res.data
        this.follower = res.data.follower
        this.following = res.data.following
      },
      error=>{
        console.log(error)
      }
    )
  },
  methods: {
    showFollowerList: function() {
      this.$router.push({ name: 'FollowerList' , params: {profileId: this.profileId}})
    },
    showFollowingList: function() {
      this.$router.push({ name: 'FollowingList' , params: {profileId: this.profileId}})
    },
  },
  computed: {
    ...mapState([
      'userId',
    ]),
  }
}
</script>