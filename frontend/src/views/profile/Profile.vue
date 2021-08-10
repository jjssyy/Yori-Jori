<template>
  <div>
    <div class="wrapC" v-if="profileUser">
      
      <div class="row" id="profile_wrap">
        <div class="row" id="profile_main">
          <div class="col-sm-3" id="profile_left_box" >
            <div class="row" id="profile_nickname_box">
              <p>{{ profileUser.nickname }}</p>
            </div>
            <div class="row" id="profie_img_box">
              <div id="profile_img">이미지</div>
            </div>
          </div>
          <div class="col-sm-9" id="profile_right_box" v-if="profileUser.id != userId">
            <div class="row" >
              <div class="col-md-4" id="profile_recipe_box" >
                <div class="row">
                  <b>레시피</b>
                </div>
                <div class="row">
                    <p>{{profileUser.recipecnt}}</p>
                </div>
              </div>
              <div class="col-md-4" id="profile_follower_box">
                <div class="row">
                  <b>팔로워</b>
                </div>
                <div class="row">
                  <p>{{ profileUser.follower }}</p>
                </div>
                
              </div>
              <div class="col-md-4" id="profile_following_box">
                <div class="row">
                  <b>팔로잉</b>
                </div>
                <div class="row">
                  <p>{{ profileUser.following }}</p>
                </div>
              </div>
            </div>
            <div class="row" id="profile_rankpoint_box">
                <div class="col-md-4" id="profile_rankpoint_rank">
                <b>순위 :</b> <span>{{profileUser.rankpoint}}</span> 
              </div>
              <div class="col-md-5" id="profile_rankpoint_point">
                <b>RP :</b> <span>{{profileUser.rankpoint}}</span> 
              </div>
            </div>
            <div class="row" style="margin-top:20px;" v-if="profileUser.id != userId">
                  <button class="btn btn-secondary" v-if="follow_already.includes(profileUser.id) " @click="senddeletefollow(profileUser)">이미 등록됨</button>
                  <button class="btn btn-primary" v-if="!follow_already.includes(profileUser.id)  " @click="sendrequest(profileUser)">신청</button>
              </div>
          </div>
          <div class="col-md-9" id="profile_right_box" v-if="profileUser.id == userId">
            <div class="row">
              <div class="col-md-4" id="profile_recipe_box">
                <div class="row">
                  <b>레시피</b>
                </div>
                <div class="row">
                  <p>{{profileUser.recipecnt}}</p>
                </div>
              </div>
              <div class="col-md-4" id="profile_follower_box">
                <div class="row">
                  <b>팔로워</b>
                </div>
                <div class="row" @click="showFollowerList"><p>{{ profileUser.follower }}</p></div>
              </div>
              <div class="col-md-4" id="profile_following_box">
                <div class="row">
                  <b>팔로잉</b>
                </div>
                <div class="row" @click="showFollowingList"> <p>{{ profileUser.following }}</p></div>
              </div>
            </div>
            <div class="row" id="profile_rankpoint_box">
                <div class="col-md-4" id="profile_rankpoint_rank">
                <b>순위 :</b> <span>{{profileUser.rankpoint}}</span> 
              </div>
              <div class="col-md-4" id="profile_rankpoint_point">
                <b>RP :</b> <span>{{profileUser.rankpoint}}</span> 
              </div>
            
              <div class="col-md-2" id="profile_rankpoint_refresh">
                <button v-if="profileUser.rankpoint != rankpoint" class="btn btn-primary" @click="updaterank">갱신</button>
              </div>
            </div>
              <div class="row" id="profile_update_box">
                <div class="col-md-6" id="profile_update_user">
                  <router-link style="text-decoration:none; color:black;" to="/user/update" class="btn--text">회원정보</router-link>
              </div>
              <div class="col-md-6" id="profile_update_pw">
                  <router-link style="text-decoration:none; color:black;" to="/user/changepassword" class="btn--text">비밀번호</router-link>
              </div>
              </div>
          </div>
        </div>
      <div class="row" id="profile_desc_box">
        <div class="row" id="profile_desc_head">
          <p>소개</p>
        </div>
        <div class="row" id="profile_desc_body">
          <p>{{ profileUser.des }}</p>
        </div>
      </div>
      
      </div>
       <div v-if="profileUser.id == userId">
        <my-recipe-item v-for="(myRecipe, idx) in myRecipes" :key="idx" :myRecipe="myRecipe" :idx="idx">
        </my-recipe-item>
      </div>
      <div>
        <div class="wrap">
          <p>비밀번호를 변경하시겠습니까</p>
          <router-link to="/user/changepassword" class="btn--text">비밀번호 변경</router-link>
        </div>
        <div class="wrap">
          <p>회원정보 변경</p>
          <router-link to="/user/update" class="btn--text">변경하기</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import UserApi from '../../api/UserApi';
import RankApi from '../../api/RankApi';
import MyRecipeItem from '../../components/profile/MyRecipeItem.vue';

export default {
  components: { MyRecipeItem },
  data: () => {
    return {
      profileUser: null,
      profileId: null,
      follower: null,
      following: null,
      myRecipes: null,
      waiting: null,
      follow_wait:[],
      follow_already:[],
      rankpoint:null,
      achieve_cnt:null,
      follower_cnt:null,
      recipe_cnt:null,
      recipe_comment_cnt:null,
      recipe_comment_like_cnt:null,
      recipe_like_cnt:null
    }
  },
  created() {
    this.profileId = this.$route.params.user_id
    
    let data = {
      id: this.profileId
    }
    
<<<<<<< HEAD
     const config =  this.$store.state.token;
=======
    const config =  this.$store.state.token;
>>>>>>> feature/S05P13C205-51
    
    UserApi.getUser(config,
      data,
      res => {
        this.profileUser = res.data
         console.log(this.profileUser.recipecnt);
        this.follower = res.data.follower
        this.following = res.data.following
      },
      error=>{
        console.log(error)
      }
    )

    UserApi.myAllRecipes(
      data,
      res => {
        this.myRecipes = res.data.latestFeed
      },
      error=>{
        console.log(error)
      }
    )
    data = {
      id: this.userId
    }

    

    UserApi.follow_already(
      
      data,
      res => {
        this.follow_already = res.data;
      },
      error=>{
        console.log(error)
      }
    )

    data = {
      id: this.profileId
    }
    RankApi.Myrank(
      data,
      res => {
        this.recipe_cnt = res.data.myrank.recipe_cnt;
        this.achieve_cnt =  res.data.myrank.achieve_cnt;
        this.follower_cnt = res.data.myrank.follower_cnt;
        this.recipe_comment_cnt = res.data.myrank.recipe_comment_cnt;
        this.recipe_comment_like_cnt = res.data.myrank.recipe_comment_like_cnt;
        this.recipe_like_cnt = res.data.myrank.recipe_like_cnt;
        this.rankpoint =  this.recipe_cnt +  this.achieve_cnt + this.follower_cnt + this.recipe_comment_cnt + this.recipe_comment_like_cnt + this.recipe_like_cnt;


        },
      error=>{
        console.log(error)
      }
    )
    // if(this.rankpoint != this.profileUser["rankpoint"]){
    //   alert("갱신필요");
    // }

  },
  methods: {
    sendrequest(member){

      let data = {
        loginid : this.userId,
        memberid : member.id,
        token : this.$store.state.token,
        
      }
      UserApi.sendfollowrequest(
      data,
      res => {
        if(res.data == "success"){
          alert("팔로우 신청을 보냈습니다.");
            this.$router.go();
        }else if(res.data == "fail"){
          alert("팔로우 신청이 보내지지 않았습니다.")
        }else{
          alert("에러발생");
        }
      },
      error=>{
        alert("에러발생");
      }
    )
    
    },

    senddeletefollow(member){

      let data = {
        loginid : this.userId,
        memberid : member.id,
        token : this.$store.state.token,
        
      }
      UserApi.sendfollowdelete(
      data,
      res => {
        if(res.data == "success"){
          alert("팔로우를 취소했습니다..")
            this.$router.go();
        }else if(res.data == "fail"){
          alert("팔로우 취소신청이 보내지지 않았습니다.")
        }else{
          alert("에러발생");
        }
      },
      error=>{
        alert("에러발생");
      }
    )
    },

    updaterank(){
      let data = {
        id : this.userId,
        rankpoint : this.rankpoint
      }

      RankApi.Updatemyrank(
        data,
        res => {
          if(res.data == "success"){
            alert("갱신되었습니다.")
            this.$router.go();
          }else if(res.data == "fail"){
            alert("갱신에 실패하였습니다.")
          }else{
            alert("에러발생");
          }
        },
        error=>{
          alert("에러발생");
        }
      )
    },

    showFollowerList: function() {
      this.$router.push({ name: 'FollowerList' , params: {profileId: this.profileId}})
    },
    showFollowingList: function() {
      this.$router.push({ name: 'FollowingList' , params: {profileId: this.profileId}})
    },
  },
  computed: {
    ...mapState([
      'userId',
    ]),
  }
}
</script>

<style>
#profile_nickname_box{
  font-size: 25px;
  
}

#profile_img{
  background-color: gray;
  color: white;
  height: 100px;
  width: 100px;
  margin-left: 10px;
}

#profile_regdate_box p{
  margin-top: 10px;
}

#profile_recipe_box, #profile_follower_box, #profile_following_box{
  text-align: center;
}

#profile_recipe_box p, #profile_follower_box p , #profile_following_box p{
  color: #ffbe76;
}

#profile_right_box{
  margin-top: 60px;
}

#profile_rankpoint_box{
  margin-top: 10px;
}

#profile_rankpoint_rank{
  margin-left: 30px;
}

#profile_rankpoint_point span, #profile_rankpoint_rank span{
  color: #ffbe76;
  margin-left: 10px;
  
}
#profile_rankpoint_refresh button{
  height: 30px;
  line-height: 20px;
  
}

#profile_update_box{
  margin-top: 30px;
  padding-left: 20px;
  
}

#profile_update_user {
  text-decoration: none;
  border: 1px solid lightgray;
  text-align: center;
}

#profile_update_pw{
  text-decoration: none;
  border: 1px solid lightgray;
  text-align: center;
}

#profile_desc_box{
  margin-top: 20px;
}

#profile_desc_box_body{
  margin-top: 10px;
}




</style>
