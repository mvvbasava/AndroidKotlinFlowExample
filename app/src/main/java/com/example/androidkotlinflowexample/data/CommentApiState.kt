package com.example.androidkotlinflowexample.data

/***
 *
 * Step 7: Create a helper class from saving States of API calls
 *
 * Let's add some helper classes to handle the loading or error state of API.
 * Create a kotlin file named CommentApiState.kt. Refer to the comment in the code for an explanation.
 *
 */

/**
 *
 * https://www.geeksforgeeks.org/kotlin/kotlin-flow-in-android-with-example/
 *
 */

data class CommentApiState<out T> (
    val status: Status,
    val data: T?,
    val message: String?
) {
    companion object {
        // In case of Success,
        // set status as Success and
        // data as the response
        // set message as null
        fun <T> success(data: T?): CommentApiState<T> {
            return CommentApiState(Status.SUCCESS, data, null)
        }

        // In case of Success,
        // set status as Success and
        // data as the response
        // set message as null
        fun <T> success(data: List<T>): CommentApiState<List<T>> {
            return CommentApiState(Status.SUCCESS, data, null)
        }

        // In case of failure,
        // set state to Error,
        // set data to null
        // message as add the error message,
        fun <T> error(msg: String): CommentApiState<T> {
            return CommentApiState(Status.ERROR, null, msg)
        }

        // When the call is loading
        // set the state as Loading and
        // set data to null
        // set message as null
        fun <T> loading(): CommentApiState<T> {
            return CommentApiState(Status.LOADING, null, null)
        }
    }
}

// An enum to store the
// current state of api call
enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
