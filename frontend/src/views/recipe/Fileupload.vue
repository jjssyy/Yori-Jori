

<template>
  <div>
    <h1>업로드 연습</h1>
    <form>
       <img v-bind:src="newImgSrc">
    <input type="file" id="file" class="inputfile" v-on:change="upload">
    <button type="button">등록</button>

    </form>
   
  </div>
</template>

<script>
import { mapState } from 'vuex'
import UserApi from '../../api/UserApi';
var frm = new FormData();

export default {
  data: () => {
    return {
      img:[],
      newImgSrc:'',
      path:'',
     
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
      console.log(frm)
      UserApi.fileupload(
          frm,
          res => {
            console.log(res);
             if(res.data == "success"){
           alert("일단감")


          }
          },
          error => {
            
            alert("아이디가 존재하지 않거나 비밀번호가 틀렸습니다.");

            this.$router.push("/error");
          }
        );
     
      
    },

     
 

  },
  computed: {
    
  }
}
</script>