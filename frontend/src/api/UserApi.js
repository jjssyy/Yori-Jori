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
     http.get('/user/profile/followerlist', {
         params:{
             id : data.id
         }
     }).then(
         res=>callback(res)
     ).catch(
         err=>errorCallback(err)
     )
 }
 
 const getWaiter = (data, callback, errorCallback)=>{
     http.get('/user/profile/waitlist', {
         params:{
             id : data.id
         }
     }).then(
         res=>callback(res)
     ).catch(
         err=>errorCallback(err)
     )
 }
 
 const getFollowing = (data, callback, errorCallback)=>{
     http.get('/user/profile/followinglist', {
         params:{
             id : data.id
         }
     }).then(
         res=>callback(res)
     ).catch(
         err=>errorCallback(err)
     )
 }
 
 const deleteFollower= (data, callback, errorCallback)=>{
     http.delete('/user/profile/followerlist', {
         params:{
             loginid : data.loginId,
             followerid: data.followerId
         }
     }).then(
         res=>callback(res)
     ).catch(
         err=>errorCallback(err)
     )
 }
 
 
 
 const deleteFollowing= (data, callback, errorCallback)=>{
     http.delete('/user/profile/followinglist', {
         params:{
             loginid : data.loginId,
             followingid: data.followingId,
         }
     }).then(
         res=>callback(res)
     ).catch(
         err=>errorCallback(err)
     )
 }
 
 
 const getUser = (config,data, callback, errorCallback) => {
    
    http.get('/user/profile', {
        
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
 const requestUser = (config, callback, errorCallback)=>{
     http.get("/user/updateuser", config).then(
         res=>callback(res)
     ).catch(
         err=>errorCallback(err)
     );
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
 
const fileupload = (data, callback, errorCallback) =>{
     http.post('/user/fileupload', data, {
         headers: {
             'Content-Type': 'multipart/form-data'
         }
     }).then(
         res=>callback(res)
     ).catch(
         err=>errorCallback(err)
     )
 }
 
 const createRecipe = (data, callback, errorCallback) =>{
     http.post('/feed/write', data, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
     }).then(
         res=>callback(res)
     ).catch(
         err=>errorCallback(err)
     )
 }

 const singleRecipe = (data, callback, errorCallback) => {
     http.get('/feed/content', {
         params: {
             recipe_idx : data.recipeIdx
         }
     }).then(
        res=>callback(res)
    ).catch(
        err=>errorCallback(err)
    )
 }

 const latestFeed = (data, callback, errorCallback) => {
    http.get('/feed/latestfeed', {
        params: {
            id: data.id
        }
    }).then(
       res=>callback(res)
   ).catch(
       err=>errorCallback(err)
   )
 }

const myAllRecipes = (data, callback, errorCallback) => {
    http.get('/feed/allrecipes', {
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

const getAllmember = (data, callback, errorCallback) =>{
    http.get('/user/getallmember', {
        params:{
            id : data.id
        }
    }).then(
        res=>callback(res)
    ).catch(
        err=>errorCallback(err)
    )
}

const sendfollowrequest = (data, callback, errorCallback) => {
    
    http.post('/user/sendfollowrequest', 
       data
    
     
    ).then(
        res=>callback(res)
    ).catch(
        err=>errorCallback(err)
    )
}

const sendfollowdelete = (data, callback, errorCallback) => {
    
    http.post('/user/sendfollowdelete', 
       data
    
     
    ).then(
        res=>callback(res)
    ).catch(
        err=>errorCallback(err)
    )
}

const follow_already = (data, callback, errorCallback) =>{
    http.get('/user/follow_already', {
        params:{
            id : data.id
        }} 
       
    ).then(
        res=>callback(res)
    ).catch(
        err=>errorCallback(err)
    )
}

const searchByNickname = (data, callback, errorCallback) =>{
    http.get('/search/nickname',{
        params:{
            nickname:data.nickname,
        }
    } 
    ).then(
        res=>callback(res)
    ).catch(
        err=>errorCallback(err)
    )
}

const snsLogin = (data, callback, errorCallback) =>{
    http.get('/user/snslogin',{
        params:{
            nickname: data.nickname,
            email: data.email
        }
    } 
    ).then(
        res=>callback(res)
    ).catch(
        err=>errorCallback(err)
    )
}

const snsregister = (data, callback, errorCallback) => {
    console.log(data.sns);
    console.log(data.email);
    console.log(data.nickname);
    
    http.post('/user/snsregister',data).then(
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
    getFollow: (data, callback, errorCallback) => getFollow(data, callback, errorCallback),
    emailfindpw: (data, callback, errorCallback) => emailfindpw(data, callback, errorCallback),
    getUser: (config,data, callback, errorCallback) => getUser(config,data, callback, errorCallback),
    getFollower: (data, callback, errorCallback) => getFollower(data, callback, errorCallback),
    getFollowing: (data, callback, errorCallback) => getFollowing(data, callback, errorCallback),
    getWaiter: (data, callback, errorCallback) => getWaiter(data, callback, errorCallback),
    deleteFollower: (data, callback, errorCallback) => deleteFollower(data, callback, errorCallback),
    requestUser: (data, callback, errorCallback) => requestUser(data, callback, errorCallback),
    latestFeed: (data, callback, errorCallback) => latestFeed(data, callback, errorCallback),
    createRecipe: (data, callback, errorCallback) => createRecipe(data, callback, errorCallback),
    searchByNickname: (data, callback, errorCallback) => searchByNickname(data, callback, errorCallback),
    getAllmember: (data, callback, errorCallback) => getAllmember(data, callback, errorCallback),
    sendfollowrequest: (data, callback, errorCallback) => sendfollowrequest(data, callback, errorCallback),
    sendfollowdelete: (data, callback, errorCallback) => sendfollowdelete(data, callback, errorCallback),
    follow_already: (data, callback, errorCallback) => follow_already(data, callback, errorCallback),
    myAllRecipes: (data, callback, errorCallback) => myAllRecipes(data, callback, errorCallback),
    singleRecipe: (data, callback, errorCallback) => singleRecipe(data, callback, errorCallback),
    snsLogin: (data, callback, errorCallback) => snsLogin(data, callback, errorCallback),
    snsregister: (data, callback, errorCallback) => snsregister(data, callback, errorCallback),
    
}
export default UserApi
