<template lang="">
  <div class="content_box">
    <!-- {{ recipeContent}} -->
    <hooper :progress="true" id="hooper">
      <slide class="thumbNail" :style="{ backgroundImage: `url(${thumbnailImg})` }">
        <div class="thumbnailContent">
          <div class="bg"></div>
          <div class="name_time">
          <p class="recipe_content_nickname" style="color:white;">작성자 : {{ recipeContent.nickname}}</p>
          <p class="time" style="color:white;">{{ date }} {{ recipeContent.regdate }}</p>
          </div>
        <h1 style="color:white;">{{ recipeContent.title}}</h1>
        <div class="hashlist">
        <div v-for="(h, idx) in recipeContent.tag" :key="idx">
          <span class="hash"># {{h}}</span>
        </div>
        </div>

        </div>
      </slide>
      <slide v-for="(recipeItem, idx) in recipe" :key="idx">
        <!-- {{ recipeItem }} -->
        <!-- {{ idx+1 }} -->
        <div>
        <img :src='recipeItem.img' width="" class="recipeImg"/>
        <p class="recipe_content">{{ recipeItem.des }}</p>
        </div>
        <!-- <RecipeCommentList :recipeItem="recipeItem" :recipeContent="recipeContent"/> -->
      </slide>
      <!-- <hooper-progress slot="hooper-addons"></hooper-progress> -->
      <hooper-navigation slot="hooper-addons"></hooper-navigation>
    </hooper>
    <div v-for="(recipeItem, idx) in recipe" :key="idx">
      <div>
        <span v-show="recipeContent.likecheck == false">
          <button class="submit" @click="like(recipeItem.idx)">
            <i class="far fa-heart"></i>
          </button>
        </span>
        <span v-show="recipeContent.likecheck == true">
          <button class="submit" @click="Unlike">
            <i class="fas fa-heart"></i>
          </button>   
        </span>
        <p class="mb-0" style="font-size:12px;">
          {{ recipeContent.like }}
        </p>
      </div>
      <RecipeCommentList :recipeItem="recipeItem" :recipeContent="recipeContent"/>
    </div>
    </div>
</template>

<script>
import { mapState } from 'vuex'
import RecipeApi from '../../api/RecipeApi';
import FirebaseApi from '../../api/FirebaseApi';
import RecipeCommentList from "../../components/recipe/RecipeCommentList.vue";
import { Hooper, Slide, Navigation as HooperNavigation } from "hooper";
import 'hooper/dist/hooper.css';

export default {
  components: {
    RecipeCommentList,
    Hooper, Slide, HooperNavigation,
  },
  props: {
    recipe: {
      type: [Array, Object]
    },
    recipeContent: {
      type: [Array, Object]
    },
    thumbnailImg: String,
  },
  data: () => {
    return {
      date: '',
    }
  },
  mounted: function() {
    var a = this.recipeContent.regdate.slice(0,4)
    a += '.'
    a += this.recipeContent.regdate.slice(5,7)
    a += '.'
    a += this.recipeContent.regdate.slice(8,10)
    a += ' '
    a += this.recipeContent.regdate.slice(11,16)
    this.date = a
    console.log(this.date)
  },
  methods: {
    change(idx){
      console.log(idx)
    },
    like() {
      let data = {
        recipe_idx: this.recipe[0].recipe_idx,
        recipe_user_id: this.userId
      }
      console.log(data)
      RecipeApi.likeRecipe(
        data,
        res => {
          console.log("글 좋아요 성공")
          this.recipeContent.like += 1
          this.recipeContent.likecheck = true
        },
        err => {
          console.log(err)
        }
      )
      let notice = {
        user:this.recipeContent.id,
        img:this.$store.state.userId,
        ReqUser:this.$store.state.userId,
        type:'like'
      }
      FirebaseApi.noticeAdd(notice)
    },
    Unlike() {
      let data = {
        recipe_idx: this.recipe[0].recipe_idx,
        recipe_user_id: this.userId
      }
      RecipeApi.unlikeRecipe(
        data,
        res => {
          console.log("글 좋아요 취소 성공")
          this.recipeContent.like -= 1
          this.recipeContent.likecheck = false
        },
        err => {
          console.log(err)
        }
      )
    },
  },
  computed: {
    ...mapState([
      'userId',
    ])
  }
}
</script>
<style scoped>
  img {
    /* margin-top: 10px; */
  }
  #hooper {
    height: 70vh;
  }
  .slide {
    background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);
    font-size: 4rem;
    line-height: 70vh;
  }
  .recipeImg {
    height: 60vh;
    width: 100%;
    object-fit: cover;
  }
  .thumbNail {
    background-position: center;
    background-repeat : no-repeat;
    background-size: cover;
  }
  .bg{
    background-color:rgba(0, 0, 0, 0.5);
    width: 100%;
    height: 100%;
    position: absolute;
  }
  h1{
    margin-top: 10px;
    margin-bottom: 10px;
    position: relative; 
    z-index:1000;
    top: 200px;
    left: 20px;
    text-align: left;
  }
  .recipe_content{
    padding: 5px;
    font-size: 10px;
  }
  .name_time p{
    margin: 0px;
    position: relative; 
    z-index:1000;
    top: 200px;
    left: 20px;
    text-align: left;
  }
  .time {
    font-size: 12px;
  }
  .hashlist{
    position: relative; 
    z-index:1000;
    top: 200px;
    left: 15px;
    text-align: left;
    display: flex;
    /* overflow: auto; */
    width: 100%;
    flex-wrap: wrap;
  }
  .hash{
  background-color: rgba(0, 0, 0, 0.5);
  padding: 2px 10px;
  margin-right: 10px;
  margin-bottom: 10px;
  cursor: pointer;
  color: #fff;
  font-size: 0.9rem;
  font-weight: 500;
  min-width: 50px;
  border-radius: 25px;
  }
</style>
