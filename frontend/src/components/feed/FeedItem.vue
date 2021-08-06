<template>
  <div class="feed-item">
    <div class="top">
      <div class="user-info">
        <div class="user-img">
          <img :src="defaultProfile" height="40px" alt="" @click="goUserProfile">
        </div>
        <div class="user-name" @click="goUserProfile">{{ latestFeed.nickname }}</div>
      </div>
      <div class="title">
        <h2 @click="goRecipeDetail">{{ latestFeed.title }}</h2>
      </div>
      <div class="btn-group">
        <div class="like">
          <i class="fas fa-heart full"></i>
          <span>0</span>
        </div>
        <div class="comment">
          <i class="far fa-comment-alt"></i>
          <span>0</span>
        </div>
        <div class="share">
          <i class="fas fa-share"></i>
        </div>
      </div>
    </div>
    <div class="feed-img" @click="goRecipeDetail">
      <img :src="latestFeed.img || defaultImage" alt="기본 이미지">
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
*{
  font-family: "Roboto", sans-serif;
  font-weight: 300;
  margin: 0%;
  padding: 0%;
}

.feed-item{
  width: 100%;
  max-width: 616px;
  margin: 10px 0px;
  border: 1px solid rgba(0, 0, 0, 0.2);
}

.top{
  width: 100%;
  height: 80px;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  margin: 0%;
}

.user-info{
  width: 15%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding-left: 10px;
}
.user-name{
  font-size: 0.8rem;
}
.title{
  display: flex;
  width: 50%;
  justify-content: flex-start;
  align-items: center;
}
.title>h1{
  margin: 0%;
  font-weight: 300;
}
.btn-group{
  margin-right: 20px;
  width: 95px;
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.btn-group div{
  color: rgba(0, 0, 0, 0.5);
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.like svg{
  margin-right: 5px;
}
.comment svg{
  margin-right: 5px;
  color: #191919;
}

.feed-img{
  width: 100%;
}
.feed-img>img{
  margin: 0%;
  padding: 0%;
  width: 100%;
}

</style>