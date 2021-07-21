

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
            <button type="button">등록</button>
        </form>
      
      </div> 

      <div class="" id="add">
        <button @click="addimagedes">+</button>
      </div>
      <div class="" id="image-des">
        <ul>
          <li v-for="t in test" v-bind:key="t">
            {{t}}
          </li>
        </ul>
        
          <write-form v-for="(data, idx) in fields" :key="idx"></write-form>

        </div>
          
      </div>
    </div>
 
</template>

<script>
import { mapState } from 'vuex'
import UserApi from '../../api/UserApi';
import WriteForm from "../../components/recipe/WriteForm.vue";

export default {
  data: () => {
    return {
      title: "",
       fields: [],
      count: 0
    }
  },
  components:{
    WriteForm,
  },
  methods: {
    
    addimagedes(){
      
        this.fields.push({
          idx : this.count++,
        })
        
      
    }

  },
  computed: {
    ...mapState([
      'user',
    ]),
  }
}
</script>