package com.example.twitterclone.api

import android.content.Context
import io.appwrite.Client
import io.appwrite.services.Account
import io.appwrite.services.Databases
import io.appwrite.services.Storage

object AppwriteConfig {
    private const val ENDPOINT = "https://sfo.cloud.appwrite.io/v1"
    private const val PROJECT_ID = "69a630fe001a72eafc43"
    const val DATABASE_ID = "69a631bc0026422b480c"
    
    // Collection IDs
    const val USERS_COLLECTION = "users"
    const val POSTS_COLLECTION = "post"
    const val LIKES_COLLECTION = "likes"
    const val REPOSTS_COLLECTION = "repost"
    const val BOOKMARKS_COLLECTION = "bookmarks"
    const val FOLLOWS_COLLECTION = "follows"
    const val NOTIFICATIONS_COLLECTION = "notifications"
    const val MESSAGES_COLLECTION = "messages"
    
    // Bucket IDs
    const val MEDIA_BUCKET = "69a6315a00096f0c2d1a"
    
    private lateinit var client: Client
    
    fun init(context: Context) {
        client = Client(context)
            .setEndpoint(ENDPOINT)
            .setProject(PROJECT_ID)
            .setSelfSigned(false)
    }
    
    fun getClient(): Client {
        if (!::client.isInitialized) {
            throw IllegalStateException("AppwriteConfig not initialized. Call init() first.")
        }
        return client
    }
    
    fun getAccount(): Account = Account(getClient())
    fun getDatabases(): Databases = Databases(getClient())
    fun getStorage(): Storage = Storage(getClient())
}
