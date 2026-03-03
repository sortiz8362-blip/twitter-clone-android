package com.example.twitterclone.models

// Legacy models - kept for reference
// Use models from AppModels.kt instead

data class Tweet(
    val id: String,
    val content: String,
    val timestamp: String
)

data class SearchResult(
    val users: List<User> = emptyList(),
    val tweets: List<Tweet> = emptyList()
)

data class ApiResponse<T>(
    val success: Boolean,
    val data: T? = null,
    val message: String? = null
)

