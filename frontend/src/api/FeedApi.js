import http from "@/util/http-common";
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
      }}
  ).then(
      res=>callback(res)
  ).catch(
      err=>errorCallback(err)
  )
}

const FeedApi = {
<<<<<<< HEAD
  likeposts: (config,data, callback, errorCallback) => likeposts(config,data, callback, errorCallback),
=======
  likeposts: (data, callback, errorCallback) => likeposts(data, callback, errorCallback),
>>>>>>> 15702f6434f6bdc63a4adb058e56d1ca4dd0d147
}

export default FeedApi