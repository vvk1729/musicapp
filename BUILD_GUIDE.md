# Music Player App - Setup and Build Guide

## Quick Start Guide

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later installed
- Android SDK with API 24+ installed
- JDK 8 or higher
- Internet connection for downloading dependencies

### Method 1: Build Using Android Studio (Recommended)

1. **Open the Project**
   - Launch Android Studio
   - Click "Open" or "File → Open"
   - Navigate to: `C:\Users\HP\Downloads\Mile web\Mile web\MusicPlayerApp`
   - Click "OK"

2. **Wait for Gradle Sync**
   - Android Studio will automatically sync Gradle
   - Wait for "Gradle build finished" message in the Build panel
   - This may take 2-5 minutes on first run

3. **Build the Project**
   - Click "Build → Make Project" (Ctrl+F9)
   - Or click the hammer icon in the toolbar
   - Wait for build to complete

4. **Run the App**
   - Connect an Android device via USB (with USB debugging enabled)
   - OR start an Android Emulator
   - Click the green "Run" button (▶) or press Shift+F10
   - Select your device/emulator
   - App will install and launch automatically

### Method 2: Build Using Command Line

1. **Open PowerShell or Command Prompt**
   ```powershell
   cd "C:\Users\HP\Downloads\Mile web\Mile web\MusicPlayerApp"
   ```

2. **Build Debug APK**
   ```powershell
   .\gradlew.bat assembleDebug
   ```

3. **Or Use the Batch File**
   - Double-click `build-and-run.bat` in the project folder
   - This will clean, build, and show APK location

4. **Install APK on Device**
   ```powershell
   adb install app\build\outputs\apk\debug\app-debug.apk
   ```

### Method 3: Quick Build Script

Simply run the included batch file:
```
build-and-run.bat
```

## APK Location

After successful build, find the APK at:
```
app\build\outputs\apk\debug\app-debug.apk
```

## Common Issues and Solutions

### Issue 1: "SDK location not found"
**Solution:**
- Open `local.properties` file
- Ensure it contains:
  ```
  sdk.dir=C\:\\Users\\HP\\AppData\\Local\\Android\\Sdk
  ```
- Update the path to match your Android SDK location

### Issue 2: Gradle sync fails
**Solution:**
- Check your internet connection
- In Android Studio: File → Invalidate Caches → Invalidate and Restart
- Delete `.gradle` folder in project directory and sync again

### Issue 3: Build fails with "Execution failed for task ':app:compileDebugKotlin'"
**Solution:**
- Ensure you have JDK 8 or higher installed
- Check File → Project Structure → SDK Location
- Update Gradle if needed: `.\gradlew.bat wrapper --gradle-version 8.2`

### Issue 4: App crashes on launch
**Solution:**
- Ensure device/emulator has Android 7.0 (API 24) or higher
- Check logcat for specific error messages
- Verify internet permission in AndroidManifest.xml

### Issue 5: "Cannot resolve symbol" in Android Studio
**Solution:**
- File → Sync Project with Gradle Files
- File → Invalidate Caches → Invalidate and Restart
- Build → Clean Project, then Build → Rebuild Project

## Testing the App

### Required for Testing
- **Internet connection** on the device/emulator
- **API 24+** (Android 7.0 or higher)

### Features to Test
1. **Load Tracks**: App should load and display music tracks
2. **Sort by Name**: Click "Name (A-Z)" chip to sort alphabetically
3. **Sort by Duration**: Click "Duration" chip to sort by length
4. **Play Track**: Tap any track to start playback
5. **Player Controls**: Use play/pause, next, previous buttons
6. **Seek Bar**: Drag to scrub through the audio
7. **Auto-play**: Next track should play automatically when current finishes

### API Information
- Primary API: Jamendo (https://api.jamendo.com/v3.0/tracks/)
- Fallback: Mock data with SoundHelix audio and Unsplash images
- The app gracefully falls back to mock data if API is unavailable

## Project Structure

```
MusicPlayerApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/musicplayer/
│   │   │   ├── data/
│   │   │   │   ├── model/          # Track data model
│   │   │   │   ├── remote/         # API client (Ktor)
│   │   │   │   └── repository/     # Repository pattern
│   │   │   ├── player/             # AudioPlayer (MediaPlayer)
│   │   │   ├── viewmodel/          # MusicViewModel (MVVM)
│   │   │   ├── ui/
│   │   │   │   ├── components/     # Reusable UI
│   │   │   │   ├── screens/        # Main screen
│   │   │   │   └── theme/          # Material 3 theme
│   │   │   └── MainActivity.kt
│   │   ├── res/                    # Resources
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts            # App dependencies
├── build.gradle.kts                # Project config
├── settings.gradle.kts             # Project settings
├── gradlew.bat                     # Gradle wrapper
├── build-and-run.bat               # Quick build script
└── README.md                       # Documentation
```

## Key Technologies Used

- **Kotlin**: 100% Kotlin code
- **Jetpack Compose**: Modern UI framework with Material 3
- **Ktor Client**: HTTP networking for API calls
- **Kotlinx Serialization**: JSON parsing
- **Coil**: Async image loading
- **Android MediaPlayer**: Audio streaming and playback
- **StateFlow**: Reactive state management
- **Coroutines**: Async programming
- **MVVM Architecture**: Clean separation of concerns

## Troubleshooting Build Issues

### Clear Build Cache
```powershell
.\gradlew.bat clean
.\gradlew.bat --stop
# Delete .gradle folder
rmdir /s /q .gradle
.\gradlew.bat assembleDebug
```

### Update Gradle Wrapper
```powershell
.\gradlew.bat wrapper --gradle-version 8.2
```

### Check Gradle Daemon
```powershell
.\gradlew.bat --status
.\gradlew.bat --stop
```

## Running Tests

While no unit tests are included in this demo, you can verify functionality by:
1. Testing all sorting modes
2. Playing multiple tracks in sequence
3. Testing seek bar functionality
4. Rotating device (configuration change handling)
5. Testing with/without internet connection
6. Testing on different Android versions

## Additional Commands

### Build Release APK (unsigned)
```powershell
.\gradlew.bat assembleRelease
```
Location: `app\build\outputs\apk\release\app-release-unsigned.apk`

### Install and Run on Device
```powershell
.\gradlew.bat installDebug
adb shell am start -n com.musicplayer/.MainActivity
```

### View Logcat
```powershell
adb logcat | findstr "MusicPlayer"
```

## Support

If you encounter issues:
1. Check this troubleshooting guide
2. Review error messages in Android Studio's Build panel
3. Check logcat for runtime errors
4. Ensure all prerequisites are met
5. Try cleaning and rebuilding the project

## License

This project is created for educational purposes as part of an internship application.

