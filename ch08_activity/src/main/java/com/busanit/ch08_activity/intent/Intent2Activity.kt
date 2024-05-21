package com.busanit.ch08_activity.intent

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.busanit.ch08_activity.R
import com.busanit.ch08_activity.databinding.ActivityIntent2Binding

// 인텐트 결과 반환 
class Intent2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityIntent2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            val extra = intent.getStringExtra("extra_msg")
            binding.textView1.text = extra


            button1.setOnClickListener {
                val resultIntent = Intent()         // 인텐트 객체 생성
                resultIntent.putExtra("result_msg", "$extra, Result from Other Activity")
                // 데이터 삽입

                setResult(RESULT_OK, resultIntent)      // 결과 설정
                finish()        // Activity를 종료
            }

        }

    }
}