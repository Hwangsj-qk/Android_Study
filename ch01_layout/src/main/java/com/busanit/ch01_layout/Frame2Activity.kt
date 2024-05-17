package com.busanit.ch01_layout

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.busanit.ch01_layout.databinding.ActivityFrame2Binding

class Frame2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 뷰 바인딩
        val binding = ActivityFrame2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Frame 레이아웃에 겹쳐있는 요소의 visibility를 변경하여 탭 기능 추가
        binding.btnRed.setOnClickListener {
            binding.layoutRed.visibility = View.VISIBLE
            binding.layoutGreen.visibility = View.INVISIBLE
            binding.btnBlue.visibility = View.GONE
        }

        binding.btnGreen.setOnClickListener {
            binding.layoutRed.visibility = View.GONE
            binding.layoutGreen.visibility = View.VISIBLE
            binding.layoutBlue.visibility = View.GONE
        }

        binding.btnBlue.setOnClickListener {
            binding.layoutRed.visibility = View.GONE
            binding.layoutGreen.visibility = View.GONE
            binding.layoutBlue.visibility = View.VISIBLE
        }

    }
}