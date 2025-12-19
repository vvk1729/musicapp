# 🎵 Music Player App - Kotlin Multiplatform

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.20-blue.svg)](https://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose%20Multiplatform-1.5.11-green.svg)](https://www.jetbrains.com/lp/compose-multiplatform/)
[![Platform](https://img.shields.io/badge/Platform-Android-brightgreen.svg)](https://www.android.com)
[![License](https://img.shields.io/badge/License-MIT-orange.svg)](LICENSE)

A modern, feature-rich music streaming app built with **100% Kotlin Multiplatform (KMP)** and **Compose Multiplatform**. This project demonstrates professional Android development with clean architecture, showcasing music playback from the Jamendo API.

## 📱 Screenshots

[Screenshots will be added here]

## ✨ Features

### Core Functionality
- 🎵 **Music Streaming** - Stream 50+ tracks from Jamendo API
- ▶️ **Full Playback Controls** - Play, Pause, Next, Previous
- 📊 **Progress Tracking** - Seek bar with live playback position
- 🔄 **Smart Sorting** - Sort by Name, Artist, or Duration
- 🎨 **Beautiful UI** - Material3 design with dark theme support
- 🖼️ **Album Art** - High-quality album artwork thumbnails
- ⚡ **Fast & Responsive** - Smooth animations and instant feedback

### Technical Features
- ✅ **100% Kotlin Multiplatform** - Pure KMP architecture
- ✅ **Compose Multiplatform UI** - Shared UI across platforms
- ✅ **MVVM Architecture** - Clean separation of concerns
- ✅ **Repository Pattern** - Centralized data management
- ✅ **Use Cases** - Business logic encapsulation
- ✅ **Expect/Actual Pattern** - Platform-specific implementations
- ✅ **Coroutines** - Asynchronous programming
- ✅ **StateFlow** - Reactive state management
- ✅ **Error Handling** - Graceful error states
- ✅ **Loading States** - User-friendly loading indicators

## 🏗️ Architecture

This app follows **Clean Architecture** principles with MVVM pattern:

```
┌─────────────────────────────────────────────────────────┐
│                    Presentation Layer                    │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐  │
│  │ UI (Compose) │←→│  ViewModel   │←→│  StateFlow   │  │
│  └──────────────┘  └──────────────┘  └──────────────┘  │
└─────────────────────────────────────────────────────────┘
                           ↕
┌─────────────────────────────────────────────────────────┐
│                     Domain Layer                         │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐  │
│  │  Use Cases   │  │   Models     │  │ Repositories │  │
│  │              │  │              │  │  (Interface) │  │
│  └──────────────┘  └──────────────┘  └──────────────┘  │
└─────────────────────────────────────────────────────────┘
                           ↕
┌─────────────────────────────────────────────────────────┐
│                      Data Layer                          │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐  │
│  │ Repository   │  │  API Client  │  │ MediaPlayer  │  │
│  │    Impl      │  │   (Ktor)     │  │ (Android)    │  │
│  └──────────────┘  └──────────────┘  └──────────────┘  │
└─────────────────────────────────────────────────────────┘
```

### Project Structure

```
MusicPlayerApp/
├── shared/                          # KMP Module (Application)
│   ├── src/
│   │   ├── commonMain/             # Shared code (all platforms)
│   │   │   └── kotlin/
│   │   │       ├── data/           # Data layer
│   │   │       │   ├── remote/     # API integration
│   │   │       │   └── repository/ # Repository implementations
│   │   │       ├── domain/         # Domain layer
│   │   │       │   ├── model/      # Data models
│   │   │       │   ├── repository/ # Repository interfaces
│   │   │       │   └── usecase/    # Business logic
│   │   │       ├── presentation/   # ViewModels
│   │   │       ├── ui/             # Compose UI
│   │   │       │   ├── components/ # Reusable components
│   │   │       │   ├── screens/    # Screen composables
│   │   │       │   └── theme/      # Material3 theme
│   │   │       ├── player/         # Audio player (expect/actual)
│   │   │       └── di/             # Dependency injection
│   │   └── androidMain/            # Android-specific code
│   │       └── kotlin/
│   │           ├── MainActivity.kt # App entry point
│   │           └── player/         # MediaPlayer implementation
│   └── build.gradle.kts
├── gradle/
├── build.gradle.kts
└── settings.gradle.kts
```

## 🚀 Getting Started

### Prerequisites

- **Android Studio** Hedgehog (2023.1.1) or later
- **JDK** 17 or later
- **Android SDK** API 24+ (Android 7.0+)
- **Gradle** 8.5 or later

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/vvk1729/musicapp.git
   cd musicapp
   ```

2. **Open in Android Studio**
   - File → Open → Select the project folder
   - Wait for Gradle sync to complete

3. **Run the app**
   - Select **"shared"** from run configuration dropdown
   - Click Run ▶️ or press `Shift + F10`

### Build from Command Line

```bash
# Clean build
./gradlew clean

# Build debug APK
./gradlew :shared:assembleDebug

# APK location
./shared/build/outputs/apk/debug/shared-debug.apk
```

## 🔧 Technologies Used

### Kotlin Multiplatform
- **Kotlin** 1.9.20 - Modern programming language
- **Compose Multiplatform** 1.5.11 - Declarative UI framework
- **Coroutines** 1.7.3 - Asynchronous programming

### Networking
- **Ktor Client** 2.3.6 - HTTP client for API calls
- **Kotlinx Serialization** 1.6.0 - JSON parsing

### UI/UX
- **Material3** - Modern Material Design
- **Coil3** 3.0.0-alpha06 - Image loading
- **Material Icons Extended** - Rich icon set

### Android
- **MediaPlayer** - Audio playback
- **Activity Compose** 1.8.1 - Jetpack Compose integration
- **AGP** 8.2.0 - Android Gradle Plugin

## 📦 APK Download

Download the latest APK from the [Releases](https://github.com/vvk1729/musicapp/releases) page.

Or build it yourself:
```bash
./gradlew :shared:assembleDebug
```
APK will be at: `shared/build/outputs/apk/debug/shared-debug.apk`

## 🎯 Features Breakdown

### Music Playback
- Stream music from Jamendo API
- MediaPlayer integration for Android
- Play/Pause/Stop controls
- Next/Previous track navigation
- Automatic track progression

### UI Components
- **Track List** - Scrollable list with album art
- **Player Controls** - Bottom sheet with playback controls
- **Sort Options** - Chip-based sorting selection
- **Progress Bar** - Draggable seek bar
- **Loading States** - Skeleton screens and spinners
- **Error Handling** - User-friendly error messages

### Sorting Options
1. **By Name** - Alphabetical (A-Z)
2. **By Artist** - Artist name alphabetically
3. **By Duration** - Shortest to longest

## 🛠️ Development

### Code Style
- **Kotlin Coding Conventions** - Following official guidelines
- **Clean Code** - Meaningful names, small functions
- **SOLID Principles** - Dependency inversion, single responsibility

### Testing
```bash
# Run unit tests
./gradlew test

# Run Android instrumented tests
./gradlew connectedAndroidTest
```

## 📄 API Documentation

This app uses the [Jamendo API](https://api.jamendo.com) for music streaming.

**Base URL:** `https://api.jamendo.com/v3.0`

**Endpoint:** `/tracks/`

**Parameters:**
- `client_id`: `56d30c95`
- `format`: `json`
- `limit`: `50`
- `audioformat`: `mp32`
- `imagesize`: `500`

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👤 Author

**Your Name**
- GitHub: [@vvk1729](https://github.com/vvk1729)

## 🙏 Acknowledgments

- [Jamendo](https://www.jamendo.com) - Music API
- [JetBrains](https://www.jetbrains.com) - Compose Multiplatform
- [Material Design](https://m3.material.io) - UI/UX guidelines

## 📞 Support

If you have any questions or issues, please open an issue on GitHub.

---

**Made with ❤️ using Kotlin Multiplatform**

