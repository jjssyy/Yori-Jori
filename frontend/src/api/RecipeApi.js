import http from "@/util/http-common";

// Comment CRUD + 좋아요

const writeComment = (data, callback, errorCallback) => {
    http.post('/feed/comment/write',data).then(
        res=>callback(res)
    ).catch(
        err=>errorCallback(err)
    )
}

const updateComment = (data,callback, errorCallback) => {
    http.put('/feed/comment/update',data).then(
        res=>callback(res)
    ).catch(
        err=>errorCallback(err)
    )
}

const deleteComment = (data,callback, errorCallback) => {
    http.delete('/feed/comment/delete',{
        params: {
            idx: data.idx,
        }
    }).then(
        res=>callback(res)
    ).catch(
        err=>errorCallback(err)
    )
}

const recipeItemComments = (data,callback, errorCallback) => {
    http.get('/feed/comment',{
      params: {
        content_idx: data.content_idx,
        id: data.id,
      }
    }).then(
        res=>callback(res)
    ).catch(
        err=>errorCallback(err)
    )
}

const commentLike = (data,callback, errorCallback) => {
    http.post('/feed/comment/like',data
    ).then(
        res=>callback(res)
    ).catch(
        err=>errorCallback(err)
    )
}

const commentUnlike = (data,callback, errorCallback) => {
    http.delete('/feed/comment/like',{
        params: {
            comment_idx: data.comment_idx,
            id: data.id,
        }
    }
    ).then(
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
    commentUnlike: (data, callback, errorCallback) => commentUnlike(data, callback, errorCallback),
}
export default RecipeApi