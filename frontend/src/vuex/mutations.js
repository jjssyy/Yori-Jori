export default {
  LOGIN: function(state, res){
    console.log(res.data['access-token'])
    const token = res.data['access-token']
    state.token = token
  },
  LOGOUT: function(state){
    state.token = ''
  },
}