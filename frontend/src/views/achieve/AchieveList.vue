<template>
  <div class="feed newsfeed">
    <div class="wrapB">
      <div style="margin-top:50px;">
        <h1>업적</h1>
      </div>
      <b-card title="전체 칭호 달성도">
        
        <b-progress :max="totalCount" height="4rem" style="margin:30px 10px">
          <b-progress-bar class="bar-color" :value="userCount">
            <span
              >달성도:
              <strong style="margin-left:3px;"
                >{{ Math.floor((userCount / totalCount) * 100) }}%</strong
              ></span
            >
          </b-progress-bar>
        </b-progress>
      </b-card>
      <b-card title="세부 칭호 달성도">
        <div class="submenu" v-for="(master, idx) in achieveTitle" :key="idx">
          <div class="line">
            <div class="box">
              <h4>{{ master.title }}</h4>
            </div>
            <div class="font" @click="showDetail(master.title, idx)">
              <i class="fas fa-clipboard-list"></i>
            </div>
          </div>
          <b-progress :max="master.achieve_cnt" height="3rem" style="margin:10px">
            <b-progress-bar class="bar-color" :value="master.clear_cnt">
              <span>
                달성도:
                <strong>{{ master.percent }}%</strong>
              </span>
            </b-progress-bar>
          </b-progress>
        </div>
      </b-card>
      {{ totalCount }}
      {{ achieveTitle }}
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
      achieveTitle: [],
      totalCount: 0,
      userCount: 0,
    };
  },
  components: {},
  methods: {
    showDetail(title, idx) {
      this.$router.push({ name: 'AchieveDetail', query: { title: title, idx: idx } });
    },
  },
  created: function() {
    let data = {
      id: this.userId,
    };

    AchieveApi.achieveTitle(
      data,
      (res) => {
        console.log(res);
        for (let i = 0; i < res.data.list.length; i++) {
          this.totalCount += res.data.list[i].achieve_cnt;
          this.userCount += res.data.list[i].clear_cnt;
        }
        this.achieveTitle = res.data.list;
        
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
