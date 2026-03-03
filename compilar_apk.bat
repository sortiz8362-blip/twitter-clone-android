@echo off
REM Script para compilar APK automaticamente
REM Para el Twitter Clone - Kotlin + Appwrite

cls
echo.
echo ========================================
echo   COMPILAR APK - TWITTER CLONE
echo ========================================
echo.

REM Verificar si está en la carpeta correcta
if not exist "gradlew.bat" (
    echo [ERROR] gradlew.bat no encontrado
    echo Debes estar en: C:\ProyectosGitHub\clonx
    echo.
    pause
    exit /b 1
)

REM Verificar Java
java -version >nul 2>nul
if %ErrorLevel% NEQ 0 (
    echo [ERROR] Java no está instalado
    echo Descarga: https://www.oracle.com/java/technologies/javase-jdk11-downloads.html
    echo.
    pause
    exit /b 1
)

echo [OK] Todos los requisitos están listos
echo.
echo Compilando APK Debug...
echo (Esto puede tomar 5-10 minutos en la primera vez)
echo.

REM Limpiar build anterior (opcional)
echo Limpiando build anterior...
call gradlew.bat clean

REM Compilar APK Debug
echo.
echo Compilando...
call gradlew.bat assembleDebug

REM Verificar si fue exitoso
if %ErrorLevel% EQU 0 (
    echo.
    echo ========================================
    echo [SUCCESS] APK COMPILADO EXITOSAMENTE
    echo ========================================
    echo.
    echo APK ubicado en:
    echo C:\ProyectosGitHub\clonx\app\build\outputs\apk\debug\app-debug.apk
    echo.
    echo Próximos pasos:
    echo 1. Conecta tu teléfono por USB
    echo 2. Ejecuta en terminal: adb install app\build\outputs\apk\debug\app-debug.apk
    echo 3. O copia el APK a Google Drive y descárgalo en tu teléfono
    echo.
) else (
    echo.
    echo ========================================
    echo [ERROR] Fallo en la compilación
    echo ========================================
    echo.
    echo Posibles soluciones:
    echo 1. Verifica que Java esté correctamente instalado
    echo 2. Intenta: gradlew.bat clean
    echo 3. Revisa los errores anteriores
    echo.
)

pause
