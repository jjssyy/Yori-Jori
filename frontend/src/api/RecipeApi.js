import http from "@/util/http-common";

// Comment CRUD + 좋아요

const writeComment = (data, callback, errorCallback) => {
  http.post('/comment/write',data).then(
      res=>callback(res)
  ).catch(
      err=>errorCallback(err)
  )
}

const updateComment = (data,callback, errorCallback) => {
  http.put('/comment/update',data).then(
    res=>callback(res)
  ).catch(
    err=>errorCallback(err)
  )
}

const deleteComment = (data,callback, errorCallback) => {
  http.delete('/comment/delete',data).then(
    res=>callback(res)
  ).catch(
    err=>errorCallback(err)
  )
}

const recipeItemComments = (data,callback, errorCallback) => {
  http.get('/comment',data).then(
    res=>callback(res)
  ).catch(
    err=>errorCallback(err)
  )
}

const commentLike = (data,callback, errorCallback) => {
  http.post('/comment',data).then(
    res=>callback(res)
  ).catch(
    err=>errorCallback(err)
  )
}

const RecipeApi = {
  writeComment: (data, callback, errorCallback) => writeComment(data, callback, errorCallback),
  updateComment: (data, callback, errorCallback) => updateComment(data, callback, errorCallback),
  deleteComment: (data, callback, errorCallback) => deleteComment(data, callback, errorCallback),
  recipeItemComments: (data, callback, errorCallback) => recipeItemComments(data, callback, errorCallback),
  commentLike: (data, callback, errorCallback) => commentLike(data, callback, errorCallback),
}
export default RecipeApi