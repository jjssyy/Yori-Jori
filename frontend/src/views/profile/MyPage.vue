

<template>
  <div>
    <div class="wrapC">
      <h1>프로필</h1>
      <div>
      <p>{{ profileUser.nickname }}</p>
      <p>{{ profileUser.des }}</p>
      </div>
      <p>{{ profileUser.regdate }}</p>
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
    }
  },
  created: function() {
    let data = {
      userId: this.$route.params.user_id
    }
    UserApi.getUser(
      data,
      res => {
        console.log(res)
        this.profileUser = res.data
      },
      error=>{
        console.log(error)
      }
    )
  },
  computed: {
    ...mapState([
      'user',
    ]),
  }
}
</script>