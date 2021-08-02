<template lang="">
  <div>
    <div v-if="update"></div>
    <p>{{ commentItem }}</p>
    <button @click="updateComment">Update</button>
    <button @click="deleteComment">Delete</button>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import RecipeApi from '../../api/UserApi';

export default {
  props: {
    commentItem: {
      type: [Array, Object],
    },
    idx: Number,
  },
  methods: {
    updateComment() {
      let data = {
        // content_idx: this.recipeItem.idx,
        // comment: this.comment.content,
        // id: this.userId,
        // nickname: this.userNickname,
      }
      RecipeApi.writeComment(
        data,
        res => {
          console.log("댓글 수정 성공")
        },
        error=> {
          console.log(error)
        }
      )

    },
    deleteComment() {
    },
  }
}
</script>

<style lang="">
  
</style>