export default {
  login: function({commit}, res){
    commit('LOGIN',res)
  },
  logout: function({commit}, res){
    commit('LOGOUT',res)
  },
}
