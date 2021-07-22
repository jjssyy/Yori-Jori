<template>
  <a class="notification" @click="Add">
    <span>알림</span>
    <span class="badge" >{{notice}}</span>
    <span>{{request}}</span>
  </a>
</template>

<script>
import firebase from 'firebase';
import FirebaseApi from '../../api/FirebaseApi';

export default {
  data:()=>{
    return {
      notice:0,
      request:0,
      items:[]
    }
  },
  created(){
    this.onSnapshot()
  },
  methods:{
    Add(){
      let data = {
        user:this.$store.state.userId,
        ReqUser:'unKnown'
      }
      FirebaseApi.noticeAdd(data)
    },
    onSnapshot(){
      const db = firebase.firestore();
      db.collection("notice"+this.$store.state.userId)
        .onSnapshot((doc) => {
          this.notice = doc.docs.length
          this.items = doc.docs.map(v=>{
            return {
              requser:v.data().ReqUser
            }
          })
      });
    }
  }
}
</script>

<style scoped>
.notification {
  background-color: #555;
  color: white;
  text-decoration: none;
  padding: 15px 26px;
  position: relative;
  display: inline-block;
  border-radius: 2px;
}

.notification:hover {
  background: red;
}

.notification .badge {
  position: absolute;
  top: -10px;
  right: -10px;
  border-radius: 50%;
  background: red;
  color: white;
}
</style>