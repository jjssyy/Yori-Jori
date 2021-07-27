<template>
  <div id="app">
    <Navbar/>
    <Sidebar />
    <div class="main">
      <router-view></router-view>
    </div>
  </div>
</template>


<script>
import "./components/css/style.scss";
import Navbar from '@/components/nav/Navbar.vue'
import Sidebar from '@/components/sidebar/Sidebar.vue'

export default {
  components:{
    Navbar,
    Sidebar
  },
  name: "app"
};
</script>
<style scoped>
.main{
  position: relative;
  top: 100px;
}
</style>