package com.busanit.ch13_login.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.busanit.ch13_login.R
import com.busanit.ch13_login.RetrofitClient
import com.busanit.ch13_login.databinding.ActivityLoginBinding
import com.busanit.ch13_login.databinding.ActivityTitleBinding
import com.busanit.ch13_login.model.LoginResponse
import com.busanit.ch13_login.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            val username = binding.EditUsername.text.toString()
            val password = binding.EditPassword.text.toString()
            val user = User(username, password)



            RetrofitClient.api.login(user).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    // 성공한 경우 JWT 토큰을 확인
                    if (response.isSuccessful) {
                        // 로그
                        Toast.makeText(this@LoginActivity, "로그인에 성공하였습니다. ", Toast.LENGTH_SHORT).show()
                        Log.d("mylog", "onResponse: ${response.code()}, ${response.body()} ")

                        // 반환된 JWT 토큰을 sharedPreferences 에 저장
                        val token = response.body()?.jwt ?: ""      // 토큰

                        val sharedPreferences =
                            getSharedPreferences("app_pref", Context.MODE_PRIVATE)
                        sharedPreferences.edit()
                            .putString("token", token)                  // 토큰
                            .putString("username", username)            // 사용자 이름
                            .apply()

                        // 로그인 성공하면 메인 화면을 진입
                        startActivity(Intent(this@LoginActivity, LoginActivity::class.java))
                        finish()    // 로그인 액티비티 종료

                    } else {
                        Toast.makeText(this@LoginActivity, "로그인에 실패하였습니다. ", Toast.LENGTH_SHORT).show()
                        Log.d("mylog", "onResponse: ${response.code()}, ${response.body()} ")
                    }

                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "로그인에 실패하였습니다. ", Toast.LENGTH_SHORT).show()
                    Log.d("mylog", "onFailure: ${t.message}")
                }
            })
        }

    }
}