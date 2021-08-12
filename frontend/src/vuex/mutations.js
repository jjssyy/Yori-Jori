
import UserApi from '../api/UserApi';
export default {
  LOGIN: function (state, res) {
    const userId = res.data['id']
    const token = res.data['access-token']
    state.token = token
    state.userId = userId
    let data = {
      id: userId,
    }
    UserApi.getUser(token,
      data,
      userRes => {
        console.log(userRes)
        state.userNickname = userRes.data.nickname
      },
      error=>{
        console.log(error)
      }
    )
  },
  LOGOUT: function(state){
    state.token = ''
    state.userId = ''
  },
  WRITERECIPEITEM: function(state, res){
    state.recipeItems[res.idx] = res.des
  },
  UPLOADIMG: function(state, res){
    state.img[res.idx] = res.file
  },
  CHOICETHUMBNAIL(state, res){
    state.thumbnailNumber = res
  },
  CLEARFORMDATA(state){
    state.img = []
    state.recipeItems = []
    state.thumnailNumbers = 0 
    state.deleteContents = []  
  },
  SELECTRECIPE(state,res){
    state.selectRecipeId = res
  },
  DELETECONTENT(state,res){
    state.deleteContents.push(res)
  }
}