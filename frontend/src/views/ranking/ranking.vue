<template>
    <div>
        <div class="wrapC">
            <div class="row">
                <h1>랭킹</h1>
            </div>
            <div class="row" id="myrank">
                <div class="row" id="myrank_head">
                    <p>My Rank</p>
                </div>
                <div class="row">
                    <div class="col md-4" id="myrank_img">
                        <p>이미지</p>
                    </div>
                    <div class="col md-8" id="myrank_des">
                        <p>닉네임 : {{mynickname}}</p>
                        <p>순위 : {{myrank}}</p>
                        <p>포인트 : {{mypoint}}</p>
                    </div>
                </div>
            </div>
            <div class="row" id="top100_head">
                <h2>TOP 100</h2>
            </div>

            <div class="row" id="top100_table">
                <table class="table table-hover">
                    <thead>
                        <th scope="col">순위</th>
                        <th scope="col">닉네임</th>
                        <th scope="col">포인트</th>
                    </thead>
                    <tbody>
                        <tr v-for="(rank, idx) in ranking" :key="idx">
                            <td>{{rank.rank}}</td>
                            <td>{{rank.nickname}}</td>
                            <td>{{rank.rankpoint}}</td>
                        </tr>
                    </tbody>
                </table>
            
            </div>
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
        'userId',
    ]),
    }
}
</script>

<style>
#myrank{
    background-color: #ffbe76;
    border-radius: 25px;
    height: 300px;
    color:white;
    margin-top: 50px;
    margin-left:10px;
}

#myrank_head{
    font-size: 35px;
    margin: 30px 0px 0px 50px;
    height: 20px;
}

#myrank_img p{
    background-color: gray;
    width:150px;
    height: 150px;
    margin: 0px 0px 0px 70px;
}

#myrank_des{
    font-size: 20px;
    margin: 5px 0px 0px 90px;  
}

#top100_head{
    margin-top: 100px;
}

#top100_table{
    margin:50px 0px 0px 10px;
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
/* thead th:first-child {
    border-radius: 30px 0 0 0;
}
thead th:last-child {
    border-radius: 0 30px 0px 0;
}

.table > thead > tr > th {
    border: none !important;
}

.table > tbody > tr:first-child > td,
.table > tbody > tr:first-child > th {
    border-top: none;
} */

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
