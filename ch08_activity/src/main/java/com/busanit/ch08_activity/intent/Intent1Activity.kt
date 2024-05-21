package com.busanit.ch08_activity.intent

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.busanit.ch08_activity.R
import com.busanit.ch08_activity.databinding.ActivityIntent1Binding
import com.busanit.ch08_activity.databinding.ActivityIntent2Binding

class Intent1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityIntent1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Intent로부터 데이터를 가져옴
        val extra1 = intent.getStringExtra("Extra1")        // 문자열, 기본값 null
        val extra2 = intent.getIntExtra("Extra2", 0)    // 정수, 기본값

        binding.textView.text = extra1


    }
}