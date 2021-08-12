import http from "@/util/http-common";
import store from '@/vuex/store.js'
import { mapState } from 'vuex'



// const likeposts = (config,data, callback, errorCallback) => {
//   console.log(this.$store.state)
//   console.log(config)
//   http.get('/feed/likedposts', {
//       headers: {
//           'access-token':config
//       },
//       params:{
//           id : data.id
const likeposts = (data, callback, errorCallback) => {
  http.get('/feed/likedposts', {
      params:{
          user_id : data.id
      }}, {
        headers: {
          'access-token': store.state.token
        }
      }
  ).then(
      res=>callback(res)
  ).catch(
      err=>errorCallback(err)
  )
}
const popularposts = (data, callback, errorCallback) => {
  http.get('/feed/popularposts', {
      params:{
          user_id : data.id
      }}
  ).then(
      res=>callback(res)
  ).catch(
      err=>errorCallback(err)
  )
}

const FeedApi = {
  likeposts: (data, callback, errorCallback) => likeposts(data, callback, errorCallback),
  popularposts: (data, callback, errorCallback) => popularposts(data, callback, errorCallback),
}

export default FeedApi