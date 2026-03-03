# 🔍 REFERENCIA RÁPIDA DEL CÓDIGO

## 1. MainActivity - Punto de entrada

```kotlin
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
```

## 2. Navegación - Pestañas inferiores

```kotlin
@Composable
fun MainApp() {
    var selectedTab by remember { mutableIntStateOf(0) }
    
    Scaffold(bottomBar = { BottomNavigationBar(...) }) { innerPadding ->
        when (selectedTab) {
            0 -> HomeScreen(feedViewModel)
            1 -> ExploreScreen(exploreViewModel)
            2 -> NotificationsScreen(notificationsViewModel)
            3 -> ProfileScreen(profileViewModel)
        }
    }
}
```

## 3. Modelo de datos - Tweet

```kotlin
data class Tweet(
    val id: String,
    val author: User,
    val content: String,
    val image: String? = null,
    val createdAt: String,
    val likesCount: Int = 0,
    val isLiked: Boolean = false
)
```

## 4. API - Interfaz Retrofit

```kotlin
interface TwitterApi {
    @GET("/api/tweets/feed")
    suspend fun getFeed(): ApiResponse<List<Tweet>>
    
    @GET("/api/search")
    suspend fun search(@Query("q") query: String): ApiResponse<SearchResult>
}
```

## 5. Repository - Acceso a datos

```kotlin
class TwitterRepository {
    suspend fun getFeed(): List<Tweet> {
        return try {
            val response = api.getFeed()
            response.data ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
}
```

## 6. ViewModel - Lógica de UI

```kotlin
class FeedViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FeedUiState())
    val uiState: StateFlow<FeedUiState> = _uiState
    
    init {
        loadFeed()
    }
    
    private fun loadFeed() {
        viewModelScope.launch {
            val tweets = repository.getFeed()
            _uiState.value = FeedUiState(tweets = tweets)
        }
    }
}
```

## 7. Pantalla - HomeScreen

```kotlin
@Composable
fun HomeScreen(feedViewModel: FeedViewModel) {
    val uiState by feedViewModel.uiState.collectAsState()
    
    LazyColumn {
        items(uiState.tweets) { tweet ->
            TweetCard(
                tweet = tweet,
                onLike = { feedViewModel.likeTweet(it) }
            )
        }
    }
}
```

## 8. Componente - TweetCard

```kotlin
@Composable
fun TweetCard(tweet: Tweet, onLike: (Tweet) -> Unit) {
    Row(modifier = Modifier.padding(16.dp)) {
        AsyncImage(
            model = tweet.author.avatar,
            modifier = Modifier.size(48.dp).clip(CircleShape)
        )
        
        Column(modifier = Modifier.weight(1f)) {
            Text(tweet.author.name, fontWeight = FontWeight.Bold)
            Text(tweet.content)
            
            IconButton(onClick = { onLike(tweet) }) {
                Icon(
                    Icons.Filled.FavoriteBorder,
                    tint = if (tweet.isLiked) Color.Red else Color.Gray
                )
            }
        }
    }
}
```

## 9. Mock API - Datos de prueba

```kotlin
class MockInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val path = chain.request().url.encodedPath
        
        val mockResponse = when {
            path.contains("/api/tweets/feed") -> createFeedResponse()
            path.contains("/api/user/profile") -> createUserProfileResponse()
            else -> createErrorResponse()
        }
        
        return chain.proceed(chain.request())
            .newBuilder()
            .body(mockResponse.toResponseBody())
            .build()
    }
}
```

## 10. Tema - Material Design 3

```kotlin
@Composable
fun TwitterCloneTheme(content: @Composable () -> Unit) {
    val colors = lightColorScheme(
        primary = Color(0xFF1DA1F2),
        secondary = Color(0xFF17BF63),
        background = Color.White
    )
    
    MaterialTheme(colorScheme = colors, content = content)
}
```

## 📊 Flujo de datos

```
MainActivity
    ↓
MainApp (Scaffold + Navigation)
    ↓
HomeScreen (Composable)
    ↓
FeedViewModel (StateFlow)
    ↓
TwitterRepository (suspend fun)
    ↓
TwitterApi (Interface Retrofit)
    ↓
MockInterceptor (Simula datos)
    ↓
Modelos (Tweet, User, ApiResponse)
```

## 🔄 Lifeclycle de una pantalla

1. **Composable** se renderiza
2. **ViewModel** se inicializa
3. **Repository** obtiene datos
4. **API** retorna datos (mock)
5. **StateFlow** actualiza IU
6. **Pantalla** se recompone

## 🎯 Casos de uso comunes

### Obtener datos
```kotlin
val tweets = repository.getFeed()
```

### Actualizar UI
```kotlin
_uiState.value = _uiState.value.copy(
    tweets = newTweets,
    isLoading = false
)
```

### Manejar errores
```kotlin
try {
    val data = api.getData()
} catch (e: Exception) {
    _uiState.value = _uiState.value.copy(error = e.message)
}
```

### Operación asincrónica
```kotlin
viewModelScope.launch {
    val result = repository.operacionLarga()
    _uiState.value = _uiState.value.copy(data = result)
}
```

## 📦 Agregar nueva característica

1. **Modelo**: Crear clase en `models/`
2. **API**: Agregar endpoint en `TwitterApi`
3. **Repository**: Implementar método
4. **ViewModel**: Crear lógica
5. **UI**: Crear composable
6. **Navigation**: Agregar a MainActivity

---

**Referencia Rápida Completada ✅**
