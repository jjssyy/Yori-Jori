/*
 User API 예시
 */
import http from "@/util/http-common";

const requestLogin = (data,callback,errorCallback) => {
    //백앤드와 로그인 통신하는 부분
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

const requestJoin = (data,callback,errorCallback)=>{
    http.post("/account/signup",data).then(
        res=>callback(res)
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
    requestChangePassword:(data,callback,errorCallback)=>requestChangePassword(data,callback,errorCallback),
}

export default UserApi
