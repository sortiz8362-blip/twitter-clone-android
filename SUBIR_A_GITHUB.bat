@echo off
echo ============================================
echo SUBIR PROYECTO A GITHUB PARA COMPILAR
echo ============================================
echo.
echo PASO 1) Crea un repositorio en GitHub:
echo    https://github.com/new
echo.
echo PASO 2) Copia la URL de tu repo (ejemplo):
echo    https://github.com/TU_USUARIO/twitter-clone-android.git
echo.
pause
echo.
set /p REPO_URL="Pega la URL de tu repo aqui: "
echo.
echo Configurando remoto...
git remote remove origin 2>nul
git remote add origin %REPO_URL%
echo.
echo Subiendo archivos...
git push -u origin master
echo.
echo ============================================
echo LISTO! Ahora:
echo 1. Ve a tu repo en GitHub.com
echo 2. Clic en "Actions"
echo 3. Clic en "Build APK"
echo 4. Clic en "Run workflow"
echo 5. Espera 5-10 minutos
echo 6. Descarga el APK generado
echo ============================================
pause
