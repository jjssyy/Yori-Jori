<template>
  <div class="notification">
    <a href="#" id="search-show" @click="searchShow">
      <i class="fa fa-2x fa-search"></i>
    </a>
    <a v-on:click="show = !show" class="tooltip-bell">
      <i class="far fa-2x fa-bell"></i>
      <span id="circle" v-if="notice > 0 && !show"></span>
    </a>
    <!-- <button @click="noticeAdd">추가</button> -->
    <div v-if="show" class="Tooltip">
      <div id="heading">
        <div class="heading-left">
          <h5 class="heading-title">알림</h5>
        </div>
        <div class="heading-right">
          <a class="notification-link" href="#" @click="deleteAll">전체 삭제</a>
        </div>
      </div>
      <ul class="notification-list">
        <li class="notification-item" v-for="(user,idx) of unreadnotice.slice().reverse()" :key="idx">
          <div class="img-left">
            <img class="user-photo" alt="User Photo" v-bind:src="defaultProfile" />
          </div>
          <div class="user-content">
            <p class="user-info"><span class="name">{{user.ReqUser}}</span>님이 댓글을 달았습니다.</p>
            <p class="time">{{user.date | timeFor}}</p>
          </div>
        </li>
      </ul>
    </div>
    <div id="search">
      <div class="search-top">
        <a href="#" id="search-show" @click="searchShow">
          <i class="fa fa-2x fa-search"></i>
        </a>
        <input type="text">
      </div>
      <div class="search-bottom"></div>
    </div>
  </div>
</template>



<script>
import firebase from 'firebase';
import FirebaseApi from '../../api/FirebaseApi';
import defaultProfile from "../../assets/images/profile_default.png";

export default {
  data:()=>{
    return {
      notice:0,
      unreadnotice:[],
      defaultProfile,
      show: false,
      isShow:false
    }
  },
  mounted(){
    this.onNotice(),
    this.onRequest()
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
        ReqUser:'cha'
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
              return v.data()

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
    },
    deleteAll(event){
      event.preventDefault()
      const db = firebase.firestore();
      db.collection("notice"+this.$store.state.userId)
      .get()
      .then(res => {
        res.forEach(element => {
          element.ref.delete();
        });
      });
    },
    searchShow(event){
      event.preventDefault()
      console.log('ee')
      const Search = document.querySelector('#search')
      if (this.isShow==false){
        Search.classList.add('active')
        this.isShow = true
      } else {
        Search.classList.remove('active')
        this.isShow = false
      }
    }
  },
  filters : {
    timeFor : function(created_at){
			const today = new Date();
			const timeValue = new Date(created_at);

			const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
			if (betweenTime < 1) return '방금전';
			if (betweenTime < 60) {
					return `${betweenTime}분전`;
			}

			const betweenTimeHour = Math.floor(betweenTime / 60);
			if (betweenTimeHour < 24) {
					return `${betweenTimeHour}시간전`;
			}

			const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
			if (betweenTimeDay < 365) {
					return `${betweenTimeDay}일전`;
			}

			return `${Math.floor(betweenTimeDay / 365)}년전`;
		}
  }
}
</script>

<style scoped>

h2 {
  font-size: 1.6rem;
  font-weight: 400;
  line-height: 2rem;
  color: #1f2022;
}

ul {
  list-style-type: none;
  padding: 0.5rem 1rem;
  margin: 0 0 0.5rem;
}

li {
  display: inline-block;
  padding: 0.85rem 1rem;
  color: #1f2022;
}

h6 {
  font-size: 1rem;
  font-weight: 500;
  line-height: 1.1;
  margin: 0.45rem 0;
}

a {
  color: #4aaee7;
  background: transparent;
  outline: 0;
  text-decoration: none;
  cursor: pointer;
}


.tooltip-bell {
  display: block;
  z-index: 200;
}

.Tooltip {
  position: absolute;
  top: 80px;
  right: 10px;
  line-height: 1.5;
  color: #27303d;
  width: 20rem;
  background: #fff;
  border-radius: 5px;
  -webkit-box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.1);
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.1);
}


#circle {
  position: absolute;
  top: 0;
  left: 1rem;
  width: 0.75rem;
  height: 0.75rem;
  border-radius: 100%;
  background: #FF9636;
}

.notification-list{
  max-height: 500px;
  overflow: scroll;
  overflow-x: hidden;
}
.notification-item {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-align: start;
  -ms-flex-align: start;
  align-items: flex-start;
  display: -ms-grid;
  display: grid;
  padding: 0.65rem 0;
}

#heading {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-align: start;
  -ms-flex-align: start;
  align-items: flex-start;
  display: -ms-grid;
  display: grid;
  padding: 0.5rem 1rem;
  border-bottom: 0.01rem solid #eee;
}

.notification-link {
  position: absolute;
  margin: 0.4rem 0;
}

.heading-left, .img-left {
  -ms-flex-preferred-size: auto;
  flex-basis: auto;
  -webkit-box-flex: 0;
  -ms-flex-positive: 0;
  flex-grow: 0;
  -ms-flex-negative: 0;
  flex-shrink: 0;
  -ms-grid-row: 1;
  grid-row: 1;
  margin: 0 1rem 0 0;
}

.heading-left, .user-content {
  grid-column: span 9;
  width: 14rem;
}

.heading-right, .img-left {
  grid-column: auto;
}

.heading-right, .user-content {
  -ms-flex-preferred-size: auto;
  flex-basis: auto;
  -webkit-box-flex: 1;
  -ms-flex-positive: 1;
  flex-grow: 1;
  -ms-flex-negative: 1;
  flex-shrink: 1;
  -ms-grid-row: 1;
  grid-row: 1;
}

.heading-right {
  width: 3rem;
}

.img-left {
  width: 3rem;
}

.user-photo {
  display: inline-block;
  vertical-align: middle;
  height: 3rem;
  width: 3rem;
  margin: 0 0.5rem 0 0;
  border-radius: 50%;
  max-width: 100%;
}

p .user-info {
    margin: 0.15rem 0 0;
}
p .time {
  margin: 0;
  color: #9da4ae;
}

span.name {
  font-weight: 500;
}

#search{
  color: #464646;
  background-color: #fff;
  width: 100vw;
  height: 100vh;
  position: fixed;
  display: flex;
  top: 0%;
  right: -100%;
  transition: 500ms;
  z-index: 10000;
}
#search.active{
  right: 0%;
  transition: 500ms;
}

@media only screen and (min-width: 650px){

  #search-show {
    display: none;
  }
}

</style>