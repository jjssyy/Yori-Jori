<template lang="">
  <div class="wrapC">
    레시피 제목 : {{ recipeTitle }}
    <RecipeDetailList :recipe="recipe" :recipeContent="recipeContent" :thumbnailImg="thumbnailImg"/>
  </div>
</template>

<script>
import RecipeDetailList from "../../components/recipe/RecipeDetailList.vue";
import UserApi from '../../api/UserApi';
import { mapState } from "vuex";


export default {
  components:{
    RecipeDetailList,
  },
  data: () => {
    return {
      recipe: null,
      recipeTitle: '',
      recipeContent: null,
      thumbnailImg: '',
    }
  },
  mounted: function() {
    let data= {
      recipeIdx: this.$route.params.recipe_idx,
      id: this.userId
    }
    UserApi.singleRecipe(
      data,
      res => {
        console.log(res)
        this.recipe = res.data.recipeContent.recipe_contents
        this.recipeContent =  res.data.recipeContent
        this.recipeTitle = res.data.recipeContent.title
        console.log('레시피')
        for (let i=0; i<this.recipe.length; i++){
          if(this.recipe[i].thumbnail === "true"){
            this.thumbnailImg = this.recipe[i].img
          }
        }
      },
      error => {
        console.log(error)
      }
    )
  },
  computed: {
    ...mapState([
      'userId',
    ]),
  }
}
</script>

<style lang="">
  
</style>