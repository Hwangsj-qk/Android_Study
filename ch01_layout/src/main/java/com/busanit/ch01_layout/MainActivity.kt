package com.busanit.ch01_layout

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// 애플리케이션의 첫 화면 액티비티를 정의하고 담당하는 코틀린 파일
// 안드로이드 플랫폼의 Activity 클래스를 상속

class MainActivity : AppCompatActivity() {
    // 생명주기 (Life Cycle) 생성될 때 수행되는 메서드 오버라이딩
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 상위 클래스 메서드 호출

        setContentView(R.layout.activity_main)
        // 리소스 레이아웃의 activity_main.xml 파일을 화면에 표시

    }
}