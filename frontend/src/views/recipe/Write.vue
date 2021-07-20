

<template>
  <div>
    <div class="wrapC">
      <h1>글쓰기</h1>

      <div class="input-with-label">
        <input
          v-model="title"
          id="title"
          placeholder="제목을 입력하세요"
          type="text"
        />
        <label for="title">제목</label>
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
          
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import UserApi from '../../api/UserApi';


export default {
  data: () => {
    return {
      title: "",
      test : []
    }
  },
  
  methods: {
    
    addimagedes(){
      alert('!!');
    }

  },
  computed: {
    ...mapState([
      'user',
    ]),
  }
}
</script>