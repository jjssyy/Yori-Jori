<template>
  <div class="newsfeed">
    <div class='feed' v-for="(latestFeed, idx) in latestFeeds" :key="idx">
      <FeedItem
        :latestFeed="latestFeed" 
        :idx="idx" 
        />
    </div>
  </div>
</template>

<script>
import FeedApi from '../../api/FeedApi';
import UserApi from '../../api/UserApi';
import { mapState } from "vuex";
import FeedItem from "../../components/feed/FeedItem.vue";

export default {
  props: ["keyword"],
  data:() => {
    return {
      latestFeeds: [],
    }
  },
  components: { 
    FeedItem,
   },
  created: function() {
    let data = {
      id: this.userId
    }
    FeedApi.likeposts(
      data,
      res => {
        console.log(res.data)
        this.latestFeeds = res.data.latestPosts
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
<style scoped>
.newsfeed{
  display: flex;
  align-items: center;
  flex-direction: column;
}
.feed{
  width: 100%;
  display: flex;
  justify-content: center;
}
</style>
