package com.example.androidkotlinflowexample.data

import com.google.gson.annotations.SerializedName

/***
 *
 * Step 5: Create a model to save data from API calls
 *
 * We will be using https://jsonplaceholder.typicode.com/comments API,
 * It gives some JSON data when id is passed as a path.
 *
 * For example, https://jsonplaceholder.typicode.com/comments/2 gives JSON
 * which contains some random data.
 *
 * We will be using this data and show it on the screen using kotlin flow.
 * Create a kotlin file named CommentModel and create a dataclass to parse data that is received from the API.
 *
 * Example Response
 * We need to create a dataclass for this response. Add the following code in CommentModel
 *
 */

/**
 *
 * https://www.geeksforgeeks.org/kotlin/kotlin-flow-in-android-with-example/
 *
 */

data class CommentModel(
    val postId: Int?=null,
    val id: Int?=null,
    val email: String?=null,
    val name:String?=null,

    @SerializedName("body")
    val comment: String?=null
)
