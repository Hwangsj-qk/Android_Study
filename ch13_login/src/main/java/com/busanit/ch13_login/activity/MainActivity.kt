package com.busanit.ch13_login.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch13_login.R
import com.busanit.ch13_login.RetrofitClient
import com.busanit.ch13_login.model.Test
import retrofit2.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // test()      // 리팩토링

        callProtect()


    }

    // 보호된 자원 네트워크 요청 함수 : 403번 (금지된 응답, Forbidden, 자원 확인 불가 -> 회원가입과 로그인 과정 필요)
    private fun callProtect() {
        val api = RetrofitClient.api

        api.protect().enqueue(object : Callback<Test> {
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