<template>
<div class="navigation">
  <div class="logo">
  
    <router-link class="no-underline" :to="{name:'FeedMain'}">logo here</router-link>
  </div>
  <SearchBox/>
  <Notification/>
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
  mounted(){
    $(".fa-heart").dblclick(function () {
      $(".notification-bubble").show(400);
    });
    $(document).on("scroll", function () {
      if ($(document).scrollTop() > 50) {
        $(".navigation").addClass("shrink");
      } else {
        $(".navigation").removeClass("shrink");
      }
    });
  },
  methods: {
    backBack: function() {
      history.back();
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
@import url('http://fonts.cdnfonts.com/css/billabong');
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

.navigation .logo a {
  position: relative;
  color: #000000;
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


</style>