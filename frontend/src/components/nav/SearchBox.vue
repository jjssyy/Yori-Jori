<template>
  <div>
    <input type="text" v-model="InputText" @keyup="searchInput">
    <button>검색</button>
  </div>
</template>

<script>
import UserApi from '../../api/UserApi';

export default {
  data:()=>{
    return {
      InputText:''
    }
  },
  methods:{
    searchInput(){
      let data = {
        nickname : this.InputText,
      };
      UserApi.searchByNickname(
        data, 
        res=>{
          console.log(res)
        },
        error=>{
          console.log(error)
        }
      )
    }
  }
}
</script>

<style>

</style>