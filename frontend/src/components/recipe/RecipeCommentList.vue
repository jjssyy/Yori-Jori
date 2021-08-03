<template lang="">
  <div>
    <div class="commentForm">
      <RecipeCommentItem v-for="(commentItem, idx) in comments" :comments="comments" :key="idx" :idx="idx"/>
      <input type="text" placeholder="댓글을 입력하시오" v-model.trim="comment.content" @keypress.enter="createComment">
      <button @click="createComment">작성</button>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import RecipeApi from '../../api/RecipeApi';

export default {
  props: {
    recipeItem: {
      type: [Array, Object]
    }
  },
  data: function() {
    return {
      comment: {
        content: '',
      },
      comments: null,
    }
  },
  mounted: function() {
    let data = {
      content_idx: this.recipeItem.idx,
      id: this.recipeItem.id,
    }
    RecipeApi.recipeItemComments(
      data,
      res => {
        console.log('조회 성공')
        console.log(res)
        this.comments = res.data
      },
      err => {
        console.log(err)
      }
    )
  },
  methods: {
    createComment() {
      console.log(this.comment.content)
      const newComments = this.comments
      let data = {
        content_idx: this.recipeItem.idx,
        comment: this.comment.content,
        id: this.userId,
        nickname: this.userNickname,
      }
      RecipeApi.writeComment(
        data,
        res => {
          console.log("댓글 쓰기 성공")
          newComments.push(this.comment.content)
        },
        error=> {
          console.log(error)
        }
      )
      this.comments = newComments
    }
  },
  computed: {
    ...mapState([
      'userId',
      'userNickname',
    ])
  }
}
</script>

<style scoped>
  .commentForm {
    margin-top: 10px;
  }
</style>