

<template>
  <div class="write-card">
    <div class="thumbnail" @click="isThumbnail">썸네일</div>
    <div class="image-box">
      <img class="image" :src="newImgSrc">
      <input type="file" accept="image/*" id="file" @change="uploadImg">
    </div>
    <div class="content-box">
      <textarea class="content" v-model.trim="recipeItemDes" @change="uploadDes"></textarea>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
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
          console.log(res)
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
<style scoped>
.write-card{
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 30%;
  margin: 1%;
  border: 1px solid #ffbe76;
  border-radius: 25px;

}
.thumbnail{
  width: 100%;
}
.image-box{
  width: 100%;
}
.image{
  width: 100%;
}
#file{
  width: 100%;
}
.content-box{
  width: 100%;
}
.content{
  width: 100%;
}
</style>