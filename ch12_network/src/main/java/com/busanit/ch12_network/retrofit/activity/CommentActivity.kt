package com.busanit.ch12_network.retrofit.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.busanit.ch12_network.R
import com.busanit.ch12_network.databinding.ActivityCommentBinding
import com.busanit.ch12_network.databinding.ActivityRetroBinding
import com.busanit.ch12_network.retrofit.RetrofitClient
import com.busanit.ch12_network.retrofit.adapter.CommentAdapter
import com.busanit.ch12_network.retrofit.model.Comment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentActivity : AppCompatActivity() {
    lateinit var binding: ActivityCommentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        // 호출한 intent에서 postId 값을 가져옴 (없으면 -1)
        val postId = intent.getIntExtra("postId", -1)

        // 데이터 네트워크 호출
        val api = RetrofitClient.instance

        // 비동기 방식으로 댓글 데이터 (postId) 기준으로 가져오기
        api.getCommentsByPostId(postId).enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful) {
                    val comments = response.body() ?: emptyList()
                    binding.recyclerView.adapter = CommentAdapter(comments)
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                // 네트워크 실패 처리
            }

        })
    }
}