<template>
  <div>  
    <div class="navigation">
      <div class="logo">
        <a href="#" class="menu-bars" id="show-menu" @click="sideShow">
          <i class="fas fa-bars"></i>
        </a>
        <router-link class="no-underline" :to="{name:'FeedMain'}">logo here</router-link>
      </div>
      <div></div>
      <SearchBox/>
      <Notification/>
    </div>
    <nav id="nav-menu">
      <ul class="nav-menu-items">
        <div id="nav-toggle" @click="sideShow">
          <a href="#" class="menu-bars" id="hide-menu">
            <i class="fas fa-bars nav-icon"></i>
          </a>
          <router-link class="no-underline" :to="{name:'FeedMain'}">logo here</router-link>
        </div>
        <div class="nav-section">
          <li class="nav-text"><span href="#"><i class="fas fa-fire nav-icon"></i>Trending</span></li>
        </div>
        <hr>
        <div class="nav-section" @click="sideShow">
          <router-link :to="{name:'FeedMain'}" active-class="active" tag="button">
            <li class="nav-text"><span><i class="fas fa-home nav-icon"></i>Home</span></li>
          </router-link>
          <router-link :to="{name:'FeedMain'}" active-class="active" tag="button">
            <li class="nav-text"><span><i class="fas fa-star nav-icon"></i>star</span></li>
          </router-link>
          <router-link :to="{name:'Recipewrite'}" active-class="active" tag="button">
            <li class="nav-text"><span><i class="fas fa-pen nav-icon"></i>Write</span></li>
          </router-link>
          <router-link :to="{name:'FeedMain'}" active-class="active" tag="button">
            <li class="nav-text"><span><i class="fas fa-heart nav-icon"></i>Likes</span></li>
          </router-link>
          <router-link :to="{name:'Profile',params: {user_id: userId}}" active-class="active" tag="button">
            <li class="nav-text"><span><i class="fas fa-fire nav-icon"></i>Profile</span></li>
          </router-link>
        </div>
      </ul>
    </nav>
    <nav id="sidebar">
      <div class="menu-items">
        <router-link :to="{name:'FeedMain'}" active-class="active" tag="button">
          <div class="link-container">
            Home
          </div>
        </router-link>
        <router-link :to="{name:'FeedMain'}" active-class="active" tag="button">
          <div class="link-container">
            Star
          </div>
        </router-link>
        <router-link :to="{name:'Recipewrite'}" active-class="active" tag="button">
          <div class="link-container">
            Write
          </div>
        </router-link>
        <router-link :to="{name:'FeedMain'}" active-class="active" tag="button">
          <div class="link-container">
            Likes
          </div>
        </router-link>
        <router-link :to="{name:'Profile',params: {user_id: userId}}" active-class="active" tag="button">
          <div class="link-container">
            Profile
          </div>
        </router-link>
      </div>
    </nav>
  </div>
</template>

<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script>
import SearchBox from './Search.vue'
import Notification from './Notice.vue'
import { mapState } from 'vuex'
export default {
  components:{
    SearchBox,
    Notification,
  },
  data: ()=>{
    return{
      isShow: false,
    }
  },
  mounted(){
    $(".fa-heart").dblclick(function () {
      $(".notification-bubble").show(400);
    });
    $(document).on("scroll", function () {
      if ($(document).scrollTop() > 50) {
        $(".navigation").addClass("shrink");
        $(".nav-toggle").addClass("shrink");
      } else {
        $(".navigation").removeClass("shrink");
        $(".nav-toggle").removeClass("shrink");
      }
    });
  },
  methods: {
    backBack: function() {
      history.back();
    },
    sideShow(event){
      event.preventDefault()
      const sideMenu = document.querySelector('#nav-menu')
      if (this.isShow==false){
        sideMenu.classList.add('active')
        this.isShow = true
      } else {
        sideMenu.classList.remove('active')
        this.isShow = false
      }
    }
  },
  computed: {
    ...mapState([
      'userId',
    ]),
  }
}
</script>


<style>

hr{
  background-color: #464646;
  height: 1px;
  border: none;
}

.navigation {
  background-color: #ffffff;
  height: 80px;
  position: fixed;
  width: 100%;
  top: 0;
  left: 0;
  border-bottom: 1px solid rgba(0, 0, 0, 0.0975);
  display: flex;
  align-items: center;
  justify-content: space-around;
  padding: 0px 50px;
  box-sizing: border-box;
  z-index: 2;
  /* animation magic */
  transition: all 0.4s ease-in-out;
  -webkit-transition: all 0.4s ease-in-out;
  -moz-transition: all 0.4s ease-in-out;
}

.shrink {
  height: 50px;
}
.logo{
  width: 230px;
  height: 80px;
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  top: 0;
  left: 0%;
  transition: 850ms;
  z-index: 10000;
}

.navigation .logo a {
  position: relative;
  color: #464646;
  font-size: 30px;
  font-family: "Roboto", sans-serif;
  text-decoration: none;
}

.navigation-search-container {
  position: relative;
}

.navigation-search-container input {
  background-color: #fafafa;
  padding: 3px 20px;
  padding-left: 25px;
  height: 30px;
  box-sizing: border-box;
  border: 1px solid rgba(0, 0, 0, 0.0975);
  border-radius: 3px;
  font-size: 14px;
}

.navigation-search-container .fa-search {
  position: absolute;
  top: 10px;
  left: 10px;
  font-size: 11px;
  color: rgba(0, 0, 0, 0.5);
}

@media only screen and (min-width: 320px) and (max-width: 650px) {
  /* Navigation */
  .navigation {
    padding: 0 20px;
    justify-content: space-between;
  }
  .navigation-search-container {
    display: none;
  }
  .notification {
    display: flex;
  }
}

.navigation-search-container input:focus {
  outline: none;
}

.navigation-search-container input::placeholder {
  text-align: center;
}

.menu-bars{
  margin-right: 1rem;
  margin-left: 2rem;
  font-size: 2rem;
  color: #464646;
}
#nav-menu{
  color: #464646;
  background-color: #fff;
  width: 230px;
  height: 100%;
  display: flex;
  justify-content: center;
  position: fixed;
  top: 0;
  left: -100%;
  transition: 850ms;
  z-index: 10000;
}
#nav-menu.active{
  left: 0;
  transition: 350ms;
}
.nav-text{
  display: flex;
  justify-content: flex-start;
  list-style: none;
  height: 50px;
  padding: 1rem;
}
.nav-text span{
  text-decoration: none;
  color: #464646;
  font-size: 1.2rem;
  margin-left: 1rem;
}
.nav-text:hover {
  background-color: rgba(144, 144, 144, 0.219);
  cursor: pointer;
}
.nav-menu-items{
  padding: 0%;
  margin: 0%;
  width: 100%;
  background-color: #fff;
}
.nav-icon{
  margin-right: 1rem;
  color: #909090;
}
#nav-toggle{
  width: 100%;
  height: 80px;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  border-bottom: 1px solid rgba(0, 0, 0, 0.0975);
}
#nav-toggle a {
  position: relative;
  color: #000000;
  font-size: 30px;
  font-family: "Roboto", sans-serif;
  text-decoration: none;
}
.fa-bars{
  color: #464646;
}
.nav-section{
  background-color: #fff;
  /* height: 250px; */
  display: flex;
  flex-direction: column;
  justify-content: center;
}

#sidebar{
  transform: translate(-50%,-50%);
  position: fixed;
  top: 100%;
  left: 50%;
  width: 100%;
  z-index: 10000;
}
.menu-items{
  background-color: rgb(255, 255, 255);
  display: flex;
  transform: translate(0%,-50%);
  flex-direction: row;
  justify-content: space-around;
  width: 100%;
}
.link-container{
  background-color: rgb(255, 255, 255);
  line-height: 50px;
  height: 50px;
  width: 50px;
}

@media only screen and (min-width: 800px){
  #sidebar{
    display: none;
  }
}

</style>