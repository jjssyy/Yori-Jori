<template>
    <div class="ranking">
        <h1>랭킹</h1>
        <div  id="myrank">
            <div  id="myrank_head">
                <p>My Rank</p>
            </div>
            <div id="myrank-content">
                <div id="myrank_img">
                    <img :src="userImg" alt="">
                </div>
                <div id="myrank_des">
                    <p>닉네임 : {{mynickname}}</p>
                    <p>순위 : {{myrank}}</p>
                    <p>포인트 : {{mypoint}}</p>
                </div>
            </div>
        </div>
        <div  id="top100_head">
            <h2>TOP 100</h2>
        </div>

        <div  id="top100_table">
            <table class="table table-hover">
                <thead>
                    <th scope="col">순위</th>
                    <th scope="col">닉네임</th>
                    <th scope="col">포인트</th>
                </thead>
                <tbody>
                    <tr v-for="(rank, idx) in ranking" :key="idx">
                        <td v-if="rank.rank == 1"><i class="fas fa-trophy" style="color:#f1c40f"></i></td>
                        <td v-else-if="rank.rank == 2"><i class="fas fa-medal" style="color:#bdc3c7"></i></td>
                        <td v-else-if="rank.rank == 3"><i class="fas fa-medal" style="color:#cd7f32"></i></td>
                        <td v-else>{{rank.rank}}</td>
                        <td>{{rank.nickname}}</td>
                        <td>{{rank.rankpoint}}</td>
                    </tr>
                </tbody>
            </table>
        
        </div>
    </div>
</template>

<script>
import { mapState } from 'vuex'
import UserApi from '../../api/UserApi';
import RankApi from '../../api/RankApi';


export default {
    components: { },
    data: () => {
    return {
        ranking:[],
        myrank:null,
        mypoint:null,
        mynickname:null,
    }
    },
created() {

    RankApi.getRanking(
        res => {
            this.ranking = res.data.rankinglist
            console.log(res);
            for(let i = 0; i < this.ranking.length; i++){
                if(this.ranking[i].id == this.userId){
            this.myrank = this.ranking[i].rank
            this.mypoint = this.ranking[i].rankpoint
            this.mynickname = this.ranking[i].nickname
            
        }
    }
        },
        error=>{
        console.log(error)
        }
    )

   

    },
methods: {


    },
    computed: {
    ...mapState([
        'userId','userImg'
    ]),
    }
}
</script>

<style>
.ranking{
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 0%;
    margin: 0%;
}
#myrank{
    background-color: #ffbe76;
    border-radius: 25px;
    height: 250px;
    width: 500px;
    color:white;
    margin: 10px;
    padding: 10px;
}

#myrank_head{
    font-size: 35px;
    height: 20px;
    display: inline;
}
#myrank-content{
    display: flex;
    justify-content: space-between;
}
#myrank_img{
    height: 152px;
    height: width;
    display: flex;
    justify-content: center;
    margin-right: 3rem;
    overflow: hidden;
    border: none;
}
#myrank_img img{
    padding: 0%;
    margin: 0%;
    width: 152px;
    height: 152px;
    border-radius: 50%;
    background-color: #fafafa;
    object-fit: cover;
    border: none;
}
#myrank_des{
    font-size: 20px;
    padding: 10px; 
}

#top100_head{
    margin-top: 100px;
}

#top100_table{
    width: 100%;
    max-width: 616px;
}

#top100_table table thead{
    
    height: 70px;
    color: white;
    font-size: 25px;
    line-height: 60px;
    text-align: center;

}

thead th {
    background-color: #ffbe76;
}

.table  tbody  td{
    border-bottom: 1px solid lightgray;
}

#top100_table table tbody{
    height: 40px;

    font-size: 20px;
    line-height: 40px;
    text-align: center;
    
}





</style>
