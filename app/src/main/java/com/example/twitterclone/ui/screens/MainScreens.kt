package com.example.twitterclone.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.twitterclone.models.User
import com.example.twitterclone.ui.components.ComposePostBar
import com.example.twitterclone.ui.components.PostCard
import com.example.twitterclone.viewmodel.AuthViewModel
import com.example.twitterclone.viewmodel.FeedViewModel
import com.example.twitterclone.viewmodel.ProfileViewModel

@Composable
fun FeedScreen(
    feedViewModel: FeedViewModel,
    authViewModel: AuthViewModel,
    currentUser: User?
) {
    val feedState by feedViewModel.feedState.collectAsState()
    var showComposeDialog by remember { mutableStateOf(false) }
    var postContent by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(12.dp)
                ) {
                    Text(
                        text = "Home",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    
                    Divider(color = Color(0xFFEFF3F4), thickness = 1.dp)
                }
            }

            // Compose bar
            item {
                ComposePostBar(user = currentUser)
            }

            // Posts
            items(feedState.posts) { post ->
                PostCard(
                    post = post,
                    author = currentUser,
                    onLike = { feedViewModel.likePost(it) },
                    onUnlike = { feedViewModel.unlikePost(it) }
                )
            }

            // Empty state
            if (feedState.posts.isEmpty() && !feedState.isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No posts yet. Create one!", color = Color.Gray)
                    }
                }
            }
        }

        // Loading indicator
        if (feedState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Error message
        if (feedState.error != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Color.Red)
                    .padding(12.dp)
            ) {
                Text(
                    text = feedState.error ?: "",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }

        // Compose button
        FloatingActionButton(
            onClick = { showComposeDialog = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color(0xFF1DA1F2)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Compose", tint = Color.White)
        }
    }

    // Compose Dialog
    if (showComposeDialog) {
        ComposePostDialog(
            onPostClick = { content ->
                feedViewModel.createPost(content)
                showComposeDialog = false
                postContent = ""
            },
            onDismiss = { showComposeDialog = false },
            onContentChange = { postContent = it },
            content = postContent
        )
    }
}

@Composable
fun ExploreScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier
                    .height(64.dp)
                    .fillMaxWidth(),
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Explore feature coming soon", fontSize = 16.sp, color = Color.Gray)
        }
    }
}

@Composable
fun NotificationsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                modifier = Modifier
                    .height(64.dp)
                    .fillMaxWidth(),
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Notifications feature coming soon", fontSize = 16.sp, color = Color.Gray)
        }
    }
}

@Composable
fun MessagesScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Mail,
                contentDescription = null,
                modifier = Modifier
                    .height(64.dp)
                    .fillMaxWidth(),
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Messages feature coming soon", fontSize = 16.sp, color = Color.Gray)
        }
    }
}

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel,
    authViewModel: AuthViewModel,
    onLogoutClick: () -> Unit
) {
    val profileState by profileViewModel.profileState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (profileState.user != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                // Banner
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color(0xFF1DA1F2))
                )

                // Profile Info
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = profileState.user!!.displayName,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "@${profileState.user!!.username}",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = profileState.user!!.bio,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            authViewModel.logout()
                            onLogoutClick()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1DA1F2)
                        )
                    ) {
                        Text("Logout")
                    }
                }
            }
        } else if (profileState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun ComposePostDialog(
    onPostClick: (String) -> Unit,
    onDismiss: () -> Unit,
    onContentChange: (String) -> Unit,
    content: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Text("Compose Post", fontSize = 18.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = content,
                onValueChange = { onContentChange(it) },
                placeholder = { Text("What's happening?!") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Button(
                    onClick = {
                        if (content.isNotEmpty()) {
                            onPostClick(content)
                        }
                    },
                    modifier = Modifier.padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1DA1F2)
                    ),
                    enabled = content.isNotEmpty()
                ) {
                    Text("Post")
                }
            }
        }
    }
}
