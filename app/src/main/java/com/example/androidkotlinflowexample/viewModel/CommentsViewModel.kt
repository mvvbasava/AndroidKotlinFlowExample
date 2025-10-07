package com.example.androidkotlinflowexample.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidkotlinflowexample.data.CommentApiState
import com.example.androidkotlinflowexample.data.CommentModel
import com.example.androidkotlinflowexample.network.RetrofitInstance
import com.example.androidkotlinflowexample.data.Status
import com.example.androidkotlinflowexample.repository.CommentsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/***
 *
 * Step 10: Working with the ViewModel
 *
 * Create a kotlin class file named CommentViewModel.kt .Add the following code.
 * Refer to the comments for an explanation.
 *
 */

/**
 *
 * https://www.geeksforgeeks.org/kotlin/kotlin-flow-in-android-with-example/
 *
 */

class CommentsViewModel : ViewModel() {
    // initialize the repo and pass the api service as parameter
    private val repository = CommentsRepository(RetrofitInstance.apiService())

    // MutableStateFlow to store comment data
    val commentState = MutableStateFlow(CommentApiState(Status.LOADING, CommentModel(), ""))

    // MutableStateFlow to store comment data
    val commentListState = MutableStateFlow(CommentApiState(Status.LOADING, listOf(CommentModel()), ""))

    init {
        // Initiate a starting search with comment Id 1
        getNewComment(1)
        getAllComments()
    }

    // Function to get new Comments
    fun getNewComment(id: Int) {
        // initialize state as loading
        commentState.value = CommentApiState.loading()

        // use coroutine scope to let the api call run asynchronously
        viewModelScope.launch {
            // Collecting the data emitted by the function in repository
            repository.getComment(id)
                // handle errors like 404 not found, invalid id etc
                .catch {
                    commentState.value = CommentApiState.error(it.message.toString())
                }
                // on the success response, data is set to state for observing and updating
                .collect {
                    commentState.value = CommentApiState.success(it.data)
                }
        }
    }

    // Function to get new Comments
    fun getAllComments() {
        // initialize state as loading
        commentListState.value = CommentApiState.loading()

        // use coroutine scope to let the api call run asynchronously
        viewModelScope.launch {
            // Collecting the data emitted by the function in repository
            repository.getAllComments()
                // handle errors like 404 not found, invalid id etc
                .catch {
                    commentListState.value = CommentApiState.error(it.message.toString())
                }
                // on the success response, data is set to state for observing and updating
                .collect {
                    commentListState.value = CommentApiState.success(it.data)
                }
        }
    }
}
