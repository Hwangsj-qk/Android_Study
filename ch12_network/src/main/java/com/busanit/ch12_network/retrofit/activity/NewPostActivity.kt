package com.busanit.ch12_network.retrofit.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.busanit.ch12_network.R
import com.busanit.ch12_network.databinding.ActivityNewPostBinding
import com.busanit.ch12_network.retrofit.RetrofitClient
import com.busanit.ch12_network.retrofit.model.NewPost
import com.busanit.ch12_network.retrofit.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "NewPostLog"
class NewPostActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val api = RetrofitClient.instance

        binding.run {
            buttonSubmit.setOnClickListener {
                // 사용자로부터 데이터를 입력받아 데이터 객체로 생성
                val title = editTextTitle.text.toString()
                val body = editTextBody.text.toString()
                val newPost = NewPost(userId = 1, title = title, body = body)

                api.createPost(newPost).enqueue(object : Callback<Post> {
                    override fun onResponse(call: Call<Post>, response: Response<Post>) {
                        if(response.isSuccessful) {
                            Toast.makeText(this@NewPostActivity, "성공적으로 글을 등록했습니다. ${response.message()}", Toast.LENGTH_SHORT).show()
                            Log.d(TAG, "onResponse: ${response.body()}")
                            finish() // 새 글 작성 성공시, Activity 종료, 이전으로 돌아감(back stack)
                        }else {     // 서버 응답이 안되는 경우, 응답이 400~500번대가 발생함
                            Toast.makeText(this@NewPostActivity, "네트워크 응답 실패 ${response.message()}", Toast.LENGTH_SHORT).show()
                        }

                    }

                    override fun onFailure(call: Call<Post>, t: Throwable) {
                        // 실패처리 (잘못된 API로 인해 작성 자체가 안되는 상황 -> Response 응답 자체가 없음)
                        // 네트워크 요청 자체가 안되어 예외(t)를 던짐
                        Toast.makeText(this@NewPostActivity, "네트워크 요청 실패 ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }



    }
}