<template lang="">
  <div>
    {{ recipe }}
    <img :src='recipe.recipeContent[0].img'/>
  </div>
</template>

<script>
import UserApi from '../../api/UserApi';

export default {
  data: () => {
    return {
      recipe: null,
    }
  },
  created: function() {
    let data= {
      recipeIdx: this.$route.params.recipe_idx
    }
    UserApi.singleRecipe(
      data,
      res => {
        console.log(res)
        this.recipe = res.data
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