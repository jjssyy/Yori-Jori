<template>
  <div class="feed newsfeed" style="margin-top:100px;">
    <div class="wrapB" >
      
      
      
      <div>
        
      </div>
      
      <FeedItem />
      <FeedItem />
      <FeedItem />
      <FeedItem />  
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import "../../components/css/feed/feed-item.scss";
import "../../components/css/feed/newsfeed.scss";
import FeedItem from "../../components/feed/FeedItem.vue";

export default {
  props: ["keyword"],

  components: { FeedItem },

  computed: {
    ...mapState([
      'userId',
    ]),
  }
};
</script>

<style>


</style>



