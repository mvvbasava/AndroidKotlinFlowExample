package com.example.androidkotlinflowexample.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/***
 *
 * Step 8: Create a Retrofit Instance for network calls
 *
 * Create a kotlin file named RetrofitInstance.kt and add code to create API service,
 * which will be used to make API calls.
 *
 */

/**
 *
 * https://www.geeksforgeeks.org/kotlin/kotlin-flow-in-android-with-example/
 *
 */

object RetrofitInstance {
    // Base url of the api
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    // create retrofit service
    fun apiService(): ApiService =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)
}
