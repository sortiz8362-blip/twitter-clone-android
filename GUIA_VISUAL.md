# 🎨 GUÍA VISUAL DEL PROYECTO

## 📱 Pantallas de la aplicación

```
┌─────────────────────────────────────────────────────────────┐
│                        TWITTER CLONE                         │
├─────────────────────────────────────────────────────────────┤
│                                                               │
│  ┌─────────────────────────────────────────────────────┐   │
│  │                   HOME SCREEN                       │   │
│  ├─────────────────────────────────────────────────────┤   │
│  │ ┌──────────────────────────────────────────────┐   │   │
│  │ │ "¿Qué está pasando?"                 [TWEET]│   │   │
│  │ └──────────────────────────────────────────────┘   │   │
│  │                                                     │   │
│  │ ┌──────────────────────────────────────────────┐   │   │
│  │ │ 👨 Juan Developer (@juandev)  hace 2h       │   │   │
│  │ │ ¡Acabo de crear un clon de Twitter!         │   │   │
│  │ │ 💬 12  🔄 45  ❤️ 234                         │   │   │
│  │ └──────────────────────────────────────────────┘   │   │
│  │                                                     │   │
│  │ ┌──────────────────────────────────────────────┐   │   │
│  │ │ 👩 María Tech (@mariatech)     hace 1h       │   │   │
│  │ │ Kotlin es increíble para Android             │   │   │
│  │ │ 💬 89  🔄 234  ❤️ 567                        │   │   │
│  │ └──────────────────────────────────────────────┘   │   │
│  │                                                     │   │
│  └─────────────────────────────────────────────────────┘   │
│                                                               │
├─────────────────────────────────────────────────────────────┤
│ [🏠 HOME] [🔍 EXPLORAR] [🔔 NOTIF] [👤 PERFIL]            │
└─────────────────────────────────────────────────────────────┘
```

## 🏗️ Arquitectura

```
┌─────────────────────────────────────────────────────────┐
│                      PRESENTACIÓN (UI)                   │
│  MainActivity.kt → HomeScreen → ExploreScreen → etc     │
├─────────────────────────────────────────────────────────┤
│         COMPONENTES (Jetpack Compose)                   │
│  TweetCard, ComposeBar, SearchBar, NavigationBar        │
├─────────────────────────────────────────────────────────┤
│         LÓGICA (ViewModel + StateFlow)                  │
│  FeedViewModel, ProfileViewModel, SearchViewModel       │
├─────────────────────────────────────────────────────────┤
│         DATOS (Repository + API)                        │
│  TwitterRepository → TwitterApi → MockInterceptor       │
├─────────────────────────────────────────────────────────┤
│         MODELOS                                         │
│  Tweet, User, Message, ApiResponse                      │
├─────────────────────────────────────────────────────────┤
│         NETWORKING (Retrofit + OkHttp)                  │
│  Retrofit, OkHttpClient, HttpLoggingInterceptor         │
└─────────────────────────────────────────────────────────┘
```

## 🔄 Flujo de datos

```
User interacción
    ↓
    ↓
MainActivity (Event)
    ↓
    ↓
ViewModel (fun onClick)
    ↓
    ↓
Repository (suspend fun)
    ↓
    ↓
TwitterApi (Retrofit)
    ↓
    ↓
MockInterceptor (Datos)
    ↓
    ↓
ApiResponse<T>
    ↓
    ↓
ViewModel (StateFlow)
    ↓
    ↓
Composable (recompose)
    ↓
    ↓
Screen actualizada ✅
```

## 📦 Dependencias principales

```
┌──────────────────────────────┐
│   JETPACK COMPOSE            │
│  (Material Design 3)         │
└──────────────────────────────┘
         ↑
         │
┌────────┴────────┬────────────┬─────────────┐
│                 │            │             │
▼                 ▼            ▼             ▼
LIFECYCLE    NAVIGATION    LIFECYCLE   VIEWMODEL
ViewModel    Compose       Library      Compose
```

```
┌──────────────────────────────┐
│      NETWORKING              │
│  (Retrofit + OkHttp)         │
└──────────────────────────────┘
         ↑
         │
    ┌────┴────┐
    │          │
    ▼          ▼
Retrofit    OkHttp
(HTTP)   (Logging)
```

```
┌──────────────────────────────┐
│     ASYNC & CONCURRENCY      │
│   (Kotlin Coroutines)        │
└──────────────────────────────┘
         ↑
         │
    ┌────┴────┐
    │          │
    ▼          ▼
launch()   Flow<T>
(Scope)    (StateFlow)
```

## 📁 Estructura de carpetas

```
clonx/
│
├── app/
│   ├── src/main/
│   │   ├── java/com/example/twitterclone/
│   │   │   │
│   │   │   ├── 📄 MainActivity.kt (punto de entrada)
│   │   │   │
│   │   │   ├── 🌐 api/
│   │   │   │   ├── TwitterApi.kt (interface)
│   │   │   │   ├── ApiClient.kt (setup)
│   │   │   │   └── MockInterceptor.kt (datos)
│   │   │   │
│   │   │   ├── 📊 models/
│   │   │   │   └── Models.kt (Tweet, User, etc)
│   │   │   │
│   │   │   ├── 💾 repository/
│   │   │   │   └── TwitterRepository.kt (repo)
│   │   │   │
│   │   │   ├── 🧠 viewmodel/
│   │   │   │   └── ViewModels.kt (lógica)
│   │   │   │
│   │   │   └── 🎨 ui/
│   │   │       ├── screens/
│   │   │       │   └── Screens.kt
│   │   │       ├── components/
│   │   │       │   └── TweetCard.kt
│   │   │       └── theme/
│   │   │           └── Theme.kt
│   │   │
│   │   └── res/values/
│   │       ├── strings.xml
│   │       ├── colors.xml
│   │       └── themes.xml
│   │
│   ├── build.gradle
│   └── AndroidManifest.xml
│
├── 📚 Documentación
│   ├── COMIENZA_AQUI.md
│   ├── README.md
│   ├── COMPILAR_APK.md
│   ├── EJEMPLOS.md
│   └── etc...
│
└── ⚙️ Config
    ├── build.gradle
    ├── settings.gradle
    ├── gradle.properties
    └── .gitignore
```

## 🎯 Ciclo de vida de un Tweet

```
1. OBTENCIÓN
   ┌──────────────────┐
   │  Repository      │
   │  getFeed()       │
   └────────┬─────────┘
            │
            ▼
   ┌──────────────────────────┐
   │  TwitterApi              │
   │  @GET /tweets/feed       │
   └────────┬─────────────────┘
            │
            ▼
   ┌──────────────────────────┐
   │  MockInterceptor         │
   │  Retorna lista de tweets │
   └────────┬─────────────────┘
            │
            ▼
   ┌──────────────────────────┐
   │  List<Tweet> creada      │
   └────────┬─────────────────┘
            │

2. ACTUALIZACIÓN DEL ESTADO
            │
            ▼
   ┌──────────────────────────────┐
   │  ViewModel.uiState           │
   │  _uiState.value = copy(...)  │
   └────────┬─────────────────────┘
            │

3. RENDERIZADO
            │
            ▼
   ┌──────────────────────────────┐
   │  Composable recompose        │
   │  HomeScreen()                │
   └────────┬─────────────────────┘
            │

4. RENDERIZADO FINAL
            │
            ▼
   ┌──────────────────────────────┐
   │  TweetCard mostrado en UI    │
   │  ✅ Visible en pantalla      │
   └──────────────────────────────┘
```

## 👆 Interacción - Like a Tweet

```
Usuario toca ❤️ en un tweet
        │
        ▼
TweetCard onLike callback
        │
        ▼
FeedViewModel.likeTweet(tweet)
        │
        ▼
viewModelScope.launch {
    Coroutine iniciada
    │
    ▼ (async)
    
    repository.likeTweet(id)
    │
    ▼
    
    TwitterApi.likeTweet(id)
    │
    ▼
    
    MockInterceptor retorna
    Tweet actualizado
    │
    ▼
    
    Lista actualizada
    │
    ▼
    
    _uiState.value = copy(...)
    │
    ▼
}
        │
        ▼
StateFlow emite nuevo estado
        │
        ▼
HomeScreen recompone
        │
        ▼
TweetCard se redibujan
        │
        ▼
❤️ Cambia de color (rojo)
Counter incrementa

✅ UI actualizada
```

## 🎨 Colores

```
┌──────────────────────────────────────┐
│  #1DA1F2 - Twitter Blue (Primario)   │ ← Botones, links
├──────────────────────────────────────┤
│  #17BF63 - Green (Secundario)        │ ← Acciones positivas
├──────────────────────────────────────┤
│  #E1245E - Red (Terciario)           │ ← Importante
├──────────────────────────────────────┤
│  #FFFFFF - White (Fondo)             │ ← Contenido
├──────────────────────────────────────┤
│  #000000 - Black (Texto)             │ ← Texto principal
├──────────────────────────────────────┤
│  #657786 - Gray (Secundario)         │ ← Texto secundario
├──────────────────────────────────────┤
│  #F7F9FA - Light Gray (Bkg)          │ ← Inputs
└──────────────────────────────────────┘
```

## 📊 Versiones

```
┌──────────────────────────────────┐
│  Kotlin:        1.9.10+          │
│  Compose:       2023.10+         │
│  Android API:   Min 24 (7.0)     │
│  Android API:   Target 34 (14)   │
│  Gradle:        8.1.2+           │
│  Retrofit:      2.9.0            │
│  OkHttp:        4.11.0           │
│  Coroutines:    1.7.3            │
│  Material3:     1.1.1            │
└──────────────────────────────────┘
```

## 🚀 Estado del proyecto

```
✅ Base MVVM implementada
✅ Jetpack Compose funcional
✅ Retrofit + MockAPI integrado
✅ 4 Pantallas principales
✅ Navegación funcionando
✅ StateFlow + ViewModel
✅ Material Design 3 aplicado
✅ Documentación completa

🔄 Próximo:
 - Conectar API real
 - Agregar autenticación
 - Implementar Room DB
 - Dark mode
 - Notificaciones push
```

---

**Guía Visual Completada ✅**
