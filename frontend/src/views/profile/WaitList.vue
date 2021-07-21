<template lang="">
  <div>
    <p @click="backBack">뒤로가기</p>
    <h1> 팔로우 신청 리스트 </h1>
    <WaitItem v-for="(waiting, idx) in waitings" :key="idx" :waiting="waiting" :idx="idx" :waitings="waitings" :profileId="profileId"/>
  </div>
</template>
<script>
import UserApi from '../../api/UserApi';
import WaitItem from "../../components/profile/WaitItem.vue";

export default {
  data: () => {
    return {
      profileId: null,
      waitings: null,
    }
  },
  components: { WaitItem },
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
    UserApi.getWaiter(
      data,
      res => {
        console.log(res.data)
        this.waitings = res.data
      },
      error=>{
        console.log(error)
      }
    )
  }
}
</script>