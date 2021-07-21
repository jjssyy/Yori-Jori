<template>
  <div class="navbar">
    <button class="back" @click="backBack">
      뒤로가기
    </button>
    <SearchBox/>
    <Notification/>
  </div>
</template>

<script>
import Notification from './Notification.vue'
import SearchBox from './SearchBox.vue'

export default {
  components:{
    Notification,
    SearchBox
  },
  methods: {
    backBack: function() {
      history.back();
    }
  },
}
</script>

<style>
.navbar{
  background-color: slategray;
  padding: 0% 5%;
  margin-bottom: 20px;
}
</style>