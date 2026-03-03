package com.example.twitterclone.repository

import com.example.twitterclone.api.AppwriteConfig
import com.example.twitterclone.models.Post
import io.appwrite.exceptions.AppwriteException
import io.appwrite.models.DocumentList
import io.appwrite.query.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository {
    private val databases = AppwriteConfig.getDatabases()

    suspend fun getFeed(): Result<List<Post>> = withContext(Dispatchers.IO) {
        try {
            val documents = databases.listDocuments(
                databaseId = AppwriteConfig.DATABASE_ID,
                collectionId = AppwriteConfig.POSTS_COLLECTION,
                queries = listOf(
                    Query.orderDesc("timestamp"),
                    Query.limit(50),
                    Query.equal("replyToId", null),
                    Query.equal("repostOfId", null)
                )
            )
            
            val posts = documents.documents.map { doc ->
                Post(
                    id = doc.id,
                    authorId = doc.data["authorId"] as? String ?: "",
                    content = doc.data["content"] as? String ?: "",
                    imageFileId = doc.data["imageFileId"] as? String,
                    timestamp = (doc.data["timestamp"] as? Number)?.toLong() ?: System.currentTimeMillis(),
                    repostOfId = doc.data["repostOfId"] as? String,
                    replyToId = doc.data["replyToId"] as? String,
                    likeCount = (doc.data["likeCount"] as? Number)?.toInt() ?: 0,
                    repostCount = (doc.data["repostCount"] as? Number)?.toInt() ?: 0,
                    replyCount = (doc.data["replyCount"] as? Number)?.toInt() ?: 0
                )
            }
            Result.success(posts)
        } catch (e: AppwriteException) {
            Result.failure(Exception(e.message ?: "Failed to get feed"))
        }
    }

    suspend fun createPost(content: String, imageFileId: String? = null): Result<Post> = 
        withContext(Dispatchers.IO) {
        try {
            val account = AppwriteConfig.getAccount()
            val currentUser = account.get()
            
            val timestamp = System.currentTimeMillis()
            val result = databases.createDocument(
                databaseId = AppwriteConfig.DATABASE_ID,
                collectionId = AppwriteConfig.POSTS_COLLECTION,
                documentId = "unique()",
                data = mapOf(
                    "authorId" to currentUser.id,
                    "content" to content,
                    "imageFileId" to (imageFileId ?: ""),
                    "timestamp" to timestamp,
                    "likeCount" to 0,
                    "repostCount" to 0,
                    "replyCount" to 0
                )
            )
            
            val post = Post(
                id = result.id,
                authorId = currentUser.id,
                content = content,
                imageFileId = if (imageFileId.isNullOrEmpty()) null else imageFileId,
                timestamp = timestamp,
                likeCount = 0,
                repostCount = 0,
                replyCount = 0
            )
            
            Result.success(post)
        } catch (e: AppwriteException) {
            Result.failure(Exception(e.message ?: "Failed to create post"))
        }
    }

    suspend fun deletePost(postId: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            databases.deleteDocument(
                databaseId = AppwriteConfig.DATABASE_ID,
                collectionId = AppwriteConfig.POSTS_COLLECTION,
                documentId = postId
            )
            Result.success(Unit)
        } catch (e: AppwriteException) {
            Result.failure(Exception(e.message ?: "Failed to delete post"))
        }
    }

    suspend fun likePost(postId: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val account = AppwriteConfig.getAccount()
            val currentUser = account.get()
            
            databases.createDocument(
                databaseId = AppwriteConfig.DATABASE_ID,
                collectionId = AppwriteConfig.LIKES_COLLECTION,
                documentId = "unique()",
                data = mapOf(
                    "userId" to currentUser.id,
                    "postId" to postId,
                    "timestamp" to System.currentTimeMillis()
                )
            )
            
            Result.success(Unit)
        } catch (e: AppwriteException) {
            Result.failure(Exception(e.message ?: "Failed to like post"))
        }
    }

    suspend fun unlikePost(postId: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val account = AppwriteConfig.getAccount()
            val currentUser = account.get()
            
            // Buscar el like por userId y postId
            val documents = databases.listDocuments(
                databaseId = AppwriteConfig.DATABASE_ID,
                collectionId = AppwriteConfig.LIKES_COLLECTION,
                queries = listOf(
                    Query.equal("userId", currentUser.id),
                    Query.equal("postId", postId)
                )
            )
            
            if (documents.documents.isNotEmpty()) {
                databases.deleteDocument(
                    databaseId = AppwriteConfig.DATABASE_ID,
                    collectionId = AppwriteConfig.LIKES_COLLECTION,
                    documentId = documents.documents[0].id
                )
            }
            
            Result.success(Unit)
        } catch (e: AppwriteException) {
            Result.failure(Exception(e.message ?: "Failed to unlike post"))
        }
    }

    suspend fun isPostLikedByUser(postId: String): Result<Boolean> = withContext(Dispatchers.IO) {
        try {
            val account = AppwriteConfig.getAccount()
            val currentUser = account.get()
            
            val documents = databases.listDocuments(
                databaseId = AppwriteConfig.DATABASE_ID,
                collectionId = AppwriteConfig.LIKES_COLLECTION,
                queries = listOf(
                    Query.equal("userId", currentUser.id),
                    Query.equal("postId", postId)
                )
            )
            
            Result.success(documents.documents.isNotEmpty())
        } catch (e: AppwriteException) {
            Result.failure(Exception(e.message ?: "Failed to check if liked"))
        }
    }
}
