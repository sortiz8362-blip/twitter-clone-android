@echo off
setlocal enabledelayedexpansion

cd /d C:\ProyectosGitHub\clonx

echo =========================================
echo COMPILADOR DE APK - TWITTER CLONE
echo =========================================
echo.

REM Configurar Java
set JAVA_HOME=C:\ProyectosGitHub\clonx\jdk-11.0.21+9
set PATH=!JAVA_HOME!\bin;!PATH!

REM Configurar Android SDK
set ANDROID_HOME=C:\ProyectosGitHub\clonx\android-sdk
set ANDROID_SDK_ROOT=C:\ProyectosGitHub\clonx\android-sdk

echo Verificando Java...
java -version
if errorlevel 1 goto ERROR_JAVA

echo.
echo Verificando Gradle...
gradle-8.4\bin\gradle.bat --version
if errorlevel 1 goto ERROR_GRADLE

echo.
echo =========================================
echo Compilando APK...
echo =========================================
echo.

gradle-8.4\bin\gradle.bat clean assembleDebug

if errorlevel 1 goto ERROR_BUILD

echo.
echo =========================================
echo COMPILACION EXITOSA!
echo =========================================
echo.
echo APK generado en:
echo app\build\outputs\apk\debug\app-debug.apk
echo.
dir app\build\outputs\apk\debug\app-debug.apk

pause
exit /b 0

:ERROR_JAVA
echo ERROR: Java no encontrado
pause
exit /b 1

:ERROR_GRADLE
echo ERROR: Gradle no encontrado
pause
exit /b 1

:ERROR_BUILD
echo ERROR: Falló la compilacion
pause
exit /b 1
