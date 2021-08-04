<template lang="">
  <div> 
    <RecipeDetailItem v-for="(recipeItem, idx) in recipe" :recipeId="recipe.id" :key="idx" :recipeItem="recipeItem" :idx="idx"/>
  </div>
</template>

<script>
import RecipeDetailItem from "../../components/recipe/RecipeDetailItem.vue";

export default {
  components: {
    RecipeDetailItem,
  },
  props: {
    recipe: {
      type: [Array, Object]
    }
  }
}
</script>
<style lang="">
  
</style>