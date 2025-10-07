package com.example.androidkotlinflowexample.repository

import com.example.androidkotlinflowexample.data.CommentApiState
import com.example.androidkotlinflowexample.data.CommentModel
import com.example.androidkotlinflowexample.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/***
 *
 * Step 9: Create a Repository
 *
 * Create a kotlin file with the name CommentsRepository.kt.
 * Add the following code. Refer to the comments for an explanation.
 * This repository calls the ApiService and returns the data as a flow in the IO Thread.
 *
 */

/**
 *
 * https://www.geeksforgeeks.org/kotlin/kotlin-flow-in-android-with-example/
 *
 */

class CommentsRepository(private val apiService: ApiService) {
    fun getComment(id: Int): Flow<CommentApiState<CommentModel>> {
        return flow {
            // get the comment Data from the api
            val comment = apiService.getComments(id)

            // Emit this data wrapped in the helper class [CommentApiState]
            emit(CommentApiState.success(comment))
        }.flowOn(Dispatchers.IO)
    }

    fun getAllComments(): Flow<CommentApiState<List<CommentModel>>> {
        return flow {
            // get the comment Data from the api
            val allComments = apiService.getAllComments()

            // Emit this data wrapped in the helper class [CommentApiState]
            emit(CommentApiState.success(allComments))
        }.flowOn(Dispatchers.IO)
    }

}