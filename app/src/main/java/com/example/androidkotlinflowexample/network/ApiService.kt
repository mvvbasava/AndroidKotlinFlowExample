package com.example.androidkotlinflowexample.network

import com.example.androidkotlinflowexample.data.CommentModel
//import com.example.androidkotlinflowexample.data.ListOfCommentModel
import retrofit2.http.GET
import retrofit2.http.Path

/***
 *
 * Step 6: Create an API Service
 *
 * We need to create an API interface to call the API using a retrofit.
 * Create a kotlin file named ApiService.kt and add the following code
 *
 */

/**
 *
 * https://www.geeksforgeeks.org/kotlin/kotlin-flow-in-android-with-example/
 *
 */

interface ApiService {
    // Get method to call the api ,passing id as a path
    @GET("/comments/{id}")
    suspend fun getComments(@Path("id") id: Int): CommentModel

    @GET("/comments")
    suspend fun getAllComments(): List<CommentModel>
}