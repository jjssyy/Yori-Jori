<template>
  <div class="feed newsfeed">
    <div class="wrapB">
      <div style="margin-top:50px;">
        <h1>업적</h1>
      </div>
      <b-card title="전체 항목 달성도">
        <b-progress :max="totalCount" height="4rem" style="margin:30px 10px">
          <b-progress-bar class="bar-color" :value="userCount">
            <span
              >달성도:
              <strong style="margin-left:3px;">{{ (userCount / totalCount) * 100 }}%</strong></span
            >
          </b-progress-bar>
        </b-progress>
      </b-card>
      <b-card title="항목 달성도">
        <div class="submenu" v-for="(master, idx) in achievemaster" :key="idx">
          <div class="line">
            <div class="box">
              <h4>{{ master.achieve_master_name }}</h4>
            </div>
            <div class="font" @click="showDetail(master.achieve_master_name, idx)">
              <i class="fas fa-clipboard-list"></i>
            </div>
          </div>
          <b-progress :max="userRecipe[idx].achieve_cnt" height="3rem" style="margin:10px">
            <b-progress-bar class="bar-color" :value="userRecipe[idx].clear_cnt">
              <span>
                달성도:
                <strong>{{ userRecipe[idx].percent }}%</strong>
              </span>
            </b-progress-bar>
          </b-progress>
        </div>
      </b-card>
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
      userRecipe: [],
      totalCount: 0,
      userCount: 0,
    };
  },
  components: {},
  methods: {
    showDetail(master, idx) {
      this.$router.push({ name: 'AchieveDetail', query: { master_name: master, idx: idx } });
    },
  },
  created: function() {
    let data = {
      id: this.userId,
    };
    AchieveApi.AchievemasterList(
      data,
      (res) => {
        this.achievemaster = res.data.masterlist;
      },
      (error) => {
        console.log(error);
      }
    );

    AchieveApi.getAchievecurrent(
      data,
      (res) => {
        this.userRecipe = res.data.achieve;
        for (let i = 0; i < res.data.achieve.length; i++) {
          this.totalCount += res.data.achieve[i].achieve_cnt;
          this.userCount += res.data.achieve[i].clear_cnt;
        }
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
  min-width: 22%;
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
  min-width: 88%;
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
.card-title {
  font-size: 28px;
}
.card-body {
  padding: 30px;
}
.card {
  margin: 40px 0px;
}
.progress-bar bar-color {
  min-width: 80px;
}
</style>
