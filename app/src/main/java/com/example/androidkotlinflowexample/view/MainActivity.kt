package com.example.androidkotlinflowexample.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidkotlinflowexample.R
import com.example.androidkotlinflowexample.data.Status
import kotlinx.coroutines.launch
import com.example.androidkotlinflowexample.databinding.ActivityMainBinding
import com.example.androidkotlinflowexample.viewModel.CommentsViewModel

/**
 *
 * Step 11: Working with MainActivity.kt
 *
 * Open presentation > MainActivity.kt.
 * Add the following code, refer to the comments for explanation.
 * We now need to call the API from view(MainActivity) and show the data on the screen.
 *
 */

/**
 *
 * https://www.geeksforgeeks.org/kotlin/kotlin-flow-in-android-with-example/
 *
 */
class MainActivity : AppCompatActivity() {
    // initiate the viewmodel
    private val viewModel: CommentsViewModel by lazy {
        ViewModelProvider(this)[CommentsViewModel::class.java]
    }
    // create a view binding variable
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val recyclerView: RecyclerView = findViewById(R.id.comments_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.getAllComments()
        // Since flow run asynchronously, start listening on background thread
        lifecycleScope.launch {
            viewModel.commentListState.collect {
                // When state to check the state of received data
                when (it.status) {
                    // on loading, show the progress bar
                    Status.LOADING -> {
                        binding.progressBar.isVisible = true
                    }
                    // on success, update ui and make progress bar invisible
                    Status.SUCCESS -> {
                        binding.progressBar.isVisible = false
                        // handle null pointer exception
                        it.data?.let { comments ->
                            binding.commentsRecyclerView.adapter = CustomListAdapter(comments)
                        }
                    }
                    // on error, show toast with error message
                    else -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(this@MainActivity, "${it.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        // set on click listener for search button
        binding.button.setOnClickListener {
            val id = binding.searchEditText.text.toString().trim()
            // check to prevent api call with no parameters
            if (id.isEmpty() || id == "0") {
                Toast.makeText(this, "Id Can't be 0 or empty", Toast.LENGTH_SHORT).show()
            } else {
                // if id isn't empty, make the api call
                viewModel.getNewComment(id.toInt())
            }
        }

        // Since flow run asynchronously, start listening on background thread
        lifecycleScope.launch {
            viewModel.commentState.collect {
                // When state to check the state of received data
                when (it.status) {
                    // on loading, show the progress bar
                    Status.LOADING -> {
                        binding.progressBar.isVisible = true
                    }
                    // on success, update ui and make progress bar invisible
                    Status.SUCCESS -> {
                        binding.progressBar.isVisible = false
                        // handle null pointer exception
                        it.data?.let { comment ->
                            binding.commentIdTextview.text = comment.id.toString()
                            binding.nameTextview.text = comment.name
                            binding.emailTextview.text = comment.email
                            binding.commentTextview.text = comment.comment
                        }
                    }
                    // on error, show toast with error message
                    else -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(this@MainActivity, "${it.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
