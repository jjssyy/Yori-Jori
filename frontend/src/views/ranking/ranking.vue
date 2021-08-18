<template>
    <div class="ranking">
        <h1>My Rank</h1>
        <div  id="myrank">
            <div id="myrank-content">
                <div  id="myrank_head">
                    <!-- <p>My Rank</p> -->
                    <div id="myrank_img">
                        <img :src="userImg" alt="">
                    </div>
                    <p>{{ mynickname }}</p>
                </div>
                <div id="myrank_des">
                    <!-- <p>닉네임 : {{mynickname}}</p> -->
                    <p>나의 순위는..</p>
                    <p class="rank">{{myrank}}위</p>
                    <p class="point">{{mypoint}}포인트</p>
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
    margin: 10px;
    background-color: #ffbe76;
    border-radius: 25px;
    height: 30%;
    width: 94%;
    max-width: 616px;
    color:white;
    padding: 10px;
}
.rank{
    font-size: 3rem;
    font-weight: bold;
}
.point{
    font-size: 1.5rem;
}
#myrank_head{
    font-size: 2rem;
    max-width: 200px;
    
    /* display: inline; */
    display: flex;
    flex-direction: column;
    align-items: flex-start;
}
#myrank_head p{
}
#myrank-content{
    display: flex;
    justify-content: space-evenly;
    height: 100%;
}
#myrank_img{
    width: 100%;
    height: width;
    display: flex;
    justify-content: center;
    margin-right: 3rem;
    overflow: hidden;
    border: none;
    margin-top: 1.2rem;
}
#myrank_img img{
    /* padding: 0%; */
    /* margin: 0%; */
    max-width: 100%;
    height: width;
    border-radius: 50%;
    background-color: #fafafa;
    object-fit: cover;
    border: none;

}
#myrank_des{
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
    font-size: 1.2rem;
    padding: 10px;
    align-items: center;
    margin-top: 1rem;
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
