<template lang="">
  <div>
    <!-- {{ recipeContent}} -->
    <hooper :progress="true" id="hooper">
      <slide class="thumbNail" :style="{ backgroundImage: `url(${thumbnailImg})` }">
      </slide>
      <slide v-for="(recipeItem, idx) in recipe" :key="idx">
        <!-- {{ recipeItem }} -->
        <!-- {{ idx+1 }} -->
        <div>
        <img :src='recipeItem.img' width="" class="recipeImg"/>
        <p>{{ recipeItem.des }}</p>
        </div>
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
    }
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
<style scope>
  img {
    /* margin-top: 10px; */
  }
  #hooper {
    height: 80vh;
  }
  .slide {
    background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);
    font-size: 4rem;
    line-height: 80vh;
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
</style>
