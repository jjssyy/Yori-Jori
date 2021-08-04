<template lang="">
  <div>
    {{ recipe }}
    <RecipeDetailList :recipe="recipe"/>
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
    }
  },
  mounted: function() {
    let data= {
      recipeIdx: this.$route.params.recipe_idx,
      id: this.selectRecipeId
    }
    console.log(data.id)
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
  },
  computed: {
    ...mapState([
      'selectRecipeId',
    ]),
  }
}
</script>

<style lang="">
  
</style>