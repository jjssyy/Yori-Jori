

<template>
  <div>
    <button @click="isThumbnail">썸네일  |</button>
    <button @click="deleteContent(idx)">|  삭제 </button>
    <p>
      {{ idx }}{{recipe_file}}
    </p>
    <div>
      <img v-bind:src="recipe_file.img" width="400px">
      <input type="file" accept="image/*" id="file" class="inputfile" v-on:change="uploadImg">
    </div>
    <div>
      <textarea style="border: solid;" cols="30" rows="5" v-model.trim="recipe_file.des"></textarea>
    </div>
    <button @click="leftContent">위로  |</button>
    <button @click="rightContent">|  아래로</button>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import UserApi from '../../api/UserApi';
import FirebaseApi from '../../api/FirebaseApi';

export default {
  props: {
    recipe_file: {
      type: [Array, Object]
    },
    recipe: {
      type: [Array, Object]
    },
    idx: Number
  },
  data: () => {
    return {
        recipeItemDes: [],
        recipeItem: [],
    }
  },
  mounted: function(){
  },
  methods: {
    leftContent() {
      if (this.idx >= 1) {
        const content = this.recipe[this.idx - 1]
        this.idx -= 1
        this.recipe_file.content_order -= 1
        const idx = this.idx + 1
        this.recipe[this.idx] = this.recipe[this.idx + 1]
        this.recipe[this.idx + 1] = content
        this.recipe[this.idx + 1].content_order += 1
      }
    },
    rightContent() {
      if (this.idx < this.recipe.length-1) {
        const content = this.recipe[this.idx + 1]
        this.idx += 1
        this.recipe_file.content_order += 1

        this.recipe[this.idx] = this.recipe[this.idx - 1]
        this.recipe[this.idx - 1] = content
        this.recipe[this.idx - 1].content_order -= 1
      }
    },
    uploadImg(e){
      let file = e.target.files[0];
      FirebaseApi.upLoad(
        file,
        res=>{
          this.recipe_file.img = res
        })
    },
    deleteContent: function (idx) {
      this.recipe.splice(idx,1)
      this.$store.dispatch('deleteContent',this.recipe_file.idx)
    },
    isThumbnail(idx) {
      this.recipe_file.thumbnail = true
      for(let i=0;i<this.recipe.length; i++){
        if(idx!=i){
          this.recipe[i].thumbnail = false
        }
      }
    },
    
  },
}
</script>