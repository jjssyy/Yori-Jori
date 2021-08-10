
<template>
  <div class="write">
    <div class="write-form">
      <h1>레시피 작성</h1>

      <input
      v-model="title"
      id="title"
      placeholder="제목을 입력하세요"
      type="text"
      />

      <div id="image-des">
        <h2>CARDs</h2>
        <div v-for="(data, idx) in fields" :key="idx" class="write-card" @click="updateCard(idx)">
          <div class="thumbnail" @click="isThumbnail(idx)">썸네일</div>
          <div class="image-box">
            <img class="image" :src="data.img">
          </div>
          <div class="content-box">
            {{data.des}}
          </div>
        </div>
        <div class="addcard" id="add" @click="tempimage">
          +
        </div>
      </div>

      <div class="black-box" v-if="showcard" @click="showcard=!showcard"></div>
      <div v-if="showcard" class="inbox">
        <label for="file">
          <img :src=" tempImg|| defaultImage">
        </label>
        <input type="file" accept="image/*" id="file" @change="uploadImg">
        <textarea v-model="tempDes"></textarea>
        <button class="submit" @click="addimage">등록</button>
      </div>

      <div id="image-des">
        <div class="hash-input-group">
          <h2>TAGs</h2>
          <div class="hash-input">
            <input type="text" v-model="temphash" @keyup.enter="createHash" placeholder="태그를 입력하세요">
            <button @click="createHash"><i class="fas fa-pen nav-icon"></i></button>
          </div>
        </div>
        <div v-for="(h, idx) in HashList" :key="idx">
          <span class="hash"># {{h.content}}</span>
        </div>
      </div>
      <div id="image-des">
        <h2>Challenges</h2>
      </div>
      <button class="submit" @click="check"><h1><i class="fas fa-pen nav-icon"></i></h1></button>
    </div>
  </div>

</template>

<script>
import { mapState } from 'vuex'
import UserApi from '../../api/UserApi';
import FirebaseApi from '../../api/FirebaseApi';
var frm = new FormData();

export default {
  data: () => {
    return {
      title: "",
      fields: [],
      count: 0,
      thumbnailNumber:0,
      ThumbNailList: [],
      HashList:[],
      temphash:'',
      showcard:false,
      tempImg:'',
      tempDes:'',
      defaultImage:"https://t1.daumcdn.net/cfile/tistory/24611E4853FDAE0B14"
    }
  },
  methods: {
    tempimage(){
      this.showcard = !this.showcard
    },
    check(){
      var frm = new FormData();
      let l = this.fields.length
      for (let i=0;i<this.fields.length; i++){
        if( this.thumbnailNumber == i){
          this.ThumbNailList.push('true')
        }
        else{
          this.ThumbNailList.push('false')
        }
      }

      frm.append("title", this.title)
      frm.append("id", this.userId)
      frm.append('nickname',this.userNickname)
      for (let i=0; i< l; i++){
        frm.append("des["+i+"]",this.fields[i].des)
        frm.append("img["+i+"]",this.fields[i].img)
        frm.append("thumbnail["+i+"]",this.ThumbNailList[i])
      }
      for (let i=0; i<this.HashList.length; i++){
        frm.append("hashtags["+i+"]",this.HashList[i].content)
      }
      console.log(frm)

      UserApi.createRecipe(
        frm,
        res => {
          console.log('성공')
          this.$router.push({ name: 'FeedMain'})
          // this.$store.dispatch('clearFormdata')
        },
        error=> {
          console.log(error)
          frm = new FormData()
        }
      )
    },
    uploadImg(e){
      let file = e.target.files[0];
      FirebaseApi.upLoad(
        file,
        res=>{
          this.tempImg = res
          frm.append("file",res);
          this.$store.dispatch('uploadImg', {file: res, idx: this.idx});
        })
    },
    addimage(){
      this.fields.push({
        idx : this.count++,
        img : this.tempImg,
        des : this.tempDes
      }) 
      this.tempDes = ''
      this.tempImg = ''
      this.showcard = !this.showcard
    },
    createHash(){
      this.HashList.push({
        content : this.temphash
      })
      this.temphash = ''
    },
    updateCard(idx){
      // this.fields[idx].des = ''
    },
    isThumbnail(idx){
      this.thumbnailNumber = idx
    }
  },
  computed: {
    ...mapState([
      'userId',
      'img',
      'userNickname'
    ]),
  }
}
</script>
<style scoped>
*{
  font-family: "Roboto", sans-serif;
}

.write{
  margin: 0%;
  padding: 0%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
.write-form{
  width: 100%;
  max-width: 616px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
#title{
  width: 98%;
  display: inline;
  margin-left: 1%;
  margin-right: 1%;
  margin-bottom: 20px;
}
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
.write-card{
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 31%;
  height: 40vw;
  max-height: 250px;
  margin: 1%;
  border: 1px solid #FF9636;
  border-radius: 5px;

}
.thumbnail{
  display: flex;
  justify-content: center;
}
.image-box{
  width: 90%;
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
.addcard{
  display: flex;
  margin: 5px;
  justify-content: center;
  cursor: pointer;
  font-size: 50px;
  align-items: center;
  width: 31%;
  height: 40vw;
  max-height: 250px;
  border: 1px solid #191919;
  border-radius: 5px;

}
.black-box{
  position: fixed;
  top: 0%;
  left: 0%;
  height: 100vh;
  width: 100vw;
  background-color: rgba(155, 155, 155, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 2;
}
.inbox{
  position: fixed;
  top: 50%;
  left: 50%;
  width: 90vw;
  max-width: 616px;
  transform: translate(-50%,-50%);
  display: flex;
  padding: 10px 0px;
  background-color: #fff;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  z-index: 3;
}
.inbox label{
  max-width: 95%;
  max-height: 50vh;
  display: flex;
  width: 500px;
  height: 500px;
  justify-content: center;
}
.inbox img{
  background-color: #191919;
  min-width: 100%;
  min-height: 100%;
  margin-bottom: 20px;
  object-fit: contain;
}
.inbox input{
  display: none;
}
.inbox textarea{
  width: 95%;
  margin: 20px 0px;
  background-color: rgba(165, 175, 182, 0.5);
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
.submit{
  width: 95%;
  display: inline;
  background-color: #DAD870;
  border-radius: 3px;
  margin-bottom: 5%;
}
</style>
