<template>
  <div class="newsfeed"
  v-infinite-scroll="loadMore" 
  infinite-scroll-disabled="busy" 
  infinite-scroll-distance="10"
  >
    <h1>{{hashtag}}</h1>
    <div 
    class='feed' 
    v-for="(latestFeed, idx) in latestFeeds" 
    :key="idx"
    >
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
      page:1,
      busy:false,
      hashtage:null,
    }
  },
  components: { 
    FeedItem,
  },
  methods: {
 
  },
  created() {
    this.hashtag = this.$route.query.hashtag.substr(1,this.$route.query.hashtag.length-1);
    alert(this.hashtag);
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
