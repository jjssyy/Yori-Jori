<template>
  <div class="feed newsfeed">
    <div class="wrapB">
      <div style="margin-top:50px;">
        <h1>{{ master_name }} 종류</h1>
        <b-progress :max="achieveCount[idx]" height="3rem" style="margin:10px">
          <b-progress-bar class="bar-color" :value="count[idx]">
            <span
              >{{ master_name }} 달성도:
              <strong>{{ count[idx] }} / {{ achieveCount[idx] }}</strong></span
            >
          </b-progress-bar>
        </b-progress>
      </div>
      <b-list-group>
        <div v-for="(slave, idx) in achieveslave" :key="idx">
          <div v-show="false">
            {{ (show = true) }}
          </div>
          <div v-for="(userrecipe, id) in userrecipe" :key="id">
            <b-list-group-item
              v-if="
                slave.achieve_master_name == master_name &&
                  slave.achieve_slave_name == userrecipe.achieve_slave
              "
              class="menu-1"
            >
              <h5 class="submenu">{{ slave.achieve_slave_name }}</h5>
              <span>
                <i class="fas fa-utensils"></i>
              </span>
              <div v-show="false">
                {{ (show = false) }}
              </div>
            </b-list-group-item>
          </div>
          <b-list-group-item
            class="menu-2"
            v-show="show"
            v-if="slave.achieve_master_name == master_name"
          >
            <h5 class="submenu">{{ slave.achieve_slave_name }}</h5>
          </b-list-group-item>
        </div>
        <!-- <div v-for="(slave, idx) in achieveslave" :key="idx">
          <b-list-group-item class="menu" v-if="slave.achieve_master_name == master_name">
            <h5 class="submenu">{{ slave.achieve_slave_name }}</h5>
            <span v-for="(userrecipe, id) in userrecipe" :key="id">
              <span v-if="slave.achieve_slave_name == userrecipe.achieve_slave"
                ><i class="fas fa-utensils"></i
              ></span>
            </span>
          </b-list-group-item>
        </div> -->
      </b-list-group>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import AchieveApi from '../../api/AchieveApi';
import UserApi from '../../api/UserApi';
export default {
  data: () => {
    return {
      achieveslave: [],
      userrecipe: [],
      achieveCount: [],
      count: [],
      show: false,
    };
  },
  created: function() {
    let data = {
      id: this.userId,
    };
    this.master_name = this.$route.query.master_name;
    this.idx = this.$route.query.idx;
    AchieveApi.AchievemasterList(
      data,
      (res) => {
        this.achieveslave = res.data.slavelist;
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
    AchieveApi.AchievemasterCount(
      data,
      (res) => {
        this.achieveCount = res.data.masterCount;
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
.menu-1 {
  display: flex;
  flex-direction: row;
  background-color: #ffbe76;
  margin: 3%;
  padding: 10px;
  min-height: 2rem;
  border: none;
  opacity: 0.9;
}
.menu-2 {
  display: flex;
  flex-direction: row;
  background-color: #b2bec3;
  margin: 3%;
  padding: 10px;
  min-height: 2rem;
  border: none;
  opacity: 0.9;
}
.submenu {
  margin: 10px;
  min-width: 87%;
}
.fa-utensils {
  width: 100%;
  height: 35px;
  margin-top: 3px;
}
.list-group-item:last-child {
  border-radius: 5px;
}
</style>