<template>
  <div class="feed newsfeed">
    <div class="wrapB">
      <h1>뉴스피드</h1>
        <router-link :to="{name:'Profile', params: {user_id: userId}}">My Page</router-link>
        <router-link :to="{name:'Recipewrite', params: {user_id: userId}}">글쓰기</router-link>
        <router-link :to="{name:'Allmember', params: {user_id: userId}}">전체 유저 목록</router-link>
        <latest-feed v-for="(latestFeed, idx) in latestFeeds" :latestFeed="latestFeed" :idx="idx" :key="idx">
        </latest-feed>
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
<<<<<<< HEAD
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
        console.log(res.data)
        this.latestFeeds = res.data.latestFeed
      },
      error => {
        console.log(error)
      }
    )
  },
=======

>>>>>>> dev
  computed: {
    ...mapState([
      'userId',
    ]),
<<<<<<< HEAD
  }
}
=======
  },

  components: { FeedItem }
};
>>>>>>> dev
</script>

