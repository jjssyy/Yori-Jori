<template>
    <div id="kakao-login">

        <button @click="kakaologin">
              <img sytle="width:300px; height:300px;" :src="require(`@/assets/images/kakao.jpg`)" />
        </button>
    </div>
</template>

<script>
import UserApi from "../../../api/UserApi";
    export default {
       
        methods: {
            kakaologin(){
                window.Kakao.Auth.login({
                    scope: 'profile_nickname, account_email,birthday',
                    success: this.getprofile
                })
            },

            getprofile(authobj){

                
                window.Kakao.API.request({
                    url:'/v2/user/me',
                    success: res=> {
                        const kakao_account = res.kakao_account;
                        console.log(kakao_account.profile.nickname);
                        console.log(kakao_account.email);
                        console.log(kakao_account.birthday);

                        let data = {
                            email : kakao_account.email,
                            nickname : kakao_account.profile.nickname,
                            sns:'kakao',
                            birthday:kakao_account.birthday
                            };

                        UserApi.snsregister(
                            data,
                            res => {
                                if(res.data == "success"){
                                    alert("회원가입에 성공하였습니다.");
                                    this.$router.push({name:'Login'});
                                 
                                }else if(res.data == "fail"){
                                    alert("회원가입에 실패하였습니다.");
                                    
                                }else{
                                    alert("에러가 발생하였습니다.");
                                    
                                }
                            },
                            error => {
                                //요청이 끝나면 버튼 활성화
                                alert("에러가 발생하였습니다.");
                                

                                this.$router.push({name:'ErrorPage'});
                            }
                            );


                        

                        
                    } 
                });

            },
        },

    }
</script>
