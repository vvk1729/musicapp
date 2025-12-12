# 🚀 QUICK START GUIDE - Music Player App

## ⚡ Fastest Way to Run the App

### Method 1: Android Studio (Easiest - 3 clicks!)

1. **Open Android Studio** → Click "Open"
2. **Navigate to**: `C:\Users\HP\Downloads\Mile web\Mile web\MusicPlayerApp`
3. **Click Run button** ▶️ (green play icon)

**That's it!** The app will build, install, and launch automatically.

---

### Method 2: PowerShell Script (No Android Studio needed!)

1. **Navigate to project folder**:
   ```
   C:\Users\HP\Downloads\Mile web\Mile web\MusicPlayerApp
   ```

2. **Right-click `build.ps1`** → Select "Run with PowerShell"

3. **Choose option 1** (Clean and Build Debug APK)

4. **Install APK on your device**:
   - Copy `app\build\outputs\apk\debug\app-debug.apk` to your phone
   - Open and install
   - OR use option 4 in the script if device is connected via USB

---

### Method 3: Command Line (Quick Build)

1. **Open PowerShell or Command Prompt**

2. **Run these commands**:
   ```powershell
   cd "C:\Users\HP\Downloads\Mile web\Mile web\MusicPlayerApp"
   .\gradlew.bat assembleDebug
   ```

3. **Find APK at**:
   ```
   app\build\outputs\apk\debug\app-debug.apk
   ```

---

### Method 4: Batch File (Double-Click!)

1. **Double-click**: `build-and-run.bat`
2. **Wait for build to complete**
3. **APK location will be displayed**

---

## 📱 Device Requirements

- **Android 7.0 (API 24) or higher**
- **Internet connection** (for downloading tracks)
- **5-10 MB free storage**

---

## 🎵 How to Use the App

### First Launch
1. App loads and fetches music tracks from Jamendo API
2. If API is down, uses mock data automatically
3. Displays list of tracks with album art

### Playing Music
1. **Tap any track** to start playing
2. **Player controls appear at bottom**:
   - ⏯️ Play/Pause button
   - ⏮️ Previous track
   - ⏭️ Next track
   - Progress bar for seeking

### Sorting Tracks
- **Click "Name (A-Z)"** chip → Sort alphabetically
- **Click "Duration"** chip → Sort by track length

### Features in Action
- ✅ Real-time progress bar updates
- ✅ Album artwork displays for each track
- ✅ Automatic next track when current ends
- ✅ Beautiful Material 3 UI with smooth animations
- ✅ Dark mode support

---

## 🛠️ Troubleshooting

### "Build Failed"
- **Solution**: Make sure you have JDK 8+ installed
- Run: `.\gradlew.bat clean` then try again

### "SDK Not Found"
- **Solution**: Install Android Studio
- Or set SDK path in `local.properties`:
  ```
  sdk.dir=C\:\\Users\\HP\\AppData\\Local\\Android\\Sdk
  ```

### "App Crashes on Launch"
- **Solution**: Check Android version (needs 7.0+)
- Clear app data and reinstall

### "No Tracks Displayed"
- **Solution**: Check internet connection
- App should auto-fallback to mock data if API fails
- Try "Retry" button

---

## 📊 Build Time Expectations

| Build Type | First Time | Subsequent |
|------------|-----------|------------|
| Clean Build | 5-10 min | 1-3 min |
| Incremental | N/A | 30-60 sec |
| Install | 30 sec | 30 sec |

**First build downloads 150+ MB of dependencies!**

---

## 📦 File Locations

After building, you'll find:

```
MusicPlayerApp/
├── app/build/outputs/apk/
│   ├── debug/
│   │   └── app-debug.apk          ← Debug APK (install this)
│   └── release/
│       └── app-release-unsigned.apk
│
├── build-and-run.bat              ← Double-click to build
├── build.ps1                      ← PowerShell script with menu
├── BUILD_GUIDE.md                 ← Detailed build instructions
├── ERROR_FIXES.md                 ← Troubleshooting guide
└── README.md                      ← Project documentation
```

---

## ✅ Pre-Flight Checklist

Before running, ensure:
- [ ] JDK 8+ installed
- [ ] Android SDK installed (if using command line)
- [ ] Internet connection available
- [ ] 500+ MB free disk space (for Gradle cache)
- [ ] USB debugging enabled (if installing on device)

---

## 🎯 What to Expect

### On First Launch:
1. **Loading screen** (2-5 seconds)
2. **Track list appears** (6-50 tracks)
3. **Album art loads** progressively
4. **Ready to play!** 🎵

### App Features:
- ✨ Modern Material 3 design
- 🌙 Dark mode support
- 🎨 Beautiful gradients and animations
- 📱 Responsive layout
- ⚡ Fast and smooth performance

---

## 🆘 Need Help?

1. **Check `ERROR_FIXES.md`** for detailed troubleshooting
2. **Read `BUILD_GUIDE.md`** for step-by-step instructions
3. **View `README.md`** for project overview

---

## 🎊 Success Indicators

You'll know it's working when:
- ✅ No build errors in console
- ✅ APK file created in `app/build/outputs/apk/debug/`
- ✅ App installs on device without errors
- ✅ App launches and shows track list
- ✅ Music plays when track is tapped

---

## 🚀 Ready to Go!

**Choose your method above and start building!**

The app is complete, tested, and ready to demonstrate all the required features for the internship assignment.

**Good luck! 🎵**

