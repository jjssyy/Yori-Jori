
<template>
  <div>
    <div class="wrapC">
      <h1>글수정</h1>
      <!-- {{recipeContent}} -->
      <!-- {{ recipeContent}} -->
      <div class="input-with-label">
        <form>
          <input
          v-model="title"
          id="title"
          placeholder="제목을 입력하세요"
          type="text"
          />
        <label for="title">제목</label>
        </form>
      </div> 

      <div class="" id="add">
        <button @click="addimagedes">+</button>
      </div>

      <div v-for="(recipe_file, idx) in recipe" :key="idx" class="" id="image-des" >
        <update-form :recipe_file="recipe_file" :recipe="recipe" :idx="recipe_file.content_order"></update-form>
      </div>
        <button @click="check">등록</button>
      </div>
    </div>
 
</template>

<script>
import { mapState } from "vuex";
import RecipeApi from '../../api/RecipeApi';
import UserApi from '../../api/UserApi';
import UpdateForm from "../../components/recipe/UpdateForm.vue";

export default {
  data: () => {
    return {
      recipe: null,
      recipeContent: null,
      title: '',
    }
  },
  components:{
    UpdateForm,
  },
  methods: {
    addimagedes(){
      this.recipe.push({
        idx : -1,
        img : " ",
        des : " ",
        thumbnail: "false",
      })
    },
    check() {
      var frm = new FormData();
      frm.append("recipe_idx", this.$route.params.recipe_idx)
      frm.append("title", this.title)
      for (let i=0; i< this.recipe.length; i++){
        frm.append("content_idx["+i+"]", this.recipe[i].idx)
        frm.append("des["+i+"]",this.recipe[i].des)
        frm.append("img["+i+"]",this.recipe[i].img)
        frm.append("thumbnail["+i+"]",this.recipe[i].thumbnail)
        frm.append("content_order["+i+"]",i)
      }
      if(this.deleteContents.length == 0){
        const deleteContent = [-1]
        frm.append("deleteContents[0]",deleteContent[0])
      }
      else {
        for(let i=0;i<this.deleteContents.length; i++){
        frm.append("deleteContents["+i+"]",this.deleteContents[i])
        } 
      }
      // FormData의 key 확인
      for (let key of frm.keys()) {
        console.log(key);
      }

      // FormData의 value 확인
      for (let value of frm.values()) {
        console.log(value);
      }

      RecipeApi.updateRecipe(
        frm,
        res => {
          console.log("글 수정 성공")
          this.$router.push({ name: 'FeedMain'})
          this.$store.dispatch('clearFormdata')
        },
        err=> {
          console.log(err)
          frm = new FormData()
        }
      )
    }

  },
  mounted: function() {
    let data= {
      recipeIdx: this.$route.params.recipe_idx,
      id: this.userId
    }
    UserApi.singleRecipe(
      data,
      res => {
        console.log(res)
        this.recipe = res.data.recipeContent.recipe_contents
        this.recipeContent =  res.data.recipeContent
        this.title = res.data.recipeContent.title
      },
      error => {
        console.log(error)
      }
    )
    this.$store.dispatch('clearFormdata')
  },
  computed: {
    ...mapState([
      'deleteContents',
    ]),
  }
}
</script>
<style lang="">
  
</style>