# Firebase 설정 가이드

이 프로젝트는 Firebase 설정 파일들을 보안을 위해 Git에서 제외하고 있습니다.
프로젝트를 처음 클론했다면 다음 단계를 따라 Firebase를 설정하세요.

## Firebase Console에서 설정 파일 다운로드

1. [Firebase Console](https://console.firebase.google.com/) 접속
2. 프로젝트 선택 (예: `cmpstudy-de74c`)
3. 프로젝트 설정 (⚙️ 아이콘) > 일반 탭

## 플랫폼별 설정

### 1. Android

#### secrets.xml 생성
`composeApp/src/androidMain/res/values/secrets.xml` 파일을 생성하고 다음 내용을 추가:

```xml
<resources>
    <!-- Firebase Configuration -->
    <string name="firebase_application_id">YOUR_APPLICATION_ID</string>
    <string name="firebase_api_key">YOUR_API_KEY</string>
    <string name="firebase_project_id">YOUR_PROJECT_ID</string>
</resources>
```

Firebase Console에서 Android 앱의 설정 값을 복사하여 입력합니다.

#### google-services.json
Firebase Console > Android 앱 > `google-services.json` 다운로드 버튼 클릭
다운로드한 파일을 `composeApp/google-services.json`에 복사합니다.

### 2. iOS

Firebase Console > iOS 앱 > `GoogleService-Info.plist` 다운로드 버튼 클릭
다운로드한 파일을 `iosApp/iosApp/GoogleService-Info.plist`에 복사합니다.

### 3. Web

`composeApp/src/webMain/kotlin/com/example/cmpstudy/FirebaseConfig.kt` 파일을 생성:

```kotlin
package com.example.cmpstudy

import dev.gitlive.firebase.FirebaseOptions

object FirebaseConfig {
    fun getOptions(): FirebaseOptions {
        return FirebaseOptions(
            applicationId = "YOUR_WEB_APPLICATION_ID",
            apiKey = "YOUR_API_KEY",
            projectId = "YOUR_PROJECT_ID"
        )
    }
}
```

Firebase Console > Web 앱에서 설정 값을 복사하여 입력합니다.

### 4. JVM/Desktop

`composeApp/src/jvmMain/kotlin/com/example/cmpstudy/FirebaseConfig.kt` 파일을 생성:

```kotlin
package com.example.cmpstudy

import dev.gitlive.firebase.FirebaseOptions

object FirebaseConfig {
    fun getOptions(): FirebaseOptions {
        // JVM/Desktop은 Web 설정을 재사용합니다
        return FirebaseOptions(
            applicationId = "YOUR_WEB_APPLICATION_ID",
            apiKey = "YOUR_API_KEY",
            projectId = "YOUR_PROJECT_ID"
        )
    }
}
```

**주의:** Firebase Console에 JVM 플랫폼은 없으므로 **Web 앱의 설정 값을 사용**합니다.

## 빠른 설정 (이미 파일이 있는 경우)

만약 이전에 Firebase 설정을 했다면 Git 히스토리에서 복구할 수 있습니다:

```bash
# Git stash에서 복구
git stash list
git stash pop

# 또는 이전 커밋에서 복구
git log --all -- "**/*google*" "**/*firebase*"
```

## 주의사항

⚠️ **절대로 실제 설정 파일을 Git에 커밋하지 마세요!**

다음 파일들은 `.gitignore`에 추가되어 있습니다:
- `**/secrets.xml`
- `**/FirebaseConfig.kt`
- `google-services.json`
- `GoogleService-Info.plist`

## 설정 확인

모든 파일을 생성한 후:

```bash
# Android 빌드
./gradlew :composeApp:assembleDebug

# iOS 빌드
cd iosApp && xcodebuild

# JVM 실행
./gradlew :composeApp:jvmRun
```
