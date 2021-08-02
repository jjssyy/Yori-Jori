<template lang="">
  <div>
     <write-form @childs-event="imgsrc" v-for="(data, idx) in fields" :key="idx"></write-form>
  </div>
</template>
<script>
import WriteForm from "../../components/recipe/WriteForm.vue";

export default {
  
}
</script>
<style lang="">
  
</style>