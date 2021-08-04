<template lang="">
  <div>
    <div v-if="update == false">
      {{ commentItem }}
      <div>
      <p>닉네임 : {{ commentItem.nickname }} 
         코멘트 : {{ commentItem.comment }}</p>
      <p>좋아요 갯수 : {{ commentItem.like }}</p>
      </div>
      <span v-if="commentItem.likecheck == false">
          <button class="submit btn btn-secondary" @click="like">
             좋아요
          </button>
      </span>
      <span v-else>
          <button class="submit btn btn-secondary" @click="unlike">
            좋아요 취소
          </button>   
      </span>
    </div>
    <div v-if="update == false">
      <div v-if="commentItem.id == userId">
        <button @click="update = true">Update</button>
        <button @click="deleteComment(idx)">Delete</button>
      </div>
    </div>
    <div v-if="update">
      <input type="text" v-model.trim="commentItem.comment">
    </div>
    <div v-if="update">
      <button @click="update = false">Update완료</button>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import RecipeApi from '../../api/RecipeApi';

export default {
  data: () => {
    return {
      update: false,
    }
  },
  props: {
    comments : {
      type: [Array, Object],
    },
    commentItem: {
      type: [Array, Object],
    },
    idx: Number,
  },
  methods: {
    like() {
      let data = {
        comment_idx: this.commentItem.idx,
        id: this.userId
      }
      RecipeApi.commentLike(
        data,
        res => {
          console.log("좋아요 성공")
          this.commentItem.likecheck = true
          this.commentItem.like += 1
        },
        err => {
          console.log(err)
        }
      )
    },
    unlike() {
      let data = {
        comment_idx: this.commentItem.idx,
        id: this.userId
      }
      console.log(data)
      RecipeApi.commentUnlike(
        data,
        res => {
          console.log("좋아요 취소 성공")
          this.commentItem.likecheck = false
          this.commentItem.like -= 1
        },
        err => {
          console.log(err)
        }
      )
    },
    updateComment() {
      let data = {
        content_idx: this.commmentItem.idx,
        comment: this.commentItem.comment,
        id: this.userId,
        nickname: this.userNickname,
      }
      RecipeApi.updateComment(
        data,
        res => {
          console.log("댓글 수정 성공")
        },
        error=> {
          console.log(error)
        }
      )

    },
    deleteComment(idx) {
      const newComments = this.comments
      let data = {
        idx: this.commentItem.idx
      }
      RecipeApi.deleteComment(
        data,
        res => {
          console.log("댓글 삭제 성공")
          newComments.splice(idx,1)
        },
        error=> {
          console.log(error)
        }
      )
      this.comments = newComments
    },
  },
  computed: {
    ...mapState([
      'userId',
      'userNickname',
    ])
  }
}
</script>

<style lang="">
  
</style>