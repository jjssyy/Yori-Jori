/*
 User API 예시
 */
import http from "@/util/http-common";

const requestLogin = (data,callback,errorCallback) => {
    //백앤드와 로그인 통신하는 부

    http.get("/account/login",{
        params:{
            email:data.email,
            password:data.password
        }
    }).then(
        res=>callback(res)
    ).catch(
        err=>errorCallback(err)
    );
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

const UserApi = {
    requestLogin:(data,callback,errorCallback)=>requestLogin(data,callback,errorCallback),
    requestJoin:(data,callback,errorCallback)=>requestJoin(data,callback,errorCallback),
    requestChangePassword: (data, callback, errorCallback) => requestChangePassword(data, callback, errorCallback),
    checkid: (data, callback, errorCallback) => checkid(data, callback, errorCallback),
    checknickname: (data, callback, errorCallback) => checknickname(data, callback, errorCallback),
}

export default UserApi
