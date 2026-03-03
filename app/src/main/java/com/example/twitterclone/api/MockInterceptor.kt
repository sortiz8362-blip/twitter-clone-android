package com.example.twitterclone.api

import com.example.twitterclone.models.ApiResponse
import com.example.twitterclone.models.SearchResult
import com.example.twitterclone.models.Tweet
import com.example.twitterclone.models.User
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url.encodedPath

        val mockResponse = when {
            path.contains("/api/tweets/feed") -> createFeedResponse()
            path.contains("/api/tweets/trending") -> createTrendingResponse()
            path.contains("/api/user/profile") -> createUserProfileResponse()
            path.contains("/api/search") -> createSearchResponse(request.url.queryParameter("q") ?: "")
            path.contains("/api/notifications") -> createNotificationsResponse()
            else -> createErrorResponse()
        }

        return chain.proceed(request)
            .newBuilder()
            .code(200)
            .message("OK")
            .body(mockResponse.toResponseBody("application/json".toMediaType()))
            .build()
    }

    private fun createFeedResponse(): String {
        val tweets = listOf(
            Tweet(
                id = "1",
                author = User(
                    id = "user1",
                    name = "Juan Developer",
                    handle = "@juandev",
                    avatar = "https://i.pravatar.cc/150?img=1"
                ),
                content = "¡Acabo de crear un clon de Twitter en Android! 🚀",
                createdAt = "2h ago",
                likesCount = 234,
                retweetsCount = 45,
                repliesCount = 12
            ),
            Tweet(
                id = "2",
                author = User(
                    id = "user2",
                    name = "María Tech",
                    handle = "@mariatech",
                    avatar = "https://i.pravatar.cc/150?img=2"
                ),
                content = "Kotlin es increíble para desarrollo Android 💜",
                createdAt = "1h ago",
                likesCount = 567,
                retweetsCount = 234,
                repliesCount = 89
            ),
            Tweet(
                id = "3",
                author = User(
                    id = "user3",
                    name = "Carlos Mobile",
                    handle = "@carlossaez",
                    avatar = "https://i.pravatar.cc/150?img=3"
                ),
                content = "Jetpack Compose es el futuro del desarrollo de UI en Android",
                createdAt = "30m ago",
                likesCount = 890,
                retweetsCount = 456,
                repliesCount = 234
            )
        )

        val response = ApiResponse(
            success = true,
            data = tweets
        )

        return Gson().toJson(response)
    }

    private fun createTrendingResponse(): String {
        val tweets = listOf(
            Tweet(
                id = "trending1",
                author = User(
                    id = "user4",
                    name = "Tech News",
                    handle = "@technewstoday",
                    avatar = "https://i.pravatar.cc/150?img=4"
                ),
                content = "#AndroidDevelopment es trending en Twitter",
                createdAt = "15m ago",
                likesCount = 5000,
                retweetsCount = 2000,
                repliesCount = 500
            )
        )

        val response = ApiResponse(
            success = true,
            data = tweets
        )

        return Gson().toJson(response)
    }

    private fun createUserProfileResponse(): String {
        val user = User(
            id = "currentUser",
            name = "Tu Nombre",
            handle = "@tunombre",
            avatar = "https://i.pravatar.cc/150?img=33",
            bio = "Desarrollador Android | Kotlin | Jetpack Compose",
            followersCount = 1500,
            followingCount = 850,
            isFollowing = false
        )

        val response = ApiResponse(
            success = true,
            data = user
        )

        return Gson().toJson(response)
    }

    private fun createSearchResponse(query: String): String {
        val users = listOf(
            User(
                id = "search1",
                name = "Usuario Coincidente",
                handle = "@usuariobuscado",
                avatar = "https://i.pravatar.cc/150?img=5"
            )
        )

        val tweets = listOf(
            Tweet(
                id = "search_tweet1",
                author = User(
                    id = "user5",
                    name = "Resultado de búsqueda",
                    handle = "@resultado",
                    avatar = "https://i.pravatar.cc/150?img=6"
                ),
                content = "Tweet relacionado con '$query'",
                createdAt = "2h ago",
                likesCount = 100,
                retweetsCount = 50,
                repliesCount = 25
            )
        )

        val response = ApiResponse(
            success = true,
            data = SearchResult(users = users, tweets = tweets)
        )

        return Gson().toJson(response)
    }

    private fun createNotificationsResponse(): String {
        val notifications = listOf(
            mapOf(
                "id" to "notif1",
                "type" to "like",
                "user" to "Juan Developer",
                "message" to "le gustó tu tweet",
                "timestamp" to "5m ago"
            ),
            mapOf(
                "id" to "notif2",
                "type" to "follow",
                "user" to "María Tech",
                "message" to "empezó a seguirte",
                "timestamp" to "1h ago"
            )
        )

        val response = ApiResponse(
            success = true,
            data = notifications
        )

        return Gson().toJson(response)
    }

    private fun createErrorResponse(): String {
        val response = ApiResponse<Any>(
            success = false,
            message = "Endpoint no encontrado"
        )

        return Gson().toJson(response)
    }
}
