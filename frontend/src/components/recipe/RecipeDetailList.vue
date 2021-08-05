<template lang="">
  <div> 
    <RecipeDetailItem v-for="(recipeItem, idx) in recipe" :key="idx" :recipeItem="recipeItem" :idx="idx" :recipeContent="recipeContent"/>
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
    },
    recipeContent: {
      type: [Array, Object]
    }
  }
}
</script>
<style lang="">
  
</style>