export default {
  LOGIN: function(state, res){
    const token = res.data['access-token']
    state.token = token
  },
  LOGOUT: function(state){
    state.token = ''
    state.userId = ''
  },
}