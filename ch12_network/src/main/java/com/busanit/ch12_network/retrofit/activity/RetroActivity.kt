package com.busanit.ch12_network.retrofit.activity

import android.content.Intent
import android.os.Bundle
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
                    binding.recyclerView.adapter = PostAdapter(posts)
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                // 실패 처리
            }
        })


        // Result API
        val ActivityResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                adapter.notifyDataChanged()         // 리사이클러뷰 데이터셋 갱신
                binding.recyclerView.scrollToPosition(0)        // 최상단으로 스크롤
            }

        // 버튼을 클릭하면 글 작성 액티비티로 넘어감
        binding.buttonCreate.setOnClickListener {
            val intent = Intent(this, NewPostActivity::class.java)
            // startActivity(intent)
            activityResultLauncher.launch(intent)

        }

    }
}