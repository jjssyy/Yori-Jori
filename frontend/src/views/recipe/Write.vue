

<template>
  <div>
    <div class="wrapC">
      <h1>글쓰기</h1>

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

      <div class="" id="image-des"  >
        <write-form @childs-event="imgsrc" v-for="(data, idx) in fields" :key="idx" :idx="idx"></write-form>
      </div>
          
      </div>
      <button @click="check">등록</button>
    </div>
 
</template>

<script>
import { mapState } from 'vuex'
import UserApi from '../../api/UserApi';
import WriteForm from "../../components/recipe/WriteForm.vue";
var frm = new FormData();

export default {
  data: () => {
    return {
      title: "",
      fields: [],
      count: 0,
      ThumbNailList: [],
    }
  },
  components:{
    WriteForm,
  },
  methods: {
    addimagedes(){
        this.fields.push({
          idx : this.count++,
          img : " "
        })
    },
    imgsrc:function(file){
      console.log(file);
    },

    check(){
      for (let i=0;i<this.recipeItems.length; i++){
        if( this.thumbnailNumber == i){
          this.ThumbNailList.push('true')
        }
        else{
          this.ThumbNailList.push('false')
        }
      }
      console.log(this.ThumbNailList)
      frm.append("title", this.title)
      frm.append("id", this.userId)
      for (let i=0; i< this.recipeItems.length; i++){
        frm.append("des["+i+"]",this.recipeItems[i])
        frm.append("img["+i+"]",this.img[i])
        frm.append("thumbnail["+i+"]",this.ThumbNailList[i])
      }
      // FormData의 key 확인
      for (let key of frm.keys()) {
        console.log(key);
      }

      // FormData의 value 확인
      for (let value of frm.values()) {
        console.log(typeof(value));
      }

      UserApi.createRecipe(
        frm,
        res => {
          console.log('성공')
        },
        error=> {
          console.log(error)
          this.recipeItems = [],
          this.img = [],
          this.ThumbNailList = []
        }
      )
    }
  },
  computed: {
    ...mapState([
      'userId',
      'recipeItems',
      'img',
      'thumbnailNumber',
    ]),
  }
}
</script>