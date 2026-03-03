@echo off
REM Verificador de requisitos para compilar APK sin Android Studio
REM Para Windows PowerShell / CMD

echo.
echo ========================================
echo VERIFICADOR DE REQUISITOS
echo ========================================
echo.

REM Verificar Java
echo Buscando Java...
where java >nul 2>nul
if %ErrorLevel% EQU 0 (
    echo [OK] Java encontrado
    java -version
) else (
    echo [ERROR] Java NO encontrado
    echo.
    echo Solución:
    echo 1. Descarga: https://www.oracle.com/java/technologies/javase-jdk11-downloads.html
    echo 2. Instala
    echo 3. Reinicia tu PC
    echo 4. Vuelve a ejecutar este script
)

echo.
echo Buscando Gradle...
if exist "C:\ProyectosGitHub\clonx\gradlew.bat" (
    echo [OK] Gradle Wrapper encontrado
) else (
    echo [ERROR] Gradle no encontrado
    echo Asegúrate de estar en C:\ProyectosGitHub\clonx
)

echo.
echo Verificando si teléfono está conectado...
adb devices >nul 2>nul
if %ErrorLevel% EQU 0 (
    echo [OK] ADB disponible
    adb devices
) else (
    echo [AVISO] ADB no disponible
    echo Puedes instalar el APK manualmente después
)

echo.
echo ========================================
echo RESUMEN:
echo ========================================
echo.
echo Si ves [OK] en todos lados, estás listo para compilar
echo.
echo Próximo paso:
echo   1. cd C:\ProyectosGitHub\clonx
echo   2. .\gradlew.bat assembleDebug
echo   3. Espera 5-10 minutos
echo   4. ¡APK generado!
echo.
pause
