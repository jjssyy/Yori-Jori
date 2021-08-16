<template>
  <div class="feed-item">
    <div class="top-a">
      <div class="user-infomation"  @click="goUserProfile">
        <div class="user-img">
          <img :src="defaultProfile" height="35px" alt="">
        </div>
        <div class="user-name">{{ latestFeed.nickname }}</div>
      </div>
      <div class="title">
        <h2 @click="goRecipeDetail">{{ latestFeed.title | longTitle}}</h2>
      </div>
      <div class="btn-group-a">
        <div class="like">
          <i class="fas fa-heart full"></i>
          <span>{{ latestFeed.like||latestFeed.likes || 0}}</span>
        </div>
        <div class="comment">
          <i class="fas fa-comment-alt full"></i>
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
  filters:{
    longTitle: function(title){
      if (title.length > 8){
        return `${title.substring(0,8)}...`
      } else {
        return title
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

<style scoped>
@font-face {
    font-family: 'BBTreeGB';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_nine_@1.1/BBTreeGB.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
*{
  margin: 0%;
  padding: 0%;
  color: rgba(0, 0, 0, 0.7);
  /* font-family: 'Pretendard-Regular'; */
}
h2{
  font-family: 'BBTreeGB';
  font-weight: 500;
}
svg{
  color: #ffbe76;
}

.feed-item{
  width: 100%;
  max-width: 616px;
  border: none;
  padding: 0%;
  padding-bottom: 50px;
  border-bottom: 1px solid rgba(0, 0,0, 0.1);
}
.top-a{
  width: 100%;
  height: 80px;
  display: flex;
  margin: 0%;
}

.user-infomation{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 0px 15px;
}
.user-name{
  font-size: 0.8rem;
}
.title{
  display: flex;
  width: 70%;
  justify-content: flex-start;
  align-items: center;
  padding-left: 20px;
}
.title>h1{
  margin: 0%;
}
.btn-group-a{
  margin-right: 20px;
  width: 120px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.btn-group-a div{
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
}

.feed-img{
  width: 100%;
}
.feed-img>img{
  margin: 0%;
  padding: 0%;
  min-width: 100%;
  border-radius: 5px;
}

</style>
