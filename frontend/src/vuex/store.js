import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

const state = {
    isUser: false,
    token:'',
}

export default new Vuex.Store({
    plugins:[
        createPersistedState
    ],
    state,
    mutations,
    getters,
    actions
})