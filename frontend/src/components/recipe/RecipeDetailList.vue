<template lang="">
  <div>
    <!-- {{ recipeContent}} -->
    <hooper :progress="true" id="hooper">
      <slide v-for="(recipeItem, idx) in recipe" :key="idx">
        <!-- {{ recipeItem }} -->
        {{ idx+1 }}
        <img :src='recipeItem.img' width="400"/>
        <p>{{ recipeItem.des }}</p>
      </slide>
      <hooper-pagination slot="hooper-addons" mode="fraction"></hooper-pagination>
    </hooper>
    <div v-for="(recipeItem, idx) in recipe" :key="idx">
      <div>
        <span v-show="recipeContent.likecheck == false">
          <button class="submit" @click="like">
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
import { Hooper, Slide, Pagination as HooperPagination } from "hooper";
import 'hooper/dist/hooper.css';

export default {
  components: {
    RecipeCommentList,
    HooperPagination, Hooper, Slide,
  },
  props: {
    recipe: {
      type: [Array, Object]
    },
    recipeContent: {
      type: [Array, Object]
    }
  },
  data: () => {
    return {
      data: [
      ]
    }
  },
  methods: {
    like() {
      let data = {
        recipe_idx: this.recipeItem.recipe_idx,
        recipe_user_id: this.userId
      }

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
        recipe_idx: this.recipeItem.recipe_idx,
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
    margin-top: 10px;
  }
  #hooper {
    height: 40vh;
  }
  .slide {
    background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);
    font-size: 4rem;
    line-height: 40vh;
  }
</style>
