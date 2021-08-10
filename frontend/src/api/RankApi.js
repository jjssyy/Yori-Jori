import http from "@/util/http-common";
import { mapState } from 'vuex'
import { mapActions } from 'vuex'


const Myrank = (data,callback,errorCallback) => {
    
    http.get("/ranking/myrank",{
        params:{
            id: data.id,
        }
    })
    .then(res=>callback(res)) 
    .catch(err=>errorCallback(err));
}

const Updatemyrank = (data,callback,errorCallback) => {
    
    http.post("/ranking/updatemyrank",data)
    .then(res=>callback(res)) 
    .catch(err=>errorCallback(err));
}

const getRanking = (callback,errorCallback) => {
    
    http.get("/ranking/rankinglist")
    .then(res=>callback(res)) 
    .catch(err=>errorCallback(err));
}

const RankApi = {
    Myrank: (data, callback, errorCallback) => Myrank(data, callback, errorCallback),
    Updatemyrank: (data, callback, errorCallback) => Updatemyrank(data, callback, errorCallback),
    getRanking:(data,callback,errorCallback)=>getRanking(data,callback,errorCallback),
}
export default RankApi
