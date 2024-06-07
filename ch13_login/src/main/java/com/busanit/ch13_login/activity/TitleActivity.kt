package com.busanit.ch13_login.activity

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

        // 로그인 버튼을 클릭하면 LoginActivity로 이동
        binding.buttonLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // 회원가입 버튼을 클릭하면 RegisterActivity로 이동
        binding.buttonRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }
}