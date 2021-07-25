<template>
  <div class="navigation-search-container">
    <i class="fa fa-search"></i>
    <input class="search-field" type="text" placeholder="Search">
    <div class="search-container">
      <div class="search-container-box">
        <div class="search-results">

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import UserApi from '../../api/UserApi';

export default {
  name:'searchbox',
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
