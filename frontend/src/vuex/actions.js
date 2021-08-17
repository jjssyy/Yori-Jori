export default {
  login: function({commit}, res){
    commit('LOGIN',res)
  },
  logout: function({commit}, res){
    commit('LOGOUT',res)
  },
  writeRecipeItem({commit}, res){
    commit('WRITERECIPEITEM',res)
  },
  uploadImg({commit}, res){
    commit('UPLOADIMG',res)
  },
  choiceThumbnail({commit}, res){
    commit('CHOICETHUMBNAIL',res)
  },
  clearFormdata({commit}){
    commit('CLEARFORMDATA')
  },
  selectRecipe({commit}, res){
    commit('SELECTRECIPE',res)
  },
  deleteContent({commit},res){
    commit('DELETECONTENT',res)
  },
  countComment({commit},res){
    commit('COUNTCOMMENT',res)
  },
  getCommentCountList({commit},res){
    commit('GETCOMMENTCOUNTLIST',res)
  },
  getCommentCount({commit},res){
    commit('GETCOMMENTCOUNT',res)
  }
}
