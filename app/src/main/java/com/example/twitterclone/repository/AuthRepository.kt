package com.example.twitterclone.repository

import com.example.twitterclone.api.AppwriteConfig
import com.example.twitterclone.models.User
import io.appwrite.exceptions.AppwriteException
import io.appwrite.models.Session
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository {
    private val account = AppwriteConfig.getAccount()
    private val databases = AppwriteConfig.getDatabases()

    suspend fun register(
        email: String,
        password: String,
        username: String,
        displayName: String
    ): Result<User> = withContext(Dispatchers.IO) {
        try {
            // Crear usuario en Appwrite Auth
            val createdUser = account.create(
                userId = "unique()",
                email = email,
                password = password
            )
            
            // Crear documento en colección "users"
            val user = User(
                id = createdUser.id,
                username = username,
                displayName = displayName,
                email = email,
                avatarFileId = null,
                bannerFileId = null,
                bio = "",
                createdAt = System.currentTimeMillis()
            )
            
            databases.createDocument(
                databaseId = AppwriteConfig.DATABASE_ID,
                collectionId = AppwriteConfig.USERS_COLLECTION,
                documentId = createdUser.id,
                data = mapOf(
                    "username" to username,
                    "displayName" to displayName,
                    "email" to email,
                    "bio" to "",
                    "createdAt" to System.currentTimeMillis()
                )
            )
            
            Result.success(user)
        } catch (e: AppwriteException) {
            Result.failure(Exception(e.message ?: "Registration failed"))
        }
    }

    suspend fun login(email: String, password: String): Result<Session> = 
        withContext(Dispatchers.IO) {
        try {
            val session = account.createEmailPasswordSession(
                email = email,
                password = password
            )
            Result.success(session)
        } catch (e: AppwriteException) {
            Result.failure(Exception(e.message ?: "Login failed"))
        }
    }

    suspend fun getCurrentUser(): Result<User> = withContext(Dispatchers.IO) {
        try {
            val authUser = account.get()
            
            // Obtener documento del usuario desde Firestore
            val doc = databases.getDocument(
                databaseId = AppwriteConfig.DATABASE_ID,
                collectionId = AppwriteConfig.USERS_COLLECTION,
                documentId = authUser.id
            )
            
            val user = User(
                id = authUser.id,
                username = doc.data["username"] as? String ?: "",
                displayName = doc.data["displayName"] as? String ?: "",
                email = authUser.email,
                avatarFileId = doc.data["avatarFileId"] as? String,
                bannerFileId = doc.data["bannerFileId"] as? String,
                bio = doc.data["bio"] as? String ?: "",
                createdAt = (doc.data["createdAt"] as? Number)?.toLong() ?: System.currentTimeMillis()
            )
            
            Result.success(user)
        } catch (e: AppwriteException) {
            Result.failure(Exception(e.message ?: "Failed to get current user"))
        }
    }

    suspend fun logout(): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            account.deleteSession(sessionId = "current")
            Result.success(Unit)
        } catch (e: AppwriteException) {
            Result.failure(Exception(e.message ?: "Logout failed"))
        }
    }

    suspend fun isUserLoggedIn(): Boolean = withContext(Dispatchers.IO) {
        try {
            account.get()
            true
        } catch (e: AppwriteException) {
            false
        }
    }
}
