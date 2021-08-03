<template lang="">
  <div>
    <RecipeDetailList :recipe="recipe"/>
  </div>
</template>

<script>
import RecipeDetailList from "../../components/recipe/RecipeDetailList.vue";
import UserApi from '../../api/UserApi';

export default {
  components:{
    RecipeDetailList,
  },
  data: () => {
    return {
      recipe: null,
    }
  },
  mounted: function() {
    let data= {
      recipeIdx: this.$route.params.recipe_idx
    }
    UserApi.singleRecipe(
      data,
      res => {
        console.log(res)
        this.recipe = res.data["recipeContent"]
        console.log('레시피')
      },
      error => {
        console.log(error)
      }
    )
  }
}
</script>

<style lang="">
  
</style>