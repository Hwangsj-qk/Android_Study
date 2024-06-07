package com.busanit.ch13_login.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.busanit.ch13_login.R
import com.busanit.ch13_login.databinding.ActivityTitleBinding

class TitleActivity : AppCompatActivity() {
    lateinit var binding:ActivityTitleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTitleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val token = getToken()

        // 토큰이 없는 경우 타이들 화면 진행
        if(token.isNullOrEmpty()) {
            // 로그인 버튼을 클릭하면 LoginActivity로 이동
            binding.buttonLogin.setOnClickListener {
                startActivity(Intent(this, LoginActivity::class.java))
            }

            // 회원가입 버튼을 클릭하면 RegisterActivity로 이동
            binding.buttonRegister.setOnClickListener {
                startActivity(Intent(this, RegisterActivity::class.java))
            }
        } else {
            // 토큰이 존재하면 MainActivity로 이동 -> 토큰이 저장되어 있으면 재로그인할 필요가 없음(로그인 정보가 저장되어 있는 경우)
            startActivity(Intent(this@TitleActivity, MainActivity::class.java))
            finish()    // TitleActivity 종료
        }

    }

    // 토큰을 가져오는 메서드
    private fun getToken() :String {
        val sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE)
        return sharedPreferences.getString("token", null) ?: ""
    }
}