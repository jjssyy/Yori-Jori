<template lang="">
  <div>
    {{ idx+1 }} : {{ myRecipe.title }}
  </div>
</template>

<script>
export default {
  props: {
    myRecipe: {
      type: [Array, Object],
    },
    idx: Number,
  }
}
</script>

<style lang="">
  
</style>