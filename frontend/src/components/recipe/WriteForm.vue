

<template>
  <div>
    <div>
      <img v-bind:src="newImgSrc">
      <input type="file" id="file" class="inputfile" v-on:change="uploadImg">
    </div>
    <div>
      <textarea style="border: solid;" cols="30" rows="5" v-model.trim="recipeItemDes" @change="uploadDes"></textarea>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import UserApi from '../../api/UserApi';
var frm = new FormData();

export default {
  data: () => {
    return {
        img: null,
        newImgSrc:'',
        recipeItemDes: [],
        recipeItem: [],
        ImgList: [],
    }
  },
  props: {
    idx: Number,
  },
  methods: {
    uploadImg(e){
      let file = e.target.files;
      let reader = new FileReader();
      
      reader.readAsDataURL(file[0]);
      reader.onload = e => {
        this.newImgSrc = e.target.result;
      };
      frm.append("file",file[0]);
      this.ImgList.push(file[0])
      
      this.$store.dispatch('uploadImg', {file: file[0], idx: this.idx});
    },

    uploadDes: function() {
      this.$store.dispatch('writeRecipeItem', {des: this.recipeItemDes, idx: this.idx});
    }
    
  },
}
</script>