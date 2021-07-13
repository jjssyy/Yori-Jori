

import Login from './views/user/Login.vue'
import Join from './views/user/Join.vue'
import FeedMain from './views/feed/IndexFeed.vue'
import Joinsuccess from './views/user/Joinsuccess.vue'
import Components from './views/Components.vue'
import Findpw from './views/user/Findpw.vue'

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
        path : '/user/joinsuccess',
        name : 'Joinsuccess',
        component : Joinsuccess
    },
    {
        path : '/user/findpw',
        name : 'Findpw',
        component : Findpw
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
    }
]
