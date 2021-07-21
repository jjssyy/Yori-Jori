<template>
  <div id="app">
    <Navbar/>
    <router-view></router-view>
  </div>
</template>


<script>
import "./components/css/style.scss";
import Navbar from '@/components/nav/Navbar.vue'

export default {
  components:{
    Navbar
  },
  name: "app"
};
</script>
