<template lang="">
  <div class="feed-item">
    <div class="top">
      <div class="user-info">
        <div class="user-img">
          <img :src="defaultProfile" height="30px" alt="" @click="goUserProfile">
        </div>
        <div class="user-name" @click="goUserProfile">{{ latestFeed.id }}</div>
      </div>
      <div class="title">
        <h1 @click="goRecipeDetail">{{ latestFeed.title }}</h1>
      </div>
      <div class="btn-group">
        <div class="like">
          <i class="fas fa-heart icon full"></i>
          0
        </div>
        <div class="comment">
          <i class="far fa-comment-alt icon"></i>
          0
        </div>
        <div class="share">
          <i class="fas fa-share"></i>
        </div>
      </div>
    </div>
    <div class="feed-img">
      <img :src="defaultImage" alt="기본 이미지">
    </div>
  </div>
</template>
<script>
import defaultImage from "../../assets/images/img-placeholder.png";
import defaultProfile from "../../assets/images/profile_default.png";

export default {  
  data: () => {
    return { defaultImage, defaultProfile };
  },
  props: {
    latestFeed: {
      type: [Array, Object],
    },
    idx: Number,
  },
  methods: {
    goRecipeDetail() {
      this.$router.push({name:'RecipeDetail', params: {recipe_idx:this.latestFeed.idx}})
    },
    goUserProfile() {
      this.$router.push({name:'Profile', params: {user_id: this.latestFeed.id}})
    }
  }
}
</script>
<style scoped>
.feed-item{
  width: 100%;
  padding-top: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.top{
  background-color: beige;
  width: 90%;
  height: 80px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin: 0%;
}
.user-info{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding-left: 10px;
}
.title{
  display: flex;
  height: 100%;
  width: 50%;
  justify-content: flex-start;
  align-items: center;
  margin: 0%;
}
.title>h1{
  margin: 0%;
}
.btn-group{
  width: 100px;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  color: blue;
  padding-right: 10px;
}
.feed-img{
  width: 90%;
}
.feed-img>img{
  width: 100%;
}

</style>