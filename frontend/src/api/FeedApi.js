import http from "@/util/http-common";
import { mapState } from 'vuex'



const likeposts = (config,data, callback, errorCallback) => {
  console.log(this.$store.state)
  console.log(config)
  http.get('/feed/likedposts', {
      headers: {
          'access-token':config
      },
      params:{
          id : data.id
      }}
  ).then(
      res=>callback(res)
  ).catch(
      err=>errorCallback(err)
  )
}

const FeedApi = {
  likeposts: (config,data, callback, errorCallback) => likeposts(config,data, callback, errorCallback),
}

export default FeedApi