package com.busanit.ch12_network.retrofit.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.busanit.ch12_network.databinding.ActivityRetroBinding
import com.busanit.ch12_network.retrofit.RetrofitClient
import com.busanit.ch12_network.retrofit.adapter.PostAdapter
import com.busanit.ch12_network.retrofit.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetroActivity : AppCompatActivity() {
    lateinit var binding: ActivityRetroBinding
    lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        // binding.recyclerView.adapter = PostAdapter()  -> 데이터를 넣어야 하기 때문에 아래의 if 문에서 처리

        val api = RetrofitClient.instance
        api.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if(response.isSuccessful) {
                    // 네트워킹에 성공할 경우 데이터를 가져옴
                    val posts = response.body() ?: emptyList()      // 하나는 nullable 이고 다른 하나는 아니기 때문에 null 처리 필요
                    // 리사이클러뷰 어댑터 매개변수를 통해 데이터 전달 + 어댑터 연결
                    adapter = PostAdapter(posts)
                    binding.recyclerView.adapter = adapter
                } else {
                    handleServerError(response)     // 오류 처리 함수 호출
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                // 실패 처리
            }
        })

        // Result API 설정
        val activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                adapter.notifyDataSetChanged()      // 리사이클러뷰 데이터셋 갱신
                binding.recyclerView.scrollToPosition(0)  // 최상단으로 스크롤
            }

        // 버튼을 클릭하면 글 작성 액티비티로
        binding.buttonCreate.setOnClickListener {
            val intent = Intent(this, NewPostActivity::class.java)
            // startActivity(intent)  // 결과 반환하지 않을 시
            activityResultLauncher.launch(intent)   // 액티비티 결과 반환
        }
    }

    // 레트로핏 오류 처리
    // 응답은 하였으나 성공(200번대)가 아닌 경우
    private fun handleServerError(response: Response<*>) {
        when (response.code()) {
            400 -> Log.d("mylog", "400 Bad Request ${response.message()}")
            401 -> Log.d("mylog", "401 Unauthorized ${response.message()}")
            403 -> Log.d("mylog", "403 Forbidden ${response.message()}")
            404 -> Log.d("mylog", "404 Not Found ${response.message()}")
            500 -> Log.d("mylog", "500 Server Error ${response.message()}")
            else -> Log.d("mylog", "Response Error ${response.message()}")
        }
    }

    // 네트워크 요청 자체가 실패한 경우 핸들러
    private fun handleNetworkError(t: Throwable) {
        Log.d("mylog", "Network Error ${t.message}")
        Toast.makeText(this, "네트워크 요청 실패", Toast.LENGTH_SHORT).show()
    }
}