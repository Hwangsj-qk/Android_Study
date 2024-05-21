# 안드로이드
## 플랫폼 아키텍쳐
- [플랫폼 아키텍쳐] (https://developer.android.com/guide/platform?hl=ko)
- 구성
  - 리눅스 커널
  - 하드웨어 추상화 계층(HAL)
  - 안드로이드 런타임 (ART)
  - Java API Framework
  - 시스템 앱

## 코드명, 버전, API 레벨
- [코드명, 버전, API 레벨] (https://source.android.com/docs/setup/about/build-numbers?hl=ko)

## 안드로이드 프로젝트 디렉토리 구조
```xml
MyAndroidApp/
 ├── .idea/                      # 프로젝트 설정 파일
 ├── app/                        # 메인 애플리케이션 모듈
 │    ├── build/                 # 빌드된 파일들
 │    ├── src/                   # 소스 코드 디렉터리
 │    │   ├── main/              # 메인 소스 파일
 │    │   │   ├── java/          # Java/Kotlin 소스 코드
 │    │   │   │   └── com/
 │    │   │   │       └── example/
 │    │   │   │           └── myandroidapp/
 │    │   │   │               └── MainActivity.java  # 메인 액티비티
 │    │   │   ├── res/           # 리소스 파일들
 │    │   │   │   ├── drawable/  # 이미지 파일
 │    │   │   │   ├── layout/    # 레이아웃 XML 파일
 │    │   │   │   ├── mipmap/    # 앱 아이콘
 │    │   │   │   ├── values/    # 문자열, 색상, 스타일 등
 │    │   │   └── AndroidManifest.xml  # 애플리케이션 매니페스트 파일
 │    │   ├── androidTest/       # 통합 테스트 소스
 │    │   └── test/              # 유닛 테스트 소스
 │    └── build.gradle           # 모듈별 빌드 파일
 ├── build/                      # 최상위 빌드된 파일들
 ├── gradle/                     # 그레이들 설정 디렉터리
 ├── .gitignore                  # 깃 무시 파일
 ├── build.gradle                # 최상위 빌드 파일
 └── settings.gradle             # 프로젝트 포함 모듈 설정
```

## 안드로이드 앱 컴포넌트
1. Activity : 앱의 화면을 담당하는 기본 구성요소
2. Intent : 액티비티 간의 전환 또는 외부 앱과의 데이터 교환을 위한 메시지
3. Service : 백그라운드에서 동작하는 작업 단위
4. Broadcast Receiver : 시스템 또는 다른 앱에서 발생하는 이벤트 수신
5. Content Provider : 앱 간 데이터 공유를 위한 인터펭스

## 안드로이드 개발 환경
- 안드로이드 스튜디오 (IDE)
- 안드로이드 SDK : 안드로이드 앱을 개발하기 위한 도구 및 라이브러리 모음
- 에뮬레이터(Emulator, AVD) : 안드로이드 앱을 테스트할 수 잇는 가상 디바이스 

## XML(eXtensible Markup Language)
- 데이터를 구조화하고 나타내는 마크업 언어
- 사람이 이해할 수 있는 구조
- 기계가 해석할 수 있는 데이터 형식
- 반정형 데이터

### XML 구성요소
1. 선언부
2. 태그(Tag) : 데이터를 감싸는 구조화된 요소 → 시작태그와 종료태그의 쌍으로 이루어짐(열면 닫아야 함)
```xml
    <data>데이터내용(콘텐츠)</data>
```
3. 엘리먼트(Element) : 시작 태그와 종료 태그 사이에 있는 콘텐츠
    ▶ 엘리먼트(요소) = 태그 + 콘텐츠(내용)

## 권한

### 1. 일반 권한 (Normal Permissions)

일반 권한은 민감하지 않은 데이터와 기능에 접근하는 데 필요한 권한입니다. 이 권한들은 앱 설치 시 자동으로 부여되며, 사용자에게 별도의 허가를 요청하지 않아도 됩니다. 일반 권한은 사용자 개인 정보나 장치 보안에 큰 영향을 미치지 않는 기능에 사용됩니다.

- 예시

- 인터넷 접근 권한 (`INTERNET`)
- 네트워크 상태 확인 권한 (`ACCESS_NETWORK_STATE`)
- Wi-Fi 상태 확인 권한 (`ACCESS_WIFI_STATE`)

- 선언 방법

일반 권한은 `AndroidManifest.xml` 파일에 선언합니다.
예를 들어, 인터넷 접근 권한을 선언하려면 아래와 같이 작성합니다.

```xml
<manifest xmlns:android="<http://schemas.android.com/apk/res/android>"
    package="com.busanit.myfirstapp">

    <uses-permission android:name="android.permission.INTERNET" />

    <!-- Other declarations -->

</manifest>
```

### 2. 위험 권한 (Dangerous Permissions)

위험 권한은 민감한 데이터나 장치 기능에 접근할 때 필요한 권한입니다. 이러한 권한들은 사용자의 개인 정보나 장치 보안에 큰 영향을 미칠 수 있기 때문에, 사용자가 명시적으로 허가해야 합니다. 앱 실행 중에 사용자가 허가를 승인하거나 거부할 수 있습니다.

- 예시

    - 위치 정보 접근 권한 (`ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`)
    - 카메라 사용 권한 (`CAMERA`)
    - 연락처 접근 권한 (`READ_CONTACTS`, `WRITE_CONTACTS`)
    - 저장소 접근 권한 (`READ_EXTERNAL_STORAGE`, `WRITE_EXTERNAL_STORAGE`)

### 권한 요청 처리 절차

1. 권한 선언: `AndroidManifest.xml` 파일에 필요한 권한을 선언합니다.
2. 권한 확인: 앱 실행 시 `ContextCompat.checkSelfPermission()` 메서드로 권한을 확인합니다.
3. 권한 요청: 권한이 부여되지 않은 경우 `ActivityCompat.requestPermissions()` 메서드로 사용자에게 권한을 요청합니다.
4. 결과 처리: `onRequestPermissionsResult()` 메서드에서 권한 요청 결과를 처리합니다.

#### 선언 및 요청 방법

1. 권한 선언: `AndroidManifest.xml` 파일에 위험 권한을 선언합니다.

    ```xml
    <manifest xmlns:android="<http://schemas.android.com/apk/res/android>"
        package="com.busanit.myfirstapp">
    
        <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    
        <!-- Other declarations -->
    
    </manifest>
    
    ```

2. 권한 요청: 앱 실행 중에 권한을 요청합니다. 예를 들어, 위치 권한을 요청하려면 아래와 같이 작성합니다.

    ```kotlin
    import android.Manifest
    import android.content.pm.PackageManager
    import androidx.core.app.ActivityCompat
    import androidx.core.content.ContextCompat
    
    class MainActivity : AppCompatActivity() {
    
        private val LOCATION_PERMISSION_REQUEST_CODE = 1
    
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
    
            // 위치 권한 요청
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
    
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE)
            }
        }
    
        override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // 권한이 승인된 경우
                    // 위치 정보를 얻는 코드 작성
                    Toast.makeText(this, "위치 권한이 승인되었습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    // 권한이 거부된 경우
                    Toast.makeText(this, "위치 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    
    ```


### 대표적인 안드로이드 시스템 권한

1. 위치 권한 (Location Permissions)
    - `ACCESS_FINE_LOCATION`: GPS를 통해 정확한 위치 정보를 접근할 수 있는 권한
    - `ACCESS_COARSE_LOCATION`: Wi-Fi 및 기지국 정보를 통해 대략적인 위치 정보를 접근할 수 있는 권한
2. 카메라 권한 (Camera Permissions)
    - `CAMERA`: 카메라 하드웨어에 접근하여 사진 및 비디오를 촬영할 수 있는 권한
3. 연락처 권한 (Contacts Permissions)
    - `READ_CONTACTS`: 사용자의 연락처 데이터를 읽을 수 있는 권한
    - `WRITE_CONTACTS`: 사용자의 연락처 데이터를 수정할 수 있는 권한
    - `GET_ACCOUNTS`: 기기에 등록된 계정 목록에 접근할 수 있는 권한
4. 전화 권한 (Phone Permissions)
    - `READ_PHONE_STATE`: 전화 상태와 관련된 정보를 읽을 수 있는 권한
    - `CALL_PHONE`: 전화를 걸 수 있는 권한
    - `READ_CALL_LOG`: 전화 기록을 읽을 수 있는 권한
    - `WRITE_CALL_LOG`: 전화 기록을 수정할 수 있는 권한
    - `ADD_VOICEMAIL`: 음성 메일을 추가할 수 있는 권한
    - `USE_SIP`: SIP 통화를 사용할 수 있는 권한
    - `PROCESS_OUTGOING_CALLS`: 발신 전화를 처리할 수 있는 권한
5. 저장소 권한 (Storage Permissions)
    - `READ_EXTERNAL_STORAGE`: 외부 저장소의 파일을 읽을 수 있는 권한
    - `WRITE_EXTERNAL_STORAGE`: 외부 저장소의 파일을 쓸 수 있는 권한
6. 마이크 권한 (Microphone Permissions)
    - `RECORD_AUDIO`: 마이크를 통해 오디오를 녹음할 수 있는 권한
7. SMS 권한 (SMS Permissions)
    - `SEND_SMS`: SMS 메시지를 보낼 수 있는 권한
    - `RECEIVE_SMS`: SMS 메시지를 받을 수 있는 권한
    - `READ_SMS`: SMS 메시지를 읽을 수 있는 권한
    - `RECEIVE_WAP_PUSH`: WAP 푸시 메시지를 받을 수 있는 권한
    - `RECEIVE_MMS`: MMS 메시지를 받을 수 있는 권한
8. 캘린더 권한 (Calendar Permissions)
    - `READ_CALENDAR`: 사용자의 캘린더 데이터를 읽을 수 있는 권한
    - `WRITE_CALENDAR`: 사용자의 캘린더 데이터를 수정할 수 있는 권한
9. 센서 권한 (Sensors Permissions)
    - `BODY_SENSORS`: 심박수와 같은 신체 센서 데이터를 접근할 수 있는 권한
10. 위치에 따른 네트워크 상태 권한 (Network State Permissions)
    - `ACCESS_NETWORK_STATE`: 네트워크 연결 상태를 확인할 수 있는 권한
    - `ACCESS_WIFI_STATE`: Wi-Fi 연결 상태를 확인할 수 있는 권한
    - `CHANGE_WIFI_STATE`: Wi-Fi 연결 상태를 변경할 수 있는 권한
11. 기타 권한 (Other Permissions)
    - `INTERNET`: 인터넷에 접근할 수 있는 권한
    - `BLUETOOTH`: 블루투스 연결을 사용할 수 있는 권한
    - `BLUETOOTH_ADMIN`: 블루투스 설정을 변경할 수 있는 권한
    - `NFC`: NFC 하드웨어에 접근할 수 있는 권한
    - `VIBRATE`: 장치를 진동시킬 수 있는 권한
    - `WAKE_LOCK`: CPU가 슬립 모드로 들어가지 않도록 할 수 있는 권한

### 공식 문서 참조

- [Permissions Overview](https://developer.android.com/guide/topics/permissions/overview)
- [Requesting Permissions at Run Time](https://developer.android.com/training/permissions/requesting)
- [Manifest.permission](https://developer.android.com/reference/android/Manifest.permission)

---