package com.example.twitterclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.twitterclone.ui.screens.ExploreScreen
import com.example.twitterclone.ui.screens.FeedScreen
import com.example.twitterclone.ui.screens.LoginScreen
import com.example.twitterclone.ui.screens.MessagesScreen
import com.example.twitterclone.ui.screens.NotificationsScreen
import com.example.twitterclone.ui.screens.ProfileScreen
import com.example.twitterclone.ui.screens.RegisterScreen
import com.example.twitterclone.ui.theme.TwitterCloneTheme
import com.example.twitterclone.viewmodel.AuthViewModel
import com.example.twitterclone.viewmodel.FeedViewModel
import com.example.twitterclone.viewmodel.ProfileViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TwitterCloneTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val authViewModel = remember { AuthViewModel() }
    val authState by authViewModel.authState.collectAsState()

    if (authState.isLoggedIn && authState.currentUser != null) {
        // Usuario autenticado - mostrar app principal
        AuthenticatedApp(authViewModel)
    } else {
        // Usuario no autenticado - mostrar login/register
        AuthenticationFlow(authViewModel)
    }
}

@Composable
fun AuthenticationFlow(authViewModel: AuthViewModel) {
    var isLogin by remember { mutableIntStateOf(0) } // 0 = login, 1 = register

    if (isLogin == 0) {
        LoginScreen(
            authViewModel = authViewModel,
            onLoginSuccess = { isLogin = -1 } // -1 = success
        )
        // Link a register
        Text(
            text = "Create Account",
            modifier = Modifier.padding(start = 16.dp),
            color = Color(0xFF1DA1F2)
        )
    } else {
        RegisterScreen(
            authViewModel = authViewModel,
            onRegisterSuccess = { isLogin = -1 }, // -1 = success
            onBackToLogin = { isLogin = 0 }
        )
    }
}

@Composable
fun AuthenticatedApp(authViewModel: AuthViewModel) {
    val authState by authViewModel.authState.collectAsState()
    val feedViewModel = remember { FeedViewModel() }
    val profileViewModel = remember { ProfileViewModel() }
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { innerPadding ->
        when (selectedTab) {
            0 -> FeedScreen(
                feedViewModel = feedViewModel,
                authViewModel = authViewModel,
                currentUser = authState.currentUser,
                modifier = Modifier.padding(innerPadding)
            )
            1 -> ExploreScreen()
            2 -> NotificationsScreen()
            3 -> MessagesScreen()
            4 -> ProfileScreen(
                profileViewModel = profileViewModel,
                authViewModel = authViewModel,
                onLogoutClick = { selectedTab = 0 },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun FeedScreen(
    feedViewModel: FeedViewModel,
    authViewModel: AuthViewModel,
    currentUser: com.example.twitterclone.models.User?,
    modifier: Modifier = Modifier
) {
    com.example.twitterclone.ui.screens.FeedScreen(
        feedViewModel = feedViewModel,
        authViewModel = authViewModel,
        currentUser = currentUser
    )
}

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel,
    authViewModel: AuthViewModel,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    com.example.twitterclone.ui.screens.ProfileScreen(
        profileViewModel = profileViewModel,
        authViewModel = authViewModel,
        onLogoutClick = onLogoutClick
    )
}

@Composable
fun BottomNavigationBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    NavigationBar(
        containerColor = Color.White,
        contentColor = Color(0xFF1DA1F2)
    ) {
        val tabs = listOf(
            NavigationItem(
                icon = Icons.Default.Home,
                label = "Home",
                contentDescription = "Home"
            ),
            NavigationItem(
                icon = Icons.Default.Search,
                label = "Explore",
                contentDescription = "Explore"
            ),
            NavigationItem(
                icon = Icons.Default.Notifications,
                label = "Notifications",
                contentDescription = "Notifications"
            ),
            NavigationItem(
                icon = Icons.Default.Mail,
                label = "Messages",
                contentDescription = "Messages"
            ),
            NavigationItem(
                icon = Icons.Default.Person,
                label = "Profile",
                contentDescription = "Profile"
            )
        )

        tabs.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.contentDescription,
                        tint = if (selectedTab == index) Color(0xFF1DA1F2) else Color.Gray
                    )
                },
                label = { Text(item.label) },
                selected = selectedTab == index,
                onClick = { onTabSelected(index) }
            )
        }
    }
}

data class NavigationItem(
    val icon: ImageVector,
    val label: String,
    val contentDescription: String
)

