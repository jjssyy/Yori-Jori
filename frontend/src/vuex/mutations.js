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
}