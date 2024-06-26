package com.busanit.ch13_login.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch13_login.RetrofitClient
import com.busanit.ch13_login.adapter.ViewPagerAdapter
import com.busanit.ch13_login.databinding.ActivityMainBinding
import com.busanit.ch13_login.model.Test
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.*


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 저장된 토큰을 보호된 리소스 요청 시 사용
        // val token = sharedPreferences.getString("token", "")?:""

        // test()      // 리팩토링

        // 인증 요청시 HTTP 헤더에 "Bearer {jwt_token}" 요청
        // callProtect("Bearer $token")



        setupViewPage()     // 뷰페이저 설정


    }

    // 뷰 페이지 설정 함수
    private fun setupViewPage() {
        binding.viewPager.adapter = ViewPagerAdapter(this)

        val tabTitles = listOf("홈", "게시판", "설정")

        TabLayoutMediator(binding.tabLayout, binding.viewPager) {
            tab, position -> tab.text = tabTitles[position]
        }.attach()
    }



    // 보호된 자원 네트워크 요청 함수 : 403번 (금지된 응답, Forbidden, 자원 확인 불가 -> 회원가입과 로그인 과정 필요)
    // 토큰과 함께 요청 -> 200번 ok 출력
    private fun callProtect(token:String) {
        val api = RetrofitClient.api

        api.protect(token).enqueue(object : Callback<Test> {
            // 응답이 있는 경우
            override fun onResponse(call: Call<Test>, response: Response<Test>) {
                // 응답 코드가 200번ㄷ
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "응답성공", Toast.LENGTH_SHORT).show()
                    Log.d("mylog", "onResponse: ${response.code()}, ${response.body()}")
                } else {
                    // 응답하였지만 400~500 번대인 경우
                    Toast.makeText(
                        this@MainActivity,
                        "${response.code()}, ${response.body()}",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("mylog", "onResponse: ${response.code()}, ${response.body()}")
                }
            }

            // 네트워크 요청 실패
            override fun onFailure(call: Call<Test>, t: Throwable) {
                Toast.makeText(this@MainActivity, "요청 실패 ${t.message}", Toast.LENGTH_SHORT).show()
                Log.d("mylog", "${t.message}")
            }

        })
    }

    // 네트워크 요청 테스트 함수 : 200번대 응답과 함께 자원 응답
    private fun callTest() {
        val api = RetrofitClient.api
        api.test().enqueue(object : Callback<Test> {
            // 응답이 있는 경우
            override fun onResponse(call: Call<Test>, response: Response<Test>) {
                // 응답 코드가 200번ㄷ
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "응답성공", Toast.LENGTH_SHORT).show()
                    Log.d("mylog", "onResponse: ${response.code()}, ${response.body()}")
                } else {
                    // 응답하였지만 400~500 번대인 경우
                    Toast.makeText(
                        this@MainActivity,
                        "${response.code()}, ${response.body()}",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("mylog", "onResponse: ${response.code()}, ${response.body()}")
                }
            }

            // 네트워크 요청 실패
            override fun onFailure(call: Call<Test>, t: Throwable) {
                Toast.makeText(this@MainActivity, "요청 실패 ${t.message}", Toast.LENGTH_SHORT).show()
                Log.d("mylog", "${t.message}")
            }

        })
    }
}