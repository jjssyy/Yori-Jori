<template>
  <div class="feed-item">
    <div class="top">
      <div class="user-info">
        <div class="user-img">
          <!-- {{ latestFeed }} -->
          <img :src="defaultProfile" height="30px" alt="" @click="goUserProfile">
        </div>
        <div class="user-name" @click="goUserProfile">{{ latestFeed.nickname }}</div>
      </div>
      <div class="title">
        <h1 @click="goRecipeDetail">{{ latestFeed.title }}</h1>
      </div>
      <div class="btn-group">
        <div class="like">
          <i class="fas fa-heart icon full"></i>
          {{ latestFeed.like }}
        </div>
        <div class="comment">
          <i class="far fa-comment-alt icon"></i>
          0
        </div>
        <div class="share">
          <i class="fas fa-share"></i>
        </div>
        <div v-if="latestFeed.id == userId">
        <p @click="deleteRecipe">삭제</p>
        <p @click="updateRecipe">수정</p>
        </div>

      </div>
    </div>
    <div class="feed-img">
      <img :src="latestFeed.img" alt="기본 이미지">
    </div>
    <!-- {{ latestFeed }} -->
  </div>
</template>
<script>
import defaultImage from "../../assets/images/img-placeholder.png";
import defaultProfile from "../../assets/images/profile_default.png";
import RecipeApi from '../../api/RecipeApi';
import { mapState } from "vuex";

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
      this.$store.dispatch('selectRecipe',this.latestFeed.id )
    },
    goUserProfile() {
      this.$router.push({name:'Profile', params: {user_id: this.latestFeed.id}})
    },
    updateRecipe() {
      this.$router.push({name:'UpdateRecipe', params: {recipe_idx:this.latestFeed.idx}})
    },
    deleteRecipe() {
      const newFeed = this.latestFeeds
      console.log(newFeed)
      let data = {
        recipe_idx: this.latestFeed.idx
      }
      RecipeApi.deleteRecipe(
        data,
        res => {
          console.log(res)
          console.log("글 삭제 성공")
          newFeed.splice(this.idx,1)
        },
        err => {
          console.log(err)
        }
      )
      this.lastetFeeds = newFeed
    },
  },
  computed: {
    ...mapState([
      'userId',
    ]),
  }
}
</script>

<style scoped>
*{
  color: #464646;
}

.feed-item{
  width: 100%;
  padding-top: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.top{
  background-color: #ffbe76;
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
