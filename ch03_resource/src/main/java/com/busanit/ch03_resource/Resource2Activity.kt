package com.busanit.ch03_resource

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

val TAG = "mylog"
class Resource2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource2)

        // 코드에서 문자열 사용하기
        val string = getString(R.string.welcome_message)
        Log.d(TAG, string)

        // 텍스트 뷰에 리소스 문자열 적용
        val textView = findViewById<TextView>(R.id.textView1)
        textView.text = string

        // 텍스트 뷰에 리소스 크기 적용
        textView.textSize = resources.getDimension(R.dimen.text_size)

        // 텍스트 뷰에 리소스 색깔 적용
        textView.setTextColor(ResourcesCompat.getColor(resources, R.color.primary_color, null))
    }

}