<template lang="">
  <div class="wrapC">
    <!-- {{ recipeItem }} -->
    {{ idx+1 }}
    <img :src='recipeItem.img' width="400"/>
    <p>{{ recipeItem.des }}</p>
    <RecipeCommentList :recipeItem="recipeItem" />
  </div>
</template>
<script>
import RecipeCommentList from "../../components/recipe/RecipeCommentList.vue";

export default {
  props: {
    recipeItem: {
      type: [Array, Object]
    },
    idx: Number,
  },
  components: {
    RecipeCommentList
  }
}
</script>
<style scope>
  img {
    margin-top: 10px;
  }
</style>