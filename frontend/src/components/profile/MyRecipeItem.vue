<template lang="">
  <tr>
      <td>{{ idx+1}}</td>
      <td @click="goRecipeDetail"> {{ myRecipe.title }}</td>
      <td> {{ myRecipe.achieve_title_name }}</td>
      <td> {{ myRecipe.likes }}</td>
  </tr>
</template>

<script>
export default {
  props: {
    myRecipe: {
      type: [Array, Object],
    },
    idx: Number,
  },
  methods: {
     goRecipeDetail() {
      this.$router.push({name:'RecipeDetail', params: {recipe_idx:this.myRecipe.idx}})
      this.$store.dispatch('selectRecipe',this.latestFeed.id )
    },
  },
}
</script>

<style lang="">
  
</style>