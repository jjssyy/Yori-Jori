<template>
  <div class="feed newsfeed">
    <div class="wrapB">
      <router-link to="/user/mypage">My Page</router-link>
        <h1>메인페이지</h1>

      <FeedItem />
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
// import "../../components/css/feed/feed-item.scss";
// import "../../components/css/feed/newsfeed.scss";
import FeedItem from "../../components/feed/FeedItem.vue";

export default {
  props: ["keyword"],

  components: { FeedItem }
};
</script>

