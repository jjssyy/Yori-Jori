

<template>
  <div>
    <div>
        <img v-bind:src="newImgSrc">
        <input type="file" id="file" class="inputfile" v-on:change="upload">
    </div>
    <div>
        <textarea  cols="30" rows="5"></textarea>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import UserApi from '../../api/UserApi';
var frm = new FormData();

export default {
  data: () => {
    return {
        newImgSrc:'',
    }
  },
  methods: {
    upload(e){
      let file = e.target.files;
      let reader = new FileReader();
      
      reader.readAsDataURL(file[0]);
      reader.onload = e => {
        this.newImgSrc = e.target.result;

      };
      frm.append("file",file[0]);
     
      
    },

     
 

  },
  
  
}
</script>