# Build the APK with GitHub Actions

This project includes a GitHub Actions workflow that builds both a debug APK and an unsigned release APK.

## 1. Create a GitHub repository

Create a new repository on GitHub. You can keep it private.

## 2. Upload this project

Upload/extract all project files so `.github/workflows/build-apk.yml` is present at exactly that path.

## 3. Run the build

Open **Actions** → **Build Android APK** → **Run workflow**.

The workflow also runs automatically for pushes to `main` and pull requests.

## 4. Download the APK

When the workflow finishes successfully, open the workflow run and scroll to **Artifacts**.

Download:

- `dua-by-aksha-comment-picker-debug` — easiest version for testing on your phone.
- `dua-by-aksha-comment-picker-release-unsigned` — release build, but unsigned.

## Signing for a normal release APK

The included release APK is intentionally unsigned. Do not put a keystore or passwords into the repository. For a distributable release, create a keystore and store its credentials as GitHub Actions secrets, then add a signing configuration to the Gradle build.

The current workflow uses JDK 17 and Gradle 8.9, matching this project's Android Gradle Plugin 8.7.3 configuration.
