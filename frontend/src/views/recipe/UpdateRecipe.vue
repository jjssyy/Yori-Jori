
<template>
  <div>
    <div class="wrapC">
      <h1>글수정</h1>
      <!-- {{recipeContent}} -->
      {{ recipeContent}}
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

      <div id="image-des">
        <div class="hash-input-group">
          <h2>TAGs</h2>
          <div class="hash-input">
            <input type="text" v-model="temphash" @keyup.enter="createHash" placeholder="태그를 입력하세요">
            <button @click="createHash"><i class="fas fa-pen nav-icon"></i></button>
          </div>
        </div>
        <div v-for="(h, idx) in recipeContent.tag" :key="idx">
          <div class="hash">
          <span># {{h}}</span>
          <span @click="deleteHash(recipeContent.hashtag_idx[idx],idx)"><i id="hashDelete" class="fas fa-times"></i></span>
          </div>
        </div>
      </div>

      <div id="image-des">
        <h2>Challenges</h2>
        <div>
          <select name="master_name" id="master_name" v-model="masterSelected" @change="updateMaster">
            <option v-for="(master_name, idx) in master_names" :key="idx" :value="master_name">
              {{ master_name }}
            </option>
          </select>
          <select name="slave_name" id="slave_name" v-model="slaveSelected">
            <option v-for="(slave_name, idx) in slave_names" :key="idx" :value="slave_name">
              {{ slave_name }}
            </option>
          </select>
        </div>
      </div>
        <button class="submit" @click="check"><h1><i class="fas fa-pen nav-icon"></i></h1></button>
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
      master_names: [],
      slave_names: [],
      masterSelected: '',
      slaveSelected: '',
      deleteHashtag: [],
      temphash:'',
    }
  },
  components:{
    UpdateForm,
  },
  methods: {
    createHash(){
      this.recipeContent.tag.push(this.temphash)
      this.recipeContent.hashtag_idx.push(-1)
      this.temphash = ''
    },
    deleteHash(tagidx, idx){
      this.recipeContent.tag.splice(idx,1)
      this.recipeContent.hashtag_idx.splice(idx,1)
      if(tagidx!=-1){
      this.deleteHashtag.push(tagidx)
      }
      console.log('dkdk')
    },
    updateMaster(){
      this.slave_names = []
      if(this.masterSelected) {
        for(let achieve of this.Achieves){
          if(this.masterSelected === achieve.achieve_master_name){
            this.slave_names.push(achieve.achieve_slave_name)
          }
        }
      }
    },
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
      console.log(this.masterSelected)
      frm.append('achieve_master',this.masterSelected)
      frm.append('achieve_slave',this.slaveSelected)
      for (let i=0; i< this.recipe.length; i++){
        frm.append("content_idx["+i+"]", this.recipe[i].idx)
        frm.append("des["+i+"]",this.recipe[i].des)
        frm.append("img["+i+"]",this.recipe[i].img)
        frm.append("thumbnail["+i+"]",this.recipe[i].thumbnail)
        frm.append("content_order["+i+"]",i)
      }
      for (let i=0; i< this.recipeContent.tag.length; i++){
        frm.append("hashtag_idx["+i+"]",this.recipeContent.hash_idx[i])
        frm.append("tag["+i+"]",this.recipeConent.tag[i])
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

      if(this.deleteHashtag.length == 0){
        this.deleteHashtag = [-1]
        frm.append("deletehashtag[0]",this.deleteHashtag[0])
      }
      else {
        for(let i=0;i<this.deleteHashtag.length; i++){
        frm.append("deletehashtag["+i+"]",this.deleteHashtag[i])
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
        this.masterSelected = res.data.recipeContent.achieve_master
        this.slaveSelected = res.data.recipeContent.achieve_slave
      },
      error => {
        console.log(error)
      }
    )
    this.$store.dispatch('clearFormdata')

    RecipeApi.achieveRecipe(
      res => {
        console.log(res)
        console.log("칭호 가져옴")
        this.Achieves = res.data.achieveList
        for(let achieve of this.Achieves){
          this.master_names.push(achieve.achieve_master_name)
        }
        this.master_names = new Set(this.master_names)

        for(let achieve of this.Achieves){
          if(this.masterSelected === achieve.achieve_master_name){
            this.slave_names.push(achieve.achieve_slave_name)
          }
        }
      },
      err=> {
        console.log(err)
      }
    )
  },
  computed: {
    ...mapState([
      'deleteContents',
    ]),
  }
}
</script>
<style scoped>
#image-des{
  display: flex;
  flex-wrap: wrap;
  width: 100%;
  padding: 2%;
}

#image-des h2{
  width: 100%;
}
#image-des .hash-input-group{
  width: 100%;
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.hash-input{
  display: flex;
  justify-content: flex-end;
  align-items: center;
  border:1px solid #FF9636;
  border-radius: 3px;
}
.hash-input input{
  height: 70%;
  background-color: #fafafa;
  border: none;
  margin-right: 2px;
}
.hash-input button{
  height: 70%;
  border-radius: 3px;
  align-items: center;
}
.hash-input svg{
  margin-right: 2px;
}

.hash{
  background-color: #FF9636;
  padding: 0px 10px;
  margin-right: 10px;
  margin-bottom: 10px;
  cursor: pointer;
  color: #fff;
  font-size: 1.2rem;
  font-weight: 500;
  min-width: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 25px;
}
#hashDelete{
  margin-top: 6px;
  margin-left: 8px;
  color: #fff;
}
.submit{
  width: 95%;
  display: inline;
  background-color: #DAD870;
  border-radius: 3px;
  margin-bottom: 5%;
}
</style>