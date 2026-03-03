# Guía Rápida: Compilar APK

## 🚀 Pasos para generar el APK en Android Studio

### Opción 1: Desde la GUI de Android Studio (Recomendado)

1. **Abre Android Studio** y selecciona el proyecto `clonx`
2. **Espera a que sincronice** Gradle (puede tardar 2-5 minutos la primera vez)
3. **Ve a: Build → Build Bundle(s) / APK(s) → Build APK(s)**
4. **Espera a que termine la compilación**
5. Abrirá automáticamente la carpeta con el APK generado

### Opción 2: Desde Terminal

```bash
# En Windows (PowerShell o CMD)
cd C:\ProyectosGitHub\clonx
gradlew build

# O para debug APK más rápido:
gradlew assembleDebug
```

## 📂 Ubicación del APK

El APK se generará en:
```
app/build/outputs/apk/debug/app-debug.apk
```

## 📱 Instalar en dispositivo/emulador

Con dispositivo conectado o emulador levantado:

```bash
cd C:\ProyectosGitHub\clonx
adb install app/build/outputs/apk/debug/app-debug.apk
```

O desde Android Studio:
1. **Run → Run 'app'** (Ctrl + Shift + R)
2. Selecciona tu dispositivo/emulador
3. ¡Listo! Se instalará y ejecutará automáticamente

## 🔍 Solución de problemas

### Error: "Gradle no sincroniza"
- Abre `File → Invalidate Caches` y reinicia
- Ejecuta `gradlew clean` desde terminal

### Error: "SDK no encontrado"
- Ve a `File → Project Structure → SDK Location`
- Asegúrate de que apunta a tu Android SDK

### Error: "Emulador lento"
- Usa un dispositivo físico conectado
- O ajusta los parámetros del emulador

## 📊 Verificar el APK

Para verificar que el APK se generó correctamente:

```bash
adb shell pm list packages | grep twitterclone
```

## 🎯 Próximas características

El proyecto está listo para:
- ✅ Ejecutar en emulador
- ✅ Instalar en dispositivo
- 🔄 Conectar API real (reemplaza MockInterceptor)
- 🔄 Añadir autenticación
- 🔄 Base de datos local con Room

¡Diviértete desarrollando! 🚀
