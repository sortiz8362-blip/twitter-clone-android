package com.example.twitterclone.api

import com.example.twitterclone.models.ApiResponse
import com.example.twitterclone.models.SearchResult
import com.example.twitterclone.models.Tweet
import com.example.twitterclone.models.User
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Body

interface TwitterApi {
    @GET("/api/tweets/feed")
    suspend fun getFeed(): ApiResponse<List<Tweet>>

    @GET("/api/tweets/trending")
    suspend fun getTrendingTweets(): ApiResponse<List<Tweet>>

    @GET("/api/search")
    suspend fun search(@Query("q") query: String): ApiResponse<SearchResult>

    @GET("/api/user/profile")
    suspend fun getUserProfile(): ApiResponse<User>

    @POST("/api/tweets/create")
    suspend fun createTweet(@Body content: Map<String, String>): ApiResponse<Tweet>

    @POST("/api/tweets/{id}/like")
    suspend fun likeTweet(@Query("id") tweetId: String): ApiResponse<Tweet>

    @GET("/api/notifications")
    suspend fun getNotifications(): ApiResponse<List<Map<String, Any>>>
}
