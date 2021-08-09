<template>
  <div class="feed newsfeed">
    <div class="wrapB">
      <div style="margin-top:50px;">
        <h1>업적</h1>
      </div>

      <h3>전체 항목 달성도</h3>
      <b-progress :max="achieveslave.length" height="4rem" style="margin:30px 10px">
        <b-progress-bar class="bar-color" :value="userrecipe.length">
          <span
            >전체 달성도: <strong>{{ userrecipe.length }} / {{ achieveslave.length }}</strong></span
          >
        </b-progress-bar>
      </b-progress>
      <div style="margin-top:50px;"></div>
      <h3>항목 달성도</h3>
      <div class="submenu" v-for="(master, idx) in achievemaster" :key="idx">
        <div class="line">
          <div class="box">
            <h4>{{ master.achieve_master_name }}</h4>
          </div>
          <div class="font" @click="showDetail(master.achieve_master_name, idx)">
            <i class="fas fa-clipboard-list"></i>
          </div>
        </div>
        <b-progress :max="achieveCount[idx]" height="3rem" style="margin:10px">
          <b-progress-bar class="bar-color" :value="count[idx]">
            <span
              >{{ master.achieve_master_name }} 종류 달성도:
              <strong>{{ count[idx] }} / {{ achieveCount[idx] }}</strong></span
            >
          </b-progress-bar>
        </b-progress>
      </div>
    </div>
  </div>
</template>

<script>
import UserApi from '../../api/UserApi';
import AchieveApi from '../../api/AchieveApi';
import { mapState } from 'vuex';

// import "../../components/css/feed/feed-item.scss";
// import "../../components/css/feed/newsfeed.scss";
// import FeedItem from "../../components/feed/FeedItem.vue";

export default {
  props: ['keyword'],
  data: () => {
    return {
      achievemaster: [],
      achieveslave: [],
      userrecipe: [],
      achieveCount: [],
      count: [],
    };
  },
  components: {},
  methods: {
    showDetail(master, idx) {
      this.$router.push({ name: 'AchieveDetail', params: { master_name: master, idx: idx } });
    },
  },
  created: function() {
    let data = {
      id: this.userId,
    };
    AchieveApi.AchievemasterList(
      data,
      (res) => {
        (this.achievemaster = res.data.masterlist), (this.achieveslave = res.data.slavelist);
      },
      (error) => {
        console.log(error);
      }
    );
    AchieveApi.AchievemasterCount(
      data,
      (res) => {
        this.achieveCount = res.data.masterCount;
      },
      (error) => {
        console.log(error);
      }
    );
    UserApi.myAllRecipes(
      data,
      (res) => {
        this.userrecipe = res.data.latestFeed;
      },
      (error) => {
        console.log(error);
      }
    );
    UserApi.myMasterCount(
      data,
      (res) => {
        this.count = res.data.latestFeed;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  computed: {
    ...mapState(['userId']),
  },
};
</script>

<style scoped>
.bar-color {
  background-color: #ffbe76;
  color: black;
  font-size: 1.1rem;
}
.line {
  display: flex;
  flex-direction: row;
}
.font {
  font-size: 20px;
  text-align: right;
}
.box {
  min-width: 89%;
}
.fa-clipboard-list {
  width: 100%;
  height: 35px;
  color: #ffbe76;
  margin-left: 10px;
}
.submenu {
  margin-top: 40px;
}
</style>