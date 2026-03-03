package com.example.twitterclone.repository

import com.example.twitterclone.models.User

// Legacy repository - kept for reference only
// Use PostRepository and AuthRepository instead
class TwitterRepository {
    // Deprecated - use PostRepository
    suspend fun getFeed(): List<User> {
        return emptyList()
    }

    suspend fun getTrendingTweets(): List<User> {
        return emptyList()
    }
}

