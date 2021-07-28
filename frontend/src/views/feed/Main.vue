<template>
  <div class="feed newsfeed">
    <div class="wrapB">
      <h1>뉴스피드</h1>
      <router-link :to="{name:'Allmember', params: {user_id: userId}}">전체 유저 목록</router-link>
      <div v-for="(latestFeed, idx) in latestFeeds" :key="idx">
        <LatestFeed
          :latestFeed="latestFeed" 
          :idx="idx" 
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
  created: function() {
    let data = {
      id: this.userId
    }
    UserApi.latestFeed(
      data,
      res => {
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

