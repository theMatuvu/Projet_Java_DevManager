@echo off
echo ========================================
echo   DevManager Pro - Lancement
echo ========================================
echo.

echo [1/3] Verification de Java...
java -version
if %errorlevel% neq 0 (
    echo ERREUR: Java n'est pas installe ou n'est pas dans le PATH
    pause
    exit /b 1
)
echo.

echo [2/3] Verification de Maven...
mvn -version
if %errorlevel% neq 0 (
    echo ERREUR: Maven n'est pas installe ou n'est pas dans le PATH
    pause
    exit /b 1
)
echo.

echo [3/3] Compilation et lancement de l'application...
echo.
mvn clean javafx:run

if %errorlevel% neq 0 (
    echo.
    echo ERREUR: Echec du lancement de l'application
    echo Verifiez que le backend est demarre sur http://localhost:8080
    pause
    exit /b 1
)

pause
