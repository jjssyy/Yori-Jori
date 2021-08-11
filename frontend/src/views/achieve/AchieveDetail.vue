<template>
  <div class="feed newsfeed">
    <div class="wrapB">
      <h1></h1>
      <h2>{{ master_name }} 종류</h2>
      <b-card>
        <div style="margin-top:50px;">
          <b-progress
            :max="userRecipeTitle[idx].achieve_cnt"
            height="4rem"
            style="margin:30px 10px"
          >
            <b-progress-bar class="bar-color" :value="userRecipeTitle[idx].clear_cnt">
              <span>
                {{ master_name }} 달성도:
                <strong>{{ userRecipeTitle[idx].percent }}%</strong>
              </span>
            </b-progress-bar>
          </b-progress>
        </div>
      </b-card>
      <b-card title="세부 항목">
        <b-list-group>
          <div v-for="(slave, idx) in achieveslave" :key="idx">
            <div v-for="(userrecipe, id) in userRecipeList" :key="id">
              <div v-show="false" v-if="slave.achieve_slave_name == userrecipe.achieve_slave">
                {{ (achieveslave[idx].show = true) }}
              </div>
            </div>
            <b-list-group-item class="menu-1" v-show="achieveslave[idx].show">
              <h5 class="submenu">{{ slave.achieve_slave_name }}</h5>
              <span>
                <i class="fas fa-utensils" />
              </span>
            </b-list-group-item>
            <b-list-group-item class="menu-2" v-show="!achieveslave[idx].show">
              <h5 class="submenu">{{ slave.achieve_slave_name }}</h5>
            </b-list-group-item>
          </div>
        </b-list-group>
      </b-card>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import AchieveApi from '../../api/AchieveApi';
export default {
  data: () => {
    return {
      userRecipeList: [],
      achieveslave: [],
      userRecipeTitle: [],
    };
  },
  created: function() {
    let data = {
      id: this.userId,
      master: this.$route.query.master_name,
    };
    this.master_name = this.$route.query.master_name;
    this.idx = this.$route.query.idx;
    //사용
    AchieveApi.getAchievecurrent(
      data,
      (res) => {
        this.userRecipeTitle = res.data.achieve;
      },
      (error) => {
        console.log(error);
      }
    );
    //사용
    AchieveApi.myRecipe(
      data,
      (res) => {
        this.userRecipeList = res.data.latestFeed;
      },
      (error) => {
        console.log(error);
      }
    );
    //사용
    AchieveApi.AchievemasterCategoryList(
      data,
      (res) => {
        this.achieveslave = res.data.slavelist;
        this.achieveslave = res.data.list;
        for (let i = 0; i < this.achieveslave.length; i++) {
          this.achieveslave[i].show = false;
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
  min-width: 25%;
}
.menu-1 {
  display: flex;
  flex-direction: row;
  background-color: #ffbe76;
  margin: 4%;
  padding: 10px;
  min-height: 2rem;
  border: none;
  border-radius: 5px;
}
.menu-2 {
  display: flex;
  flex-direction: row;
  background-color: #b2bec3;
  margin: 4%;
  padding: 10px;
  min-height: 2rem;
  border: none;
  border-radius: 5px;
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
.card-title {
  font-size: 28px;
}
.card-body {
  padding: 30px;
}
.card {
  margin: 40px 0px;
}
</style>
