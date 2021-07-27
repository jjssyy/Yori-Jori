export default {
  LOGIN: function(state, res){
    const userId = res.config.params.id
    console.log(res.data['access-token'])
    const token = res.data['access-token']
    state.token = token
    state.userId = userId
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
  }
}