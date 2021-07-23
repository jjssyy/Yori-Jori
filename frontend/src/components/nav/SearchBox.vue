<template>
  <div>
    <input type="text" v-model="InputText" @keyup="searchInput">
    <button>
      <router-link :to="{name:'Allmember', params: {searchname: InputText,user_id: userId}}">검색</router-link>
    </button>
  </div>
</template>

<script>
import UserApi from '../../api/UserApi';
import { mapState } from "vuex";
export default {
  data:()=>{
    return {
      InputText:''
    }
  },
  computed: {
    ...mapState([
      'userId',
    ]),
  },
  methods:{
    searchInput(){
      let data = {
        nickname : this.InputText,
      };
      // UserApi.searchByNickname(
      //   data, 
      //   res=>{
      //     console.log(res)
      //   },
      //   error=>{
      //     console.log(error)
      //   }
      // )
    }
  }
}
</script>

<style>

</style>