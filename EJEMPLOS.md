# 📚 Ejemplos de Extensión del Proyecto

## Ejemplo 1: Agregar una pantalla "Mensajes"

### Paso 1: Crear el modelo
En `models/Models.kt`, añade:

```kotlin
data class Message(
    val id: String,
    val sender: User,
    val receiver: User,
    val content: String,
    val timestamp: String,
    val isRead: Boolean = false
)
```

### Paso 2: Agregar a la API
En `api/TwitterApi.kt`:

```kotlin
@GET("/api/messages")
suspend fun getMessages(): ApiResponse<List<Message>>

@POST("/api/messages/send")
suspend fun sendMessage(@Body message: Map<String, String>): ApiResponse<Message>
```

### Paso 3: Agregar al repositorio
En `repository/TwitterRepository.kt`:

```kotlin
suspend fun getMessages(): List<Message> {
    return try {
        val response = api.getMessages()
        response.data ?: emptyList()
    } catch (e: Exception) {
        emptyList()
    }
}
```

### Paso 4: Crear ViewModel
En `viewmodel/ViewModels.kt`:

```kotlin
data class MessagesUiState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false
)

class MessagesViewModel : ViewModel() {
    private val repository = TwitterRepository()
    private val _uiState = MutableStateFlow(MessagesUiState())
    val uiState: StateFlow<MessagesUiState> = _uiState

    init {
        loadMessages()
    }

    private fun loadMessages() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val messages = repository.getMessages()
            _uiState.value = _uiState.value.copy(
                messages = messages,
                isLoading = false
            )
        }
    }
}
```

### Paso 5: Crear la pantalla
En `ui/screens/Screens.kt`:

```kotlin
@Composable
fun MessagesScreen(messagesViewModel: MessagesViewModel) {
    val uiState by messagesViewModel.uiState.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(uiState.messages) { message ->
            MessageCard(message)
            Divider()
        }
    }
}
```

### Paso 6: Agregar componente
En `ui/components/TweetCard.kt`:

```kotlin
@Composable
fun MessageCard(message: Message) {
    Row(modifier = Modifier.padding(16.dp)) {
        AsyncImage(
            model = message.sender.avatar,
            contentDescription = null,
            modifier = Modifier.size(48.dp).clip(CircleShape)
        )
        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(message.sender.name, fontWeight = FontWeight.Bold)
            Text(message.content)
            Text(message.timestamp, fontSize = 12.sp, color = Color.Gray)
        }
    }
}
```

### Paso 7: Actualizar MainActivity
En `MainActivity.kt`:

```kotlin
val messagesViewModel = remember { MessagesViewModel() }

// En el Scaffold, agregar:
NavigationItem(
    icon = Icons.Filled.Mail,
    label = "Mensajes",
    contentDescription = "Mensajes"
)

// En el when:
3 -> MessagesScreen(messagesViewModel)
```

## Ejemplo 2: Conectar una API Real

### Cambiar MockInterceptor por API real

En `api/ApiClient.kt`:

```kotlin
object ApiClient {
    private const val BASE_URL = "https://api.twitter.com/2/"  // Tu URL real

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        // .addInterceptor(MockInterceptor())  // Comentar esta línea
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer YOUR_TOKEN")  // Agregar token
                .build()
            chain.proceed(request)
        }
        .build()
    
    // Resto igual...
}
```

## Ejemplo 3: Base de datos local con Room

### Paso 1: Agregar dependencias en `build.gradle`

```gradle
implementation 'androidx.room:room-runtime:2.5.2'
implementation 'androidx.room:room-ktx:2.5.2'
kapt 'androidx.room:room-compiler:2.5.2'
```

### Paso 2: Crear entity

```kotlin
@Entity(tableName = "tweets")
data class TweetEntity(
    @PrimaryKey val id: String,
    val authorName: String,
    val content: String,
    val createdAt: String,
    val likesCount: Int
)
```

### Paso 3: Crear DAO

```kotlin
@Dao
interface TweetDao {
    @Query("SELECT * FROM tweets ORDER BY createdAt DESC")
    fun getAllTweets(): Flow<List<TweetEntity>>

    @Insert
    suspend fun insertTweet(tweet: TweetEntity)

    @Delete
    suspend fun deleteTweet(tweet: TweetEntity)
}
```

### Paso 4: Crear Base de datos

```kotlin
@Database(entities = [TweetEntity::class], version = 1)
abstract class TwitterDatabase : RoomDatabase() {
    abstract fun tweetDao(): TweetDao

    companion object {
        fun getDatabase(context: Context): TwitterDatabase {
            return Room.databaseBuilder(
                context,
                TwitterDatabase::class.java,
                "twitter_db"
            ).build()
        }
    }
}
```

## Ejemplo 4: Dark Mode

En `ui/theme/Theme.kt`:

```kotlin
private val DarkColors = darkColorScheme(
    primary = Color(0xFF1DA1F2),
    secondary = Color(0xFF17BF63),
    background = Color(0xFF000000),
    surface = Color(0xFF15202B)
)

@Composable
fun TwitterCloneTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    
    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}
```

## Ejemplo 5: Obtener permisos de cámara

En `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```

En una Composable:

```kotlin
val context = LocalContext.current
val launcher = rememberLauncherForActivityResult(
    ActivityResultContracts.RequestPermission()
) { isGranted ->
    if (isGranted) {
        // Permiso concedido
    }
}

Button(onClick = {
    launcher.launch(Manifest.permission.CAMERA)
}) {
    Text("Abrir cámara")
}
```

## Recursos útiles

- [Documentación oficial de Android](https://developer.android.com)
- [Jetpack Compose](https://developer.android.com/develop/ui/compose)
- [Retrofit](https://square.github.io/retrofit/)
- [Room Database](https://developer.android.com/training/data-storage/room)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)

---

¡Experimenta y diviértete desarrollando! 🚀
