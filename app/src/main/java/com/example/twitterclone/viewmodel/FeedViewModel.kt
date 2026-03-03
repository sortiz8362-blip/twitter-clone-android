package com.example.twitterclone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twitterclone.models.Post
import com.example.twitterclone.models.User
import com.example.twitterclone.repository.AuthRepository
import com.example.twitterclone.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class FeedUiState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class FeedViewModel : ViewModel() {
    private val postRepository = PostRepository()
    
    private val _feedState = MutableStateFlow(FeedUiState())
    val feedState: StateFlow<FeedUiState> = _feedState
    
    init {
        loadFeed()
    }
    
    fun loadFeed() {
        viewModelScope.launch {
            _feedState.value = _feedState.value.copy(isLoading = true, error = null)
            
            val result = postRepository.getFeed()
            result.onSuccess { posts ->
                _feedState.value = _feedState.value.copy(
                    posts = posts,
                    isLoading = false
                )
            }.onFailure { error ->
                _feedState.value = _feedState.value.copy(
                    isLoading = false,
                    error = error.message ?: "Failed to load feed"
                )
            }
        }
    }
    
    fun createPost(content: String) {
        viewModelScope.launch {
            val result = postRepository.createPost(content)
            result.onSuccess { newPost ->
                val currentPosts = _feedState.value.posts.toMutableList()
                currentPosts.add(0, newPost)
                _feedState.value = _feedState.value.copy(posts = currentPosts)
            }.onFailure { error ->
                _feedState.value = _feedState.value.copy(
                    error = error.message ?: "Failed to create post"
                )
            }
        }
    }
    
    fun deletePost(postId: String) {
        viewModelScope.launch {
            val result = postRepository.deletePost(postId)
            result.onSuccess {
                val updatedPosts = _feedState.value.posts.filter { it.id != postId }
                _feedState.value = _feedState.value.copy(posts = updatedPosts)
            }.onFailure { error ->
                _feedState.value = _feedState.value.copy(
                    error = error.message ?: "Failed to delete post"
                )
            }
        }
    }
    
    fun likePost(postId: String) {
        viewModelScope.launch {
            val result = postRepository.likePost(postId)
            result.onSuccess {
                val updatedPosts = _feedState.value.posts.map { post ->
                    if (post.id == postId) {
                        post.copy(
                            isLiked = true,
                            likeCount = post.likeCount + 1
                        )
                    } else post
                }
                _feedState.value = _feedState.value.copy(posts = updatedPosts)
            }.onFailure { error ->
                _feedState.value = _feedState.value.copy(
                    error = error.message ?: "Failed to like post"
                )
            }
        }
    }
    
    fun unlikePost(postId: String) {
        viewModelScope.launch {
            val result = postRepository.unlikePost(postId)
            result.onSuccess {
                val updatedPosts = _feedState.value.posts.map { post ->
                    if (post.id == postId) {
                        post.copy(
                            isLiked = false,
                            likeCount = post.likeCount - 1
                        )
                    } else post
                }
                _feedState.value = _feedState.value.copy(posts = updatedPosts)
            }.onFailure { error ->
                _feedState.value = _feedState.value.copy(
                    error = error.message ?: "Failed to unlike post"
                )
            }
        }
    }
    
    fun clearError() {
        _feedState.value = _feedState.value.copy(error = null)
    }
}

data class ProfileUiState(
    val user: User? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class ProfileViewModel : ViewModel() {
    private val authRepository = AuthRepository()
    
    private val _profileState = MutableStateFlow(ProfileUiState())
    val profileState: StateFlow<ProfileUiState> = _profileState
    
    init {
        loadProfile()
    }
    
    fun loadProfile() {
        viewModelScope.launch {
            _profileState.value = _profileState.value.copy(isLoading = true, error = null)
            
            val result = authRepository.getCurrentUser()
            result.onSuccess { user ->
                _profileState.value = _profileState.value.copy(
                    user = user,
                    isLoading = false
                )
            }.onFailure { error ->
                _profileState.value = _profileState.value.copy(
                    isLoading = false,
                    error = error.message ?: "Failed to load profile"
                )
            }
        }
    }
}
