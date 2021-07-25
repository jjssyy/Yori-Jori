<template>
  <div class="navigation-icons">
    <a class="navigation-link notifica">
      <i class="far fa-heart">
        <div class="notification-bubble-wrapper">
          <div class="notification-bubble">
            <span class="notifications-count">{{notice+request}}</span>
          </div>
        </div>
      </i>
    </a>
  </div>
</template>

<script>
import firebase from 'firebase';
import FirebaseApi from '../../api/FirebaseApi';

export default {
  name:'Notification',
  data:()=>{
    return {
      isClick:0,
      noticeORrequest:1,
      notice:0,
      request:0,
      readnotice:[],
      unreadnotice:[],
      readrequest:[],
      unreadrequest:[]
    }
  },
  watch :{
    getUserId(){
      this.onNotice(),
      this.onRequest()
    }
  },
  computed:{
    getUserId(){
      return this.$store.state.userId
    }
  },
  methods:{
    noticeAdd(){
      let data = {
        user:this.$store.state.userId,
        ReqUser:'unKnown'
      }
      FirebaseApi.noticeAdd(data)
    },
    requestAdd(){
      let data = {
        user:this.$store.state.userId,
        permitUser:'unKnown'
      }
      FirebaseApi.requestAdd(data)
    },
    onNotice(){
      const db = firebase.firestore();
      db.collection("notice"+this.$store.state.userId)
        .onSnapshot((doc) => {
          this.unreadnotice = doc.docs.map(v=>{
            if (v.data().isRead == 0){
              return {
                requser:v.data().ReqUser
              }
            }
          })
          this.notice = this.unreadnotice.length
      });
    },
    onRequest(){
      const db = firebase.firestore();
      db.collection("request"+this.$store.state.userId)
        .onSnapshot((doc) => {
          this.unreadrequest = doc.docs.map(v=>{
            return {
              requser:v.data().ReqUser
            }
          })
          this.request = this.unreadrequest.length
      });
    }
  }
}
</script>
