# Dua by Aksha — Instagram Comment Picker Giveaway

Polished, APK-ready Android project for a branded giveaway comment picker.

## Branding
- Dua by Aksha wordmark/header
- Purple-to-pink giveaway visual identity
- Branded winner result/share text
- App package: `com.duabyaksha.commentpicker`

## Features
- Paste Instagram comments for local picking/testing
- One-entry-per-username option
- Keyword / hashtag filter
- Pick one or multiple winners
- Winner animation
- Share winner result
- Demo comments
- No Instagram password handling

## Build
Open this folder in Android Studio with Android SDK 35 and JDK 17 configured.

### Debug APK
```bash
./gradlew assembleDebug
```
APK: `app/build/outputs/apk/debug/app-debug.apk`

### Release APK
```bash
./gradlew assembleRelease
```
APK: `app/build/outputs/apk/release/app-release.apk`

The release build is not signed with a private production keystore. Configure your own signing key before Play Store distribution.

## Instagram API note
The picker currently accepts pasted comments. Automatic Instagram comment importing requires Meta/Instagram API access and appropriate authentication/permissions; it is intentionally not implemented through scraping.
