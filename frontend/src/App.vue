<template>
  <div id="app">
<<<<<<< HEAD
    <Navbar/>
=======
    <nav>
      
    </nav>
>>>>>>> 73fb7665421217fea509d977d129e3fe9a8588f8
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
