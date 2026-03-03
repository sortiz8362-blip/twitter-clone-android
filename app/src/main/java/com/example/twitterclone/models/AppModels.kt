package com.example.twitterclone.models

import java.io.Serializable

data class User(
    val id: String,
    val username: String,
    val displayName: String,
    val email: String,
    val avatarFileId: String? = null,
    val bannerFileId: String? = null,
    val bio: String = "",
    val createdAt: Long
) : Serializable

data class Post(
    val id: String,
    val authorId: String,
    val content: String,
    val imageFileId: String? = null,
    val timestamp: Long,
    val repostOfId: String? = null,
    val replyToId: String? = null,
    val likeCount: Int = 0,
    val repostCount: Int = 0,
    val replyCount: Int = 0,
    val isLiked: Boolean = false
) : Serializable

data class Like(
    val id: String,
    val userId: String,
    val postId: String,
    val timestamp: Long
) : Serializable

data class Follow(
    val id: String,
    val followerId: String,
    val followingId: String,
    val timestamp: Long
) : Serializable

data class Bookmark(
    val id: String,
    val userId: String,
    val postId: String,
    val timestamp: Long
) : Serializable

data class Notification(
    val id: String,
    val recipientId: String,
    val actorId: String,
    val type: String,
    val postId: String? = null,
    val timestamp: Long,
    val isRead: Boolean = false
) : Serializable

data class Message(
    val id: String,
    val senderId: String,
    val receiverId: String,
    val content: String,
    val timestamp: Long,
    val isRead: Boolean = false
) : Serializable
