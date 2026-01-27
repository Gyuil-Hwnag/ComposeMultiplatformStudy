# Firebase 설정 가이드

이 프로젝트는 Firebase 설정 파일들을 보안을 위해 Git에서 제외하고 있습니다.
프로젝트를 처음 클론했다면 다음 단계를 따라 Firebase를 설정하세요.

## 설정 파일 생성

### 1. Android

#### secrets.xml
```bash
cp composeApp/src/androidMain/res/values/secrets.xml.example \
   composeApp/src/androidMain/res/values/secrets.xml
```

`secrets.xml` 파일을 열고 Firebase Console에서 받은 값으로 변경:
- `firebase_application_id`
- `firebase_api_key`
- `firebase_project_id`

#### google-services.json
```bash
cp composeApp/google-services.json.example \
   composeApp/google-services.json
```

Firebase Console에서 다운로드한 `google-services.json` 파일로 교체하거나,
파일 내용을 직접 수정합니다.

### 2. iOS

```bash
cp iosApp/iosApp/GoogleService-Info.plist.example \
   iosApp/iosApp/GoogleService-Info.plist
```

Firebase Console에서 다운로드한 `GoogleService-Info.plist` 파일로 교체하거나,
파일 내용을 직접 수정합니다.

### 3. Web

```bash
cp composeApp/src/webMain/kotlin/com/example/cmpstudy/FirebaseConfig.kt.example \
   composeApp/src/webMain/kotlin/com/example/cmpstudy/FirebaseConfig.kt
```

`FirebaseConfig.kt` 파일을 열고 Firebase Console에서 받은 값으로 변경:
- `applicationId`
- `apiKey`
- `projectId`

## Firebase Console에서 설정 값 받기

1. [Firebase Console](https://console.firebase.google.com/) 접속
2. 프로젝트 선택
3. 프로젝트 설정 (⚙️ 아이콘) > 일반 탭
4. 각 플랫폼별로 앱 추가 또는 기존 앱 선택
5. 설정 파일 다운로드 또는 API 키 복사

## 주의사항

⚠️ **절대로 실제 설정 파일(secrets.xml, FirebaseConfig.kt, google-services.json, GoogleService-Info.plist)을 Git에 커밋하지 마세요!**

이 파일들은 `.gitignore`에 추가되어 있습니다.
