<template>
  <div class="feed newsfeed">
    <div class="wrapB">
      <h1>뉴스피드</h1>
        
        <router-link :to="{name:'Profile', params: {user_id: userId}}">My Page</router-link>
        <router-link :to="{name:'Recipewrite', params: {user_id: userId}}">글쓰기</router-link>
        <router-link :to="{name:'Achievelist', params:{user_id: userId}}">업적</router-link>
        <router-link :to="{name:'Rankinglist', params:{user_id: userId}}">랭킹</router-link>
        <button @click="logout">로그아웃</button>
        <div v-for="(latestFeed, idx) in latestFeeds" :key="idx">
        <LatestFeed
          :latestFeed="latestFeed" 
          :idx="idx"
          :latestFeeds="latestFeeds"
          />
      </div>
    </div>
  </div>
</template>

<script>
import UserApi from '../../api/UserApi';
import { mapState } from "vuex";
// import "../../components/css/feed/feed-item.scss";
// import "../../components/css/feed/newsfeed.scss";
// import FeedItem from "../../components/feed/FeedItem.vue";
import LatestFeed from "../../components/feed/LatestFeed.vue";

export default {
  props: ["keyword"],
  data:() => {
    return {
      latestFeeds: [],
    }
  },
  components: { 
    // FeedItem,
    LatestFeed,
   }, 
   methods: {
      logout(){
      alert('로그아웃 되었습니다.');
      this.$store.dispatch('logout');
      this.$router.push({name:'Login'});
    },},
  created: function() {
    let data = {
      id: this.userId
    }
    console.log(this.userId);
    UserApi.latestFeed(
      data,
      res => {
        console.log(res.data)
        this.latestFeeds = res.data.latestFeed
      },
      error => {
        console.log(error)
      }
    )
  },
  computed: {
    ...mapState([
      'userId',
    ]),
  }
}
</script>

