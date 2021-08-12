<template>
  <div class="feed newsfeed">
    <div class="wrapB">
      <h1>인기글</h1>
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
import FeedApi from '../../api/FeedApi';
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
      page : 1
    }
  },
  components: { 
    // FeedItem,
    LatestFeed,
  }, 
  methods: {
    getPost(){
      let data = {
        id: this.userId,
        page: this.page
      }
      FeedApi.popularposts(
        data,
        res => {
          this.latestFeeds = res.data.popularPosts
        },
        error => {
          console.log(error)
        }
      )
    }
  },
  created: function() {
    this.getPost()
  },
  computed: {
    ...mapState([
      'userId',
    ]),
  }
}
</script>

