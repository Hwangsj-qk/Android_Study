package com.busanit.ch08_activity.stack

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.busanit.ch08_activity.R
import com.busanit.ch08_activity.databinding.ActivityTwoBinding

class TwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {

            oneTextView.setOnClickListener {
                startActivity(Intent(this@TwoActivity, OneActivity::class.java))
            }

            twoTextView.setOnClickListener {
                // SINGLE_TOP : 동일한 액티비티가 스택의 맨 위에 존재하면 기존 인스턴스 재사용, 불필요한 인스턴스 생성 방지
                val intent = Intent(this@TwoActivity, TwoActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)    // 플래그 설정
                startActivity(intent)
            }

            threeTextView.setOnClickListener {
                startActivity(Intent(this@TwoActivity, ThreeActivity::class.java))
            }
        }

    }
}