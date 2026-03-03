package com.example.twitterclone.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.twitterclone.models.Tweet

@Composable
fun TweetCard(
    tweet: Tweet,
    onLike: (Tweet) -> Unit = {},
    onReply: (Tweet) -> Unit = {},
    onRetweet: (Tweet) -> Unit = {},
    onClick: (Tweet) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(tweet) }
            .padding(16.dp)
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            // Avatar
            AsyncImage(
                model = tweet.author.avatar,
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.size(12.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                // Nombre y handle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = tweet.author.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )

                    Spacer(modifier = Modifier.size(4.dp))

                    Text(
                        text = tweet.author.handle,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.size(4.dp))

                    Text(
                        text = tweet.createdAt,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.size(4.dp))

                // Contenido
                Text(
                    text = tweet.content,
                    fontSize = 15.sp,
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.size(12.dp))

                // Acciones
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Responder
                    IconButton(
                        onClick = { onReply(tweet) },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Message,
                            contentDescription = "Responder",
                            tint = Color.Gray,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Text(
                        text = if (tweet.repliesCount > 0) tweet.repliesCount.toString() else "",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.size(24.dp))

                    // Retweet
                    IconButton(
                        onClick = { onRetweet(tweet) },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Repeat,
                            contentDescription = "Retweet",
                            tint = Color.Gray,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Text(
                        text = if (tweet.retweetsCount > 0) tweet.retweetsCount.toString() else "",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.size(24.dp))

                    // Like
                    IconButton(
                        onClick = { onLike(tweet) },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = if (tweet.isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = "Like",
                            tint = if (tweet.isLiked) Color.Red else Color.Gray,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Text(
                        text = if (tweet.likesCount > 0) tweet.likesCount.toString() else "",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun ComposeBar(
    onTweetClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            // Avatar temporal
            AsyncImage(
                model = "https://i.pravatar.cc/150?img=33",
                contentDescription = "Mi avatar",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.size(12.dp))

            Text(
                text = "¿Qué está pasando?",
                color = Color.Gray,
                fontSize = 20.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 8.dp)
            )
        }
    }
}
