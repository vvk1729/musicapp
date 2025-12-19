# 🎵 Music Player - Full Kotlin Multiplatform App

A beautiful, fully-featured music player built with **100% Kotlin Multiplatform** and **Compose Multiplatform**.

## ✨ Highlights

- **Pure KMP**: No native Android code - everything in `shared/`
- **Shared UI**: Compose Multiplatform UI works across platforms
- **Modern Architecture**: MVVM + Clean Architecture
- **Real Audio Streaming**: Jamendo API integration
- **Material3 Design**: Beautiful, polished interface

## 🏗️ Project Structure

```
MusicPlayerApp/
└── shared/                    ← The ONLY module (KMP Application)
    ├── build.gradle.kts      ← Compose Multiplatform app config
    ├── src/
    │   ├── commonMain/       ← 100% shared code (UI + logic)
    │   ├── androidMain/      ← Android-specific (MainActivity, MediaPlayer)
    │   ├── iosMain/          ← iOS-specific (stubs for now)
    │   └── desktopMain/      ← Desktop-specific (stubs for now)
    └── build/
        └── outputs/apk/      ← APK output here
```

## 🚀 Quick Start

### Prerequisites
- JDK 11 or higher
- Android Studio (latest version)
- Android SDK with API 34

### Build & Run

**Option 1: Android Studio**
1. Open project in Android Studio
2. Wait for Gradle sync
3. Select **"shared"** run configuration (not "app"!)
4. Click Run ▶️

**Option 2: Command Line**
```bash
# Windows
build-kmp-app.bat

# Linux/Mac
./gradlew :shared:assembleDebug
```

**Option 3: Install APK**
```bash
# Build
./gradlew :shared:assembleDebug

# Install
adb install shared/build/outputs/apk/debug/shared-debug.apk
```

## 📱 Features

### Core Features (✅ Complete)
- [x] Fetch tracks from Jamendo API
- [x] Display track list with album art
- [x] Sort by Name, Artist, or Duration
- [x] Stream audio from internet
- [x] Play/Pause controls
- [x] Next/Previous track
- [x] Seek bar with progress
- [x] Loading states
- [x] Error handling

### UI/UX (✅ Complete)
- [x] Material3 Design System
- [x] Dark theme support
- [x] Smooth animations
- [x] Album art thumbnails
- [x] Playing indicator
- [x] Gradient backgrounds
- [x] Filter chips for sorting
- [x] Bottom player controls

### Architecture (✅ Complete)
- [x] MVVM pattern
- [x] Clean Architecture
- [x] Expect/Actual for platform code
- [x] Repository pattern
- [x] Use cases
- [x] Dependency injection

## 🎯 Platform Support

| Platform | Status | Notes |
|----------|--------|-------|
| **Android** | ✅ Full Support | Complete with MediaPlayer |
| **iOS** | ⚠️ UI Ready | Audio playback stubbed |
| **Desktop** | ⚠️ UI Ready | Audio playback stubbed |

## 🔧 Tech Stack

### Multiplatform
- **Kotlin Multiplatform** 1.9.20
- **Compose Multiplatform** 1.5.11
- **Ktor Client** 2.3.6 (networking)
- **Kotlinx Serialization** (JSON parsing)
- **Kotlinx Coroutines** (async)
- **Coil3** (image loading)

### Android-Specific
- **MediaPlayer** (audio streaming)
- **Activity Compose** (app wrapper)

## 📂 Key Files

### Entry Points
- `shared/src/commonMain/kotlin/.../App.kt` - Main composable
- `shared/src/androidMain/kotlin/.../MainActivity.kt` - Android launcher

### UI Components
- `ui/screens/MusicListScreen.kt` - Main screen
- `ui/components/TrackListItem.kt` - Track card
- `ui/components/PlayerControls.kt` - Bottom player
- `ui/theme/` - Material3 theme

### Business Logic
- `presentation/MusicListViewModel.kt` - ViewModel
- `player/AudioPlayer.kt` - Expect class (platform audio)
- `domain/` - Models, repositories, use cases
- `data/` - API client, Jamendo integration

## 🎨 Screenshots

*(Add screenshots here)*

## 📖 API

Uses **Jamendo API** for free music:
- **Endpoint**: https://api.jamendo.com/v3.0/tracks/
- **Client ID**: Included in code
- **License**: Creative Commons

## 🛠️ Development

### Build Commands
```bash
# Clean build
./gradlew clean

# Build debug APK
./gradlew :shared:assembleDebug

# Build release APK
./gradlew :shared:assembleRelease

# Install on device
./gradlew :shared:installDebug
```

### Project Configuration
- `build.gradle.kts` (root) - Plugin versions
- `shared/build.gradle.kts` - App configuration
- `settings.gradle.kts` - Module settings
- `gradle.properties` - Gradle options

## 🤝 Contributing

This is a demo/assignment project, but improvements are welcome!

### Adding iOS Support
1. Implement `actual class AudioPlayer` in `iosMain/kotlin/.../player/`
2. Use AVPlayer for audio streaming
3. Create iOS app wrapper

### Adding Desktop Support
1. Implement `actual class AudioPlayer` in `desktopMain/kotlin/.../player/`
2. Use JavaFX Media or vlcj
3. Create desktop app wrapper

## 📝 License

This project is for educational purposes.

- **Code**: MIT License
- **Music**: Provided by Jamendo (Creative Commons)
- **API**: Jamendo API (free tier)

## 🎓 Learning Outcomes

This project demonstrates:
- ✅ Kotlin Multiplatform architecture
- ✅ Compose Multiplatform for shared UI
- ✅ Expect/Actual pattern
- ✅ Clean Architecture in KMP
- ✅ REST API integration
- ✅ Media playback
- ✅ State management
- ✅ Modern Material Design

## 📞 Support

For questions about the internship assignment:
- Check `FULL_KMP_MIGRATION.md` for migration details
- Check `HOW_TO_RUN.md` for detailed instructions

---

**Built with ❤️ using Kotlin Multiplatform**

