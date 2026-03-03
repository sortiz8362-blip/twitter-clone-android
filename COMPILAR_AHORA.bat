@echo off
setlocal enabledelayedexpansion

cls
echo ========================================
echo COMPILADOR APK - TWITTER CLONE
echo ========================================
echo.

REM Buscar Java instalada
echo Buscando Java...
for /f "tokens=*" %%A in ('where java 2^>nul') do (
    set "JAVA_PATH=%%A"
    goto java_found
)

REM Si no encuentra en PATH, buscar en Program Files
if not defined JAVA_PATH (
    if exist "C:\Program Files\Amazon Corretto\jdk11.0.30_7\bin\java.exe" (
        set "JAVA_PATH=C:\Program Files\Amazon Corretto\jdk11.0.30_7\bin\java.exe"
        set "JAVA_HOME=C:\Program Files\Amazon Corretto\jdk11.0.30_7"
        goto java_found
    )
)

if not defined JAVA_PATH (
    echo [ERROR] Java no encontrado
    echo Por favor instala Java 11+ desde:
    echo https://www.oracle.com/java/technologies/javase-jdk11-downloads.html
    pause
    exit /b 1
)

:java_found
echo [OK] Java encontrado
echo.

REM Verificar gradlew
if not exist "gradlew.bat" (
    echo [ERROR] gradlew.bat no encontrado
    echo Debe estar en: C:\ProyectosGitHub\clonx\gradlew.bat
    pause
    exit /b 1
)

echo [OK] Gradle Wrapper encontrado
echo.

REM Compilar
echo ========================================
echo Compilando APK...
echo ========================================
echo.

call gradlew clean
call gradlew assembleDebug

if %errorlevel% equ 0 (
    echo.
    echo ========================================
    echo [EXITO] APK compilado
    echo ========================================
    echo.
    echo Ubicacion: %cd%\app\build\outputs\apk\debug\app-debug.apk
    echo.
    echo Proximos pasos:
    echo 1. Conecta tu telefono por USB
    echo 2. Ejecuta: adb install app\build\outputs\apk\debug\app-debug.apk
    echo.
) else (
    echo.
    echo ========================================
    echo [ERROR] Fallo en compilacion
    echo ========================================
)

pause
