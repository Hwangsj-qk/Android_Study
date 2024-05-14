package com.busanit.ch03_resource

import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Resource7Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_resource7)

        // 기기의 가로 세로 크기 가져오기
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // API 버전 30 이상인 경우
            val windowMetrics = windowManager.currentWindowMetrics
            val width = windowMetrics.bounds.width()
            val height = windowMetrics.bounds.height()
            Log.d("mylog", "기기 너비 : $width, 기기 높이; $height")
        } else {
            // API 버전 30 이하인 경우
            val display = windowManager.defaultDisplay
            val displayMetrics = DisplayMetrics()
            display.getRealMetrics(displayMetrics)
            val width = displayMetrics.widthPixels
            val height = displayMetrics.heightPixels
            Log.d("mylog", "기기 너비: $width, 기기 높이: $height")
        }

    }
}