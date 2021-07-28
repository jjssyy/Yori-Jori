<template>
  <div id="sidebar">
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
  </div>
</template>

<script>
export default {
  computed:{
    userId(){
      return this.$store.state.userId
    }
  }
  
}
</script>

<style scoped>
#sidebar{
  transform: translate(0%,-50%);
  position: fixed;
  top: 50%;
}
.menu-items{
  display: flex;
  flex-direction: column;
  background-color: rgb(255, 255, 255);
}
.link-container{
  background-color: rgb(255, 255, 255);
  line-height: 50px;
  height: 50px;
  width: 50px;
}
@media only screen and (min-width: 320px) and (max-width: 650px) {
  /* Navigation */
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
    border: 1px solid rgba(0, 0, 0, 0.0975);
    border-radius: 3px;
    display: flex;
    transform: translate(0%,-50%);
    flex-direction: row;
    justify-content: space-around;
    width: 100%;
  }
}
</style>