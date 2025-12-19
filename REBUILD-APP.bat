@echo off
cd /d "%~dp0"
echo ════════════════════════════════════════════════════════════
echo   REBUILDING MUSIC PLAYER APP
echo ════════════════════════════════════════════════════════════
echo.
echo [1/3] Cleaning old build...
call gradlew clean
echo.
echo [2/3] Building fresh APK...
call gradlew :shared:assembleDebug
echo.
echo [3/3] Done!
echo.
echo ════════════════════════════════════════════════════════════
echo.
if exist "shared\build\outputs\apk\debug\shared-debug.apk" (
    echo ✓ APK created successfully!
    echo   Location: shared\build\outputs\apk\debug\shared-debug.apk
    echo.
    echo NOW DO THIS:
    echo   1. Uninstall old "Music Player" app from your phone
    echo   2. In Android Studio, click the green Run button
    echo   3. App installs and works without crash!
) else (
    echo × Build failed! Check errors above.
)
echo.
pause

