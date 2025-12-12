
# Music Player App - Android Internship Assignment

A modern music player app built with **Kotlin** and **Jetpack Compose**, demonstrating Android development best practices with MVVM architecture, API integration, and audio playback capabilities.

---

## 🚀 How to Run the App

### Method 1: Android Studio (Recommended)
1. **Open the project**:
   - Launch Android Studio
   - Click `File → Open`
   - Navigate to the `MusicPlayerApp` folder
   - Click `OK`

2. **Wait for Gradle sync** (first time takes 2-5 minutes)

3. **Run the app**:
   - Connect an Android device (with USB debugging enabled) OR start an emulator
   - Click the green **Run** button ▶️ (or press `Shift + F10`)
   - Select your device/emulator
   - The app will build, install, and launch automatically

### Method 2: Build APK via Command Line
```bash
# Navigate to project directory
cd MusicPlayerApp

# Build debug APK (Windows)
gradlew.bat assembleDebug

# Install on connected device
adb install app\build\outputs\apk\debug\app-debug.apk
```

### Method 3: Quick Build Script (Windows)
- **Double-click** `build-and-run.bat` in the project folder
- Wait for build completion
- APK will be created at: `app\build\outputs\apk\debug\app-debug.apk`

### APK Location
After building, find the APK at:
```
app\build\outputs\apk\debug\app-debug.apk
```

---

## 🎧 API Used: Jamendo

**Endpoint:**  
`https://api.jamendo.com/v3.0/tracks/`

### ✔ Why Jamendo?
- Full-length MP3 audio URLs
- Rich metadata (title, artist, artwork, duration)
- Free & legal for demos
- No OAuth — only a simple client ID
- Stable JSON responses

### ✔ Fallback Handling
If Jamendo API fails:
- The app loads **mock tracks**
- Uses **SoundHelix** audio + **Unsplash** images  
  This ensures the app **always works**, even without internet.

---

## 📋 Key Assumptions
- Requires **internet** for live API (mock fallback available)
- Minimum Android version: **API 24**
- Permissions needed:
    - `INTERNET`
    - `ACCESS_NETWORK_STATE`
- Audio URLs are streamable (MP3)
- No login system or offline downloads required
- State persists across rotation using Compose + ViewModel

---

## 🏗 Architecture Overview

**Pattern:** MVVM


### **Tech Stack**
- Kotlin
- Jetpack Compose + Material 3
- Ktor Client
- Coil (image loading)
- Coroutines + StateFlow
- MediaPlayer for audio playback

---

## ✨ Features Implemented

### **Core Requirements**
- Fetch audio list from API
- Display:
    - title
    - artist
    - duration
    - thumbnail
- Loading + error handling
- Sorting:
    - **A–Z**
    - **Duration (short → long)**
- Audio playback (Play/Pause)
- Show total duration + current position
- Retry on network error
- Proper MediaPlayer lifecycle

### **Bonus Features**
- Seek bar
- Next / Previous track
- Auto-play next track
- Dark mode
- Smooth UI animations
- Mock data fallback
- State survives rotation

---

## 📱 How to Test
1. Launch the app
2. Scroll and browse tracks
3. Switch between sort options
4. Tap a track to play
5. Use play/pause + next/previous
6. Drag seek bar
7. Rotate device (state preserved)
8. Turn off internet → mock data loads

---

## 📦 Deliverables
- Full Android Studio project
- This README
- API explanation
- Assumptions
- APK file

---

## 🛠 Build Requirements
- Android Studio Hedgehog or newer
- JDK 8+
- Android SDK 24+
- Internet for dependency sync

---

## 📌 Project Name
**musicapp**

