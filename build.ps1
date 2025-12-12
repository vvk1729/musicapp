# Music Player App - Build and Run Script
# PowerShell script for building the Android app

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Music Player App - Build Script" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Set location to script directory
$scriptPath = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $scriptPath

Write-Host "Project Location: $scriptPath" -ForegroundColor Yellow
Write-Host ""

# Check if gradlew.bat exists
if (-not (Test-Path "gradlew.bat")) {
    Write-Host "ERROR: gradlew.bat not found!" -ForegroundColor Red
    Write-Host "Please ensure you're in the project root directory." -ForegroundColor Red
    pause
    exit 1
}

# Function to execute gradle command
function Invoke-GradleCommand {
    param([string]$command)

    Write-Host "Executing: .\gradlew.bat $command" -ForegroundColor Cyan
    & .\gradlew.bat $command

    if ($LASTEXITCODE -ne 0) {
        Write-Host ""
        Write-Host "Command failed with exit code $LASTEXITCODE" -ForegroundColor Red
        return $false
    }
    return $true
}

# Menu
Write-Host "Select an option:" -ForegroundColor Green
Write-Host "1. Clean and Build Debug APK" -ForegroundColor White
Write-Host "2. Build Debug APK (no clean)" -ForegroundColor White
Write-Host "3. Build Release APK" -ForegroundColor White
Write-Host "4. Install Debug APK on device" -ForegroundColor White
Write-Host "5. Clean project only" -ForegroundColor White
Write-Host "6. Check Gradle status" -ForegroundColor White
Write-Host "7. Exit" -ForegroundColor White
Write-Host ""

$choice = Read-Host "Enter choice (1-7)"

switch ($choice) {
    "1" {
        Write-Host ""
        Write-Host "Cleaning project..." -ForegroundColor Yellow
        if (Invoke-GradleCommand "clean") {
            Write-Host ""
            Write-Host "Building Debug APK..." -ForegroundColor Yellow
            if (Invoke-GradleCommand "assembleDebug") {
                Write-Host ""
                Write-Host "========================================" -ForegroundColor Green
                Write-Host "BUILD SUCCESSFUL!" -ForegroundColor Green
                Write-Host "========================================" -ForegroundColor Green
                Write-Host ""
                Write-Host "APK Location:" -ForegroundColor Cyan
                Write-Host "$scriptPath\app\build\outputs\apk\debug\app-debug.apk" -ForegroundColor White
                Write-Host ""

                # Check if APK exists
                if (Test-Path "app\build\outputs\apk\debug\app-debug.apk") {
                    $apkSize = (Get-Item "app\build\outputs\apk\debug\app-debug.apk").Length / 1MB
                    Write-Host ("APK Size: {0:N2} MB" -f $apkSize) -ForegroundColor Yellow
                }
            }
        }
    }
    "2" {
        Write-Host ""
        Write-Host "Building Debug APK..." -ForegroundColor Yellow
        if (Invoke-GradleCommand "assembleDebug") {
            Write-Host ""
            Write-Host "========================================" -ForegroundColor Green
            Write-Host "BUILD SUCCESSFUL!" -ForegroundColor Green
            Write-Host "========================================" -ForegroundColor Green
            Write-Host ""
            Write-Host "APK Location:" -ForegroundColor Cyan
            Write-Host "$scriptPath\app\build\outputs\apk\debug\app-debug.apk" -ForegroundColor White
        }
    }
    "3" {
        Write-Host ""
        Write-Host "Building Release APK..." -ForegroundColor Yellow
        if (Invoke-GradleCommand "assembleRelease") {
            Write-Host ""
            Write-Host "========================================" -ForegroundColor Green
            Write-Host "BUILD SUCCESSFUL!" -ForegroundColor Green
            Write-Host "========================================" -ForegroundColor Green
            Write-Host ""
            Write-Host "APK Location:" -ForegroundColor Cyan
            Write-Host "$scriptPath\app\build\outputs\apk\release\app-release-unsigned.apk" -ForegroundColor White
            Write-Host ""
            Write-Host "NOTE: This is an unsigned APK." -ForegroundColor Yellow
        }
    }
    "4" {
        Write-Host ""
        Write-Host "Installing Debug APK on device..." -ForegroundColor Yellow
        if (Invoke-GradleCommand "installDebug") {
            Write-Host ""
            Write-Host "========================================" -ForegroundColor Green
            Write-Host "INSTALL SUCCESSFUL!" -ForegroundColor Green
            Write-Host "========================================" -ForegroundColor Green
            Write-Host ""
            Write-Host "You can now launch the app on your device." -ForegroundColor White
        }
    }
    "5" {
        Write-Host ""
        Write-Host "Cleaning project..." -ForegroundColor Yellow
        if (Invoke-GradleCommand "clean") {
            Write-Host ""
            Write-Host "Clean completed successfully." -ForegroundColor Green
        }
    }
    "6" {
        Write-Host ""
        Write-Host "Checking Gradle daemon status..." -ForegroundColor Yellow
        Invoke-GradleCommand "--status"
    }
    "7" {
        Write-Host ""
        Write-Host "Exiting..." -ForegroundColor Yellow
        exit 0
    }
    default {
        Write-Host ""
        Write-Host "Invalid choice!" -ForegroundColor Red
    }
}

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan

# Check for connected devices
Write-Host ""
Write-Host "Checking for connected Android devices..." -ForegroundColor Yellow
if (Get-Command adb -ErrorAction SilentlyContinue) {
    $devices = & adb devices
    Write-Host $devices -ForegroundColor White
} else {
    Write-Host "ADB not found in PATH. Cannot check for devices." -ForegroundColor Yellow
}

Write-Host ""
pause

