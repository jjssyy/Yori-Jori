<template>
  <div class="feed newsfeed">
    <div class="wrapB">
      <div style="margin-top:50px;">
        <h1>업적</h1>
      </div>
        <div v-for="(master, idx) in achievemaster" :key="idx">
          <span @click="showslave">{{master.achieve_master_name}}</span>
          <div v-show="show" v-for="(slave, idx) in achieveslave" :key="idx">
              <span v-if="slave.achieve_master_name == master.achieve_master_name">{{slave.achieve_slave_name}}</span>
          </div>
        </div>
    </div>
  </div>
</template>

<script>
import UserApi from '../../api/UserApi';
import AchieveApi from '../../api/AchieveApi';
import { mapState } from "vuex";
// import "../../components/css/feed/feed-item.scss";
// import "../../components/css/feed/newsfeed.scss";
// import FeedItem from "../../components/feed/FeedItem.vue";

export default {
  props: ["keyword"],
  data:() => {
    return {
      achievemaster:[],
      achieveslave: [],
      show: false,
    }
  },
  components: { 
 
  },
  methods: {
    showslave(){
      this.show = !this.show;
    }
  },
  created: function() {
    let data = {
      id: this.userId
    }
    console.log(this.userId);
    AchieveApi.AchievemasterList(
      data,
      res => {
        
        this.achievemaster = res.data.masterlist,
        this.achieveslave = res.data.slavelist
        
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

