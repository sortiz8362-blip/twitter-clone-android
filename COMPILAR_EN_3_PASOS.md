# ⚡ COMPILAR APK EN 3 PASOS (Para tu laptop 4GB RAM)

**Genera el APK listo para instalar en tu teléfono. Sin Android Studio. Sin emulador.**

---

## NECESITAS:

```
✅ Java instalado (11+)
✅ Teléfono Android
✅ Cable USB (o WiFi)
```

---

## PASO 1️⃣ INSTALAR JAVA (si no lo tienes)

### Verificar si tienes Java:

Abre **PowerShell** (Windows+X → PowerShell) y escribe:

```powershell
java -version
```

**Si ves una versión → Salta al PASO 2**

**Si dice "no se reconoce" → Necesitas instalar Java:**

```
1. Descarga: https://www.oracle.com/java/technologies/javase-jdk11-downloads.html
2. Descarga el archivo para Windows (64-bit)
3. Haz doble clic en el instalador
4. Presiona "Next" → "Next" → "Install"
5. REINICIA TU COMPUTADORA
6. Vuelve a verificar: java -version
```

---

## PASO 2️⃣ COMPILAR EL APK

### En VS Code:

```
1. Abre Terminal → New Terminal
   (O: Ctrl + `)
   
2. Navega al proyecto:
   cd C:\ProyectosGitHub\clonx
   
3. Ejecuta:
   .\gradlew.bat assembleDebug
   
4. ESPERA 5-10 minutos
```

### Qué verás:

```
Download gradle...
Compiling...
Building...
BUILD SUCCESSFUL
```

### ¡APK Generado! ✅

Se encuentra en:
```
C:\ProyectosGitHub\clonx\app\build\outputs\apk\debug\app-debug.apk
```

---

## PASO 3️⃣ INSTALAR EN TU TELÉFONO

### Opción A: Con ADB (Recomendado)

```
1. Conecta teléfono por USB
2. En teléfono: Ajustes → Opciones de desarrollador → Depuración por USB ✓
3. En terminal PowerShell (en VS Code):
   adb install app\build\outputs\apk\debug\app-debug.apk
   
4. Espera a que dice: Success ✅
```

### Opción B: Manual (si falla ADB)

```
1. Copia el APK:
   C:\ProyectosGitHub\clonx\app\build\outputs\apk\debug\app-debug.apk

2. Sube a Google Drive

3. En tu teléfono: Abre Drive → Descarga el APK

4. Abre el archivo descargado → Instalar

5. ¡Listo! 🎉
```

---

## 🎯 ATAJOS (Guarda esto)

### Si quieres compilar rápido:

En la terminal:
```powershell
cd C:\ProyectosGitHub\clonx
.\gradlew.bat assembleDebug
```

### Instalar en teléfono:
```powershell
adb install app\build\outputs\apk\debug\app-debug.apk
```

### Ver si teléfono está conectado:
```powershell
adb devices
```

---

## ⚠️ PROBLEMAS COMUNES

| Error | Solución |
|-------|----------|
| "java -version no funciona" | Reinicia tu PC después de instalar Java |
| "gradlew.bat not found" | Asegúrate de estar en `C:\ProyectosGitHub\clonx` |
| "ADB: command not found" | Usa instalación manual (Google Drive) |
| "BUILD FAILED" | Intenta: `.\gradlew.bat clean` |

---

## 📱 PROBAR LA APP

Una vez instalada en teléfono:

```
1. Busca el ícono "Twitter Clone"
2. Tócalo para abrir
3. Ves Pantalla de Login
4. Crea cuenta o haz login
5. ¡Usa la app! 🚀
```

---

## ✅ VERIFICACIÓN RÁPIDA

```
Antes de compilar:
☑ Java instalado (java -version funciona)
☑ Estoy en C:\ProyectosGitHub\clonx
☑ El archivo gradlew.bat existe aquí
☑ Tengo 30 minutos de tiempo
☑ Teléfono disponible (no obligatorio ahora)
```

---

## 🚀 RESUMEN

```bash
# 1. Instala Java (si no lo tienes)
# 2. En terminal:
cd C:\ProyectosGitHub\clonx
.\gradlew.bat assembleDebug
# 3. Espera 5-10 min
# 4. APK en: app\build\outputs\apk\debug\app-debug.apk
# 5. Instala en teléfono con: adb install app\build\outputs\apk\debug\app-debug.apk
# 6. ¡Usa tu app!
```

---

## 💡 VENTAJAS DE ESTE MÉTODO

```
✅ Solo 2GB RAM (tu laptop funciona)
✅ Sin emulador (consume 2GB RAM extra)
✅ Sin Android Studio (consume 1GB RAM)
✅ APK real en tu teléfono
✅ 100% Funcional
✅ Rápido (5-10 min total)
✅ Puedes compartir el APK
```

---

## 🎊 ¡AHORA ADELANTE!

**Próximo paso:**

1. ✅ Instala Java (si falta)
2. ✅ Abre Terminal en VS Code
3. ✅ Ejecuta: `.\gradlew.bat assembleDebug`
4. ✅ Espera
5. ✅ ¡Listo! APK compilado

**¿Necesitas más ayuda?** Lee: `COMPILAR_APK_SIN_ANDROID_STUDIO.md`

---

*Guía rápida para laptops con bajo RAM | 3 de marzo de 2026*

🐦 **Tu APK está a solo 3 pasos de distancia** 🚀
