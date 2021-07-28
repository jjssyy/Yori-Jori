

<template>
  <div>
    <button @click="isThumbnail">썸네일</button>
    <div>
      <img v-bind:src="newImgSrc">
      <input type="file" accept="image/*" id="file" class="inputfile" v-on:change="uploadImg">
    </div>
    <div>
      <textarea style="border: solid;" cols="30" rows="5" v-model.trim="recipeItemDes" @change="uploadDes"></textarea>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import UserApi from '../../api/UserApi';
import FirebaseApi from '../../api/FirebaseApi';
var frm = new FormData();

export default {
  data: () => {
    return {
        img: null,
        newImgSrc:'',
        recipeItemDes: [],
        recipeItem: [],
    }
  },
  props: {
    idx: Number,
  },
  methods: {
    uploadImg(e){
      let file = e.target.files[0];
      FirebaseApi.upLoad(
        file,
        res=>{
          this.newImgSrc = res
          frm.append("file",res);
          this.$store.dispatch('uploadImg', {file: res, idx: this.idx});
        })

    },

    uploadDes: function() {
      this.$store.dispatch('writeRecipeItem', {des: this.recipeItemDes, idx: this.idx});
    },

    isThumbnail() {
      this.$store.dispatch('choiceThumbnail', this.idx);
    },
    
  },
}
</script>