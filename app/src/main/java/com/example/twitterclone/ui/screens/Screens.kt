package com.example.twitterclone.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.twitterclone.ui.components.ComposeBar
import com.example.twitterclone.ui.components.TweetCard
import com.example.twitterclone.viewmodel.ExploreViewModel
import com.example.twitterclone.viewmodel.FeedViewModel
import com.example.twitterclone.viewmodel.NotificationsViewModel
import com.example.twitterclone.viewmodel.ProfileViewModel

@Composable
fun HomeScreen(feedViewModel: FeedViewModel) {
    val uiState by feedViewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            item {
                ComposeBar()
                Divider(color = Color.LightGray, thickness = 1.dp)
            }

            items(uiState.tweets) { tweet ->
                TweetCard(
                    tweet = tweet,
                    onLike = { feedViewModel.likeTweet(it) }
                )
                Divider(color = Color.LightGray, thickness = 1.dp)
            }
        }

        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        if (uiState.error != null) {
            Text(
                text = "Error: ${uiState.error}",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun ExploreScreen(exploreViewModel: ExploreViewModel) {
    val uiState by exploreViewModel.uiState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            item {
                SearchBar(
                    query = searchQuery,
                    onQueryChange = { searchQuery = it },
                    onSearch = {},
                    active = false,
                    onActiveChange = {},
                    modifier = Modifier
                        .padding(16.dp)
                        .background(Color(0xFFF0F0F0)),
                    placeholder = { Text("Buscar en Twitter...") },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "Buscar")
                    }
                )
                Divider(color = Color.LightGray, thickness = 1.dp)

                Text(
                    text = "Tendencias para ti",
                    modifier = Modifier.padding(16.dp)
                )
            }

            items(uiState.trendingTweets) { tweet ->
                TweetCard(tweet = tweet)
                Divider(color = Color.LightGray, thickness = 1.dp)
            }
        }

        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun NotificationsScreen(notificationsViewModel: NotificationsViewModel) {
    val uiState by notificationsViewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            item {
                Text(
                    text = "Notificaciones",
                    modifier = Modifier.padding(16.dp)
                )
                Divider(color = Color.LightGray, thickness = 1.dp)
            }

            items(uiState.notifications) { notification ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "${notification["user"]} ${notification["message"]}",
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                }
                Divider(color = Color.LightGray, thickness = 1.dp)
            }
        }

        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel) {
    val uiState by profileViewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (uiState.user != null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(Color(0xFF1DA1F2))
                    )
                }

                item {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Perfil de ${uiState.user!!.name}")
                        Text(text = uiState.user!!.handle)
                        Text(text = uiState.user!!.bio ?: "Sin bio")
                        Text(text = "Seguidores: ${uiState.user!!.followersCount}")
                        Text(text = "Siguiendo: ${uiState.user!!.followingCount}")
                    }
                }
            }
        }

        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
