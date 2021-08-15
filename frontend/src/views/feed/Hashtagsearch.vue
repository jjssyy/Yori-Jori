<template>
  <div class="newsfeed">
    <h1>#{{hashtag}}</h1>
    <div class='feed' v-for="(hashtagfeeds, idx) in hashtagfeed" :key="idx">
        <FeedItem :latestFeed="hashtagfeeds" :idx="idx"/>
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
      hashtagfeed: [],
      page:1,
      busy:false,
      hashtag:null,
    }
  },
  components: { 
    FeedItem,
  },
  methods: {
 
  },
  created() {
    this.hashtag = this.$route.query.hashtag.substr(1,this.$route.query.hashtag.length-1);
    
      let data ={
        hashtag: this.hashtag,
      }
      FeedApi.hashtagsearch(
      
      data,
      res => {
        
        this.hashtagfeed = res.data.hashtagfeed
        console.log(this.hashtagfeed)
      },
      error=>{
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
