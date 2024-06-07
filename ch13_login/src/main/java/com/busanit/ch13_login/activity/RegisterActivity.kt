package com.busanit.ch13_login.activity

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
import com.busanit.ch13_login.databinding.ActivityResigerBinding
import com.busanit.ch13_login.databinding.ActivityTitleBinding
import com.busanit.ch13_login.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityResigerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResigerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 회원가입 버튼 클릭 -> API 요청
        binding.buttonRegister.setOnClickListener {
            // 입력된 정보를 가져와 요청할 객체 생성
            val username = binding.EditUsername.text.toString()        // EditUsername에 입력된 내용 가져오기
            val password = binding.EditPassword.text.toString()        // EditPassword에 입력된 내용 가져오기
            val user = User(username, password)

            // 레트로핏으로 API 비동기 요청
            RetrofitClient.api.register(user).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if(response.isSuccessful) {
                      Toast.makeText(this@RegisterActivity, "회원가입에 성공하였습니다. ", Toast.LENGTH_SHORT).show()
                        Log.d("mylog", "onResponse: ${response.code()}, ${response.body()} ")
                        // 회원 가입 성공시 메시지 띄우고, 로그인 화면으로 이동
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    } else {
                        // 실패
                        Toast.makeText(this@RegisterActivity, "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                        Log.d("mylog", "onResponse: ${response.code()}, ${response.body()} ")
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, "회원가입에 실패하였습니다. ", Toast.LENGTH_SHORT).show()
                    Log.d("mylog", "onFailure: ${t.message}")
                }
            })
        }

    }
}