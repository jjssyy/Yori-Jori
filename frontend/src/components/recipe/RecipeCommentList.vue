<template lang="">
  <div>
    <div class="commentForm">
      <input type="text" placeholder="댓글을 입력하시오" v-model.trim="comment.content" @keypress.enter="createComment">
      <button @click="createComment">작성</button>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import UserApi from '../../api/UserApi';

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
  methods: {
    createComment() {
      let data = {
        content_idx: this.recipeItem.idx,
        comment: this.comment.content,
        id: this.userId,
      }
      UserApi.writeComment(
        data,
        res => {
          console.log("댓글 쓰기 성공")
          // newComments.push(comment.content)
        },
        error=> {
          console.log(error)
        }
      )
    }
  },
  computed: {
    ...mapState([
      'userId',
    ])
  }
}
</script>

<style scoped>
  .commentForm {
    margin-top: 10px;
  }
</style>