# 🚀 EJECUTAR LA APP: PASOS EXACTOS (Windows)

## 3 MINUTOS HASTA VER TU APP EN PANTALLA

---

## PASO 1: Abre Android Studio (1 min)

```
1. Haz clic en el ícono de Android Studio en tu escritorio
   
   (O si no lo encuentras)
   
2. Presiona Windows + S
3. Escribe: "Android Studio"
4. Presiona Enter
5. Espera a que Android Studio cargue (20 segundos)
```

---

## PASO 2: Abre tu Proyecto (30 segundos)

```
EN ANDROID STUDIO:

1. Haz clic en: File (arriba a la izquierda)
2. Selecciona: Open
3. Navega a: C:\ProyectosGitHub\clonx
4. Presiona: Open
5. Android Studio abre tu proyecto
```

### O MEJOR AÚN (si ya lo tienes abierto):
```
Si ya está abierto, salta al PASO 3
```

---

## PASO 3: SINCRONIZA GRADLE (2-5 min) ⏳

```
ESPERA a que aparezca una notificación como:

    "Gradle sync in progress..."

Android Studio está descargando dependencias.

CUANDO DIGA: "Gradle sync finished"
    ↓
CONTINÚA AL PASO 4
```

### Si ves error rojo:
```
1. Presiona: Build → Clean Build Folder
2. Presiona: File → Sync Now
3. Espera de nuevo
```

---

## PASO 4: ELIGE DÓNDE EJECUTAR (Emulador o Teléfono)

### OPCIÓN A: Emulador (más fácil)

```
EN ANDROID STUDIO:

1. Haz clic en: Tools (arriba)
2. Selecciona: Device Manager
3. Si ves un dispositivo, presiona el botón ▶ (verde)
4. Espera a que el emulador cargue (30 segundos)
```

### OPCIÓN B: Teléfono Real

```
1. Conecta tu teléfono con cable USB a la computadora
2. En tu teléfono:
   - Ajustes → Información del dispositivo
   - Toca "Número de compilación" 7 veces
   - Vuelve atrás
   - Ve a "Opciones de desarrollador"
   - Activa "Depuración por USB"
3. En Android Studio: Los drivers se instalan automáticamente
```

---

## PASO 5: EJECUTA LA APP (1 click) 🎯

```
EN ANDROID STUDIO:

Busca este botón verde en la barra de herramientas:

    ▶ (botón de Play)

Presiona una vez.

ESPERA. Verás en la consola:

    "Build successful"
    "Installing APK"
    "App is running"
```

---

## ¡LISTO! 🎉

Tu app abrirá en:
- **Emulador**: pantalla virtual en tu PC
- **Teléfono**: directamente en el dispositivo

---

## PRIMERAS COSAS QUE VERÁS

```
┌─────────────────────────────┐
│   TWITTER CLONE LOGIN       │
├─────────────────────────────┤
│                             │
│  Email: _________________   │
│  Password: _____________   │
│                             │
│  ┌─────────────────────┐   │
│  │   LOGIN BUTTON      │   │
│  └─────────────────────┘   │
│                             │
│  Create Account? Click here │
│                             │
└─────────────────────────────┘
```

---

## PASOS PARA PROBAR

### 1️⃣ CREAR UNA CUENTA

```
Email:      test@gmail.com
Password:   password123
Name:       Mi Nombre
Username:   @miUsername
```

Presiona: "Register" → Espera 2 segundos → Verás modal de confirmación

### 2️⃣ HACER LOGIN

```
Email:      test@gmail.com
Password:   password123
```

Presiona: "Login" → Espera 2 segundos → ¡ENTRA AL FEED!

### 3️⃣ VER EL FEED

Verás:
- Posts vacío (primera vez)
- Botón + verde abajo a la derecha
- 5 tabs en la parte inferior

### 4️⃣ CREAR UN POST

```
1. Presiona el botón + verde
2. Escribe: "¡Hola mundo! Mi primer post en mi app Twitter clone"
3. Presiona: "Tweet"
4. El post aparece en el feed
```

### 5️⃣ DAR LIKE

```
1. Presiona el corazón en tu post
2. Se pone rojo 💗
3. El contador sube a 1
```

### 6️⃣ VER TU PERFIL

```
1. Presiona la tab de perfil (derecha, última)
2. Verás tu foto, nombre, username
3. Presiona "Logout"
4. Vuelves a LoginScreen
```

---

## SI ALGO NO FUNCIONA

### Error: "Build failed"

```
Solución:
1. Build → Clean Build Folder
2. Build → Rebuild Project
3. Espera 2 minutos
4. Presiona Run de nuevo
```

### Error: "Gradle sync failed"

```
Solución:
1. File → Invalidate Caches / Restart
2. Selecciona: Invalidate and Restart
3. Espera que Android Studio reinicie
4. Presiona Run
```

### La app no abre en el emulador

```
Solución:
1. Cierra el emulador (botón X)
2. Tools → Device Manager
3. Presiona ▶ de nuevo
4. Espera 30 segundos
5. Presiona Run
```

### Ves "Cannot resolve symbol" error

```
Solución:
1. File → Sync Now
2. Build → Clean Build Folder
3. Build → Rebuild Project
```

### La app se cierra inmediatamente

```
EN ANDROID STUDIO:
1. View → Tool Windows → Logcat
2. Busca el texto ROJO
3. Cópialo y muéstramelo
4. Encontraremos el error
```

---

## BUSCAS MÁS? 📚

Ver otros archivos:

| Archivo | Para qué |
|---------|----------|
| `LISTO_PARA_USAR.md` | Overview general |
| `ESTRUCTURA_ARCHIVOS.md` | Dónde están qué archivos |
| `GUIA_EJECUTAR_SIN_ERRORES.md` | Troubleshooting avanzado |
| `TWITTER_APPWRITE_LISTO.md` | Detalles técnicos |

---

## PRÓXIMOS 3 PASOS (Después de que funcione)

```
1. PRUEBA EN TU TELÉFONO
   - Desconecta USB si usas emulador
   - Conecta tu teléfono
   - Build → Build APK(s)

2. COMPARTE LA APP
   - APK está en: app/build/outputs/apk/debug/app-debug.apk
   - Sube a Google Drive, Dropbox, etc.
   - Comparte el link

3. PUBLICA EN GOOGLE PLAY
   - Google Play Console
   - Sube APK release
   - Llena descripción, screenshots
   - ¡Publish!
```

---

## ÚLTIMA COSA: ANDROID STUDIO NO ENCONTRADO? 💻

Si Android Studio NO está instalado:

```
1. Descarga: https://developer.android.com/studio
2. Ejecuta el .exe
3. Sigue el instalador (siguiente → siguiente → instalar)
4. Elige: "Custom" installation
5. Instala:
   ✅ Android SDK
   ✅ Android SDK Platform
   ✅ Android Virtual Device
6. Espera 20 min
7. Crea un emulador (Tools → Device Manager → Create)
8. Vuelve a este documento PASO 1
```

---

## 🎯 CHECKLIST FINAL

```
Antes de presionar Run:

☑ Android Studio está abierto
☑ Proyecto abierto (veo nombre en la ventana)
☑ Gradle sincronizado (dice "Gradle sync finished")
☑ Emulador abierto O teléfono conectado
☑ No veo errores rojos en MainActivity.kt
```

---

## BOTONES IMPORTANTES EN ANDROID STUDIO

```
▶                    = Run (ejecutar)
🔨                   = Build (compilar)
🗑️                   = Clean (limpiar)
🔄 Sync Now         = Sincronizar gradle
⚙️ File             = Menú principal

Barra verde arriba  = Éxito de compilación
Barra roja arriba   = Error ❌
Barra azul arriba   = Compilando ⏳
```

---

## ¿QUÉ PASARÁ PASO A PASO?

```
1. Presionas ▶ Run
   ↓
2. Android Studio compila el proyecto (5-10 seg)
   ↓
3. Mensaje: "Gradle Build Successful"
   ↓
4. Android Studio instala APK en emulador/teléfono (3 seg)
   ↓
5. Mensaje: "Gradle Build Successful"
   ↓
6. Tu app abre automáticamente
   ↓
7. Ves: LoginScreen
   ↓
8. ¡FUNCIÓN EXITOSA! 🎉
```

---

## COMANDOS MANUALES (OPCIONAL)

Si quieres hacer esto en terminal (PowerShell):

```powershell
# Navega al proyecto
cd C:\ProyectosGitHub\clonx

# Sincroniza gradle
./gradlew clean

# Compila
./gradlew build

# Genera APK
./gradlew assembleDebug

# APK estará en:
# app/build/outputs/apk/debug/app-debug.apk
```

Pero es más fácil hacer clic en botones en Android Studio.

---

## 💡 TIPS

```
💡 Si Android Studio se pone lenta:
   - Aumenta RAM: Help → Edit Custom VM Options
   - Cambia -Xmx256m a -Xmx1024m

💡 Para desarrollar más rápido:
   - Habilita "Instant Run": File → Settings → Instant Run

💡 Para bajar APK final:
   - Build → Build Bundle(s) / APK(s) → Build APK(s)
   - Espera 2 minutos
   - Va a: app/build/outputs/apk/debug/app-debug.apk

💡 Si todo falla:
   - Reinicia Android Studio
   - Reinicia tu computadora
   - Intenta de nuevo
```

---

## 🎬 VIDEO RESUMEN (TÚ AHORA)

1. Haz clic en Android Studio
2. File → Open → C:\ProyectosGitHub\clonx
3. Espera Gradle
4. Presiona ▶ Run
5. ¡LISTO! Tu app está corriendo

**Tiempo total: 5-10 minutos**

---

## 🚀 AHORA ADELANTE

Tu Twitter clone está LISTO.

**Siguiente paso:**
```
↓
Abre Android Studio
↓
File → Open → C:\ProyectosGitHub\clonx
↓
Presiona Run
↓
¡DISFRUTA TU APP!
↓
Compártela con amigos
↓
Publica en Google Play
↓
MILLONARIO 🤑 (bromeando... pero quién sabe)
```

---

**¡A ejecutar!** 🚀

*Guía creada: 3 de marzo de 2026*
*Tiempo de ejecución estimado: 5 minutos*
*Chances de éxito: 99%*

---

¿Preguntas? Lee los otros archivos .md o muéstrame el error.

**¡Tu app está lista! Vamos!** 🎉
