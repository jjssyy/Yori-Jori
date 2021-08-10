<template lang="">
  <div class="wrapC">
    {{ recipeItem }}
    <!-- {{ recipeContent }} -->
    {{ idx+1 }}
    <img :src='recipeItem.img' width="400"/>
    <p>{{ recipeItem.des }}</p>
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
</template>
<script>
import { mapState } from 'vuex'
import RecipeApi from '../../api/RecipeApi';
import FirebaseApi from '../../api/FirebaseApi';
import RecipeCommentList from "../../components/recipe/RecipeCommentList.vue";

export default {
  props: {
    recipeItem: {
      type: [Array, Object]
    },
    idx: Number,
    recipeContent: {
      type: [Array, Object]
    }
  },
  data: () => {
    return {
      heart: false,
    }
  },
  components: {
    RecipeCommentList
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
      'selectRecipeId',
    ])
  }
}
</script>
<style scope>
  img {
    margin-top: 10px;
  }
</style>
