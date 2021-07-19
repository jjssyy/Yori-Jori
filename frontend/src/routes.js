

import Login from './views/user/Login.vue'
import Join from './views/user/Join.vue'
import ChangePassword from './views/user/ChangePassword.vue'
import JoinComplete from './views/user/JoinComplete.vue'
import FeedMain from './views/feed/IndexFeed.vue'
import Components from './views/Components.vue'
import Pagenotfound from './views/error/PageNotFound.vue'
import ErrorPage from './views/error/ErrorPage.vue'
import MyPage from './views/profile/MyPage.vue'

export default [


    {
        path : '/',
        name : 'Login',
        component : Login
    },
    {
        path : '/user/join',
        name : 'Join',
        component : Join
    },
    {
        path : '/feed/main',
        name : 'FeedMain',
        component : FeedMain
    },
    {
        path : '/components',
        name : 'Components',
        component : Components
    },
    {
        path:'/user/password',
        name:'ChangePassword',
        component:ChangePassword
    },
    {
        path:'/user/join/complete',
        name:'JoinComplete',
        component:JoinComplete
    },
    {
        path: '/user/mypage',
        name: 'MyPage',
        component: MyPage
    },
    {
        path: '*',
        redirect:'/404'
    },
    {
        path: '/404',
        component:Pagenotfound
    },
    {
        path:'/error',
        name:'ErrorPage',
        component:ErrorPage
    },
]
