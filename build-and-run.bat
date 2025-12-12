@echo off
echo ========================================
echo Building Music Player App
echo ========================================
echo.

REM Navigate to project directory
cd /d "%~dp0"

echo Cleaning previous build...
call gradlew.bat clean

echo.
echo Building Debug APK...
call gradlew.bat assembleDebug

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ========================================
    echo Build Successful!
    echo ========================================
    echo.
    echo APK Location:
    echo %CD%\app\build\outputs\apk\debug\app-debug.apk
    echo.
    echo To install on device:
    echo 1. Connect your Android device via USB
    echo 2. Enable USB Debugging on your device
    echo 3. Run: adb install app\build\outputs\apk\debug\app-debug.apk
    echo.
    pause
) else (
    echo.
    echo ========================================
    echo Build Failed! Check errors above.
    echo ========================================
    echo.
    pause
)

