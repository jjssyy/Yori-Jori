<template>
  <div class="feed newsfeed">
    <div class="wrapB">
      
      <router-link :to="{name:'Profile', params: {user_id: userId}}">My Page</router-link>
      <router-link :to="{name:'Recipewrite', params: {user_id: userId}}">글쓰기</router-link>
      <router-link :to="{name:'Allmember', params: {user_id: userId}}">전체 유저 목록</router-link>
      <h1>뉴스피드</h1>

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

