@echo off
echo ============================================
echo   Building Full KMP Music Player App
echo ============================================
echo.

echo [1/3] Cleaning build...
call gradlew clean

echo.
echo [2/3] Building shared module (KMP)...
call gradlew :shared:assembleDebug

echo.
echo [3/3] Build complete!
echo.
echo APK Location:
echo   shared\build\outputs\apk\debug\shared-debug.apk
echo.
echo To install: adb install shared\build\outputs\apk\debug\shared-debug.apk
echo.
pause

