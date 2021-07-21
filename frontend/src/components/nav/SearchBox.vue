<template>
  <div>
    <input type="text" v-model="InputText" @keyup.enter="searchInput">
    <button>검색</button>
  </div>
</template>

<script>
export default {
  data:()=>{
    return {
      InputText:''
    }
  },
  methods:{
    searchInput(){
      console.log(this.InputText)
    }
  }
}
</script>

<style>

</style>