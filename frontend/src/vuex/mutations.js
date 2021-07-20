export default {
  LOGIN: function(state, res){
<<<<<<< HEAD
=======
    const userId = res.config.params.id
>>>>>>> 98c8d36aef68802007aa364805540e8c06f67bd5
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