import http from "@/util/http-common";
import { mapState } from 'vuex'



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
  likeposts: (data, callback, errorCallback) => likeposts(data, callback, errorCallback),
}

export default FeedApi