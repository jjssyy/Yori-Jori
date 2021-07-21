/*
 User API 예시
 */

import http from "@/util/http-common";
import { mapState } from 'vuex'
import { mapActions } from 'vuex'


const requestLogin = (data,callback,errorCallback) => {
    //백앤드와 로그인 통신하는 부
    http.get("/user/login",{
        params:{
            id:data.id,
            pw:data.pw
        }
    })
    .then(res=>callback(res)) 
    .catch(err=>errorCallback(err));
}

const requestJoin = (data, callback, errorCallback) => {

    http.post("/user/join",data).then(
        res=>callback(res)
    ).catch(
        err=>errorCallback(err)
    );
}

const checkid = (data, callback, errorCallback)=>{
    http.get("/user/checkid", {
        params: {
            id: data.id
        }
    }).then(
        res => callback(res),
        
    ).catch(
        err=>errorCallback(err)
    );
}

const checknickname = (data, callback, errorCallback)=>{
    http.get("/user/checknickname", {
        params: {
            nickname: data.nickname
        }
    }).then(
        res => callback(res),
        
    ).catch(
        err=>errorCallback(err)
    );
}

const requestChangePassword = (data, callback, errorCallback)=>{
    http.post("/account/changepassword", data).then(
        res=>callback(res)
    ).catch(
        err=>errorCallback(err)
    );
}

const changepw = (data, callback, errorCallback) => {
    
    http.post("/user/updatepw",data).then(
        res=>callback(res)
    ).catch(
        err=>errorCallback(err)
    );
}

const requestUser = (config, callback, errorCallback)=>{
    http.get("/user/updateuser", config).then(
        res=>callback(res)
    ).catch(
        err=>errorCallback(err)
    );
}

const requestUpdate = (data, callback, errorCallback)=>{
    http.put("/user/updateuser", data).then(
        res=>callback(res)
    ).catch(
        err=>errorCallback(err)
    );
}

const getFollow = (data, callback, errorCallback)=>{
    http.get('/follow', data).then(
        res=>callback(res)
    ).catch(
        err=>errorCallback(err)
    )
}

const emailfindpw = (data, callback, errorCallback)=>{
    http.get("/user/findpw", {
        params: {
            id: data.id,
            email: data.email
        }
    }).then(
        res => callback(res),
        
    ).catch(
        err=>errorCallback(err)
    );
}

const getFollower= (data, callback, errorCallback)=>{
    http.get('/user/followerlist', data).then(
        res=>callback(res)
    ).catch(
        err=>errorCallback(err)
    )
}

const getFollowing = (data, callback, errorCallback)=>{
    http.get('/user/followinglist', data).then(
        res=>callback(res)
    ).catch(
        err=>errorCallback(err)
    )
}

const getUser = (data, callback, errorCallback)=>{
    http.get('/user/profile', {
        params:{
            id : data.id
        }
    }).then(
        res=>callback(res)
    ).catch(
        err=>errorCallback(err)
    )
}

const Follow = (data, callback, errorCallback) =>{
    http.post('/user/profile',{
        params:{
            loginId: data.loginId,
            profileId: data.profileId,
        }
    }).then(
        res=>callback(res)
    ).catch(
        err=>errorCallback(err)
    )
}

const UserApi = {
    requestLogin:(data,callback,errorCallback)=>requestLogin(data,callback,errorCallback),
    requestJoin:(data,callback,errorCallback)=>requestJoin(data,callback,errorCallback),
    requestChangePassword: (data, callback, errorCallback) => requestChangePassword(data, callback, errorCallback),
    checkid: (data, callback, errorCallback) => checkid(data, callback, errorCallback),
    checknickname: (data, callback, errorCallback) => checknickname(data, callback, errorCallback),
    changepw: (data, callback, errorCallback) => changepw(data, callback, errorCallback),
    requestUpdate: (data, callback, errorCallback) => requestUpdate(data, callback, errorCallback),
    requestUser: (data, callback, errorCallback) => requestUser(data, callback, errorCallback),
    getFollow: (data, callback, errorCallback) => getFollow(data, callback, errorCallback),
    emailfindpw: (data, callback, errorCallback) => emailfindpw(data, callback, errorCallback),
    getUser: (data, callback, errorCallback) => getUser(data, callback, errorCallback),
    getFollower: (data, callback, errorCallback) => getFollower(data, callback, errorCallback),
    getFollowing: (data, callback, errorCallback) => getFollowing(data, callback, errorCallback),
    Follow: (data, callback, errorCallback) => Follow(data, callback, errorCallback),
}

export default UserApi