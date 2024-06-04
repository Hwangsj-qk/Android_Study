package com.busanit.ch12_network.news

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.busanit.ch12_network.R
import com.busanit.ch12_network.databinding.ActivityNewsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val api = NewsRetrofitClient.instance

        // API 호출
        api.getTopHeadlines("ff95d9d3b9854a20963e8ce18ab33e41", "kr")       // 내가 받은 API Key (https://newsapi.org/)
            .enqueue(object : Callback<NewsResponse>{
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    if(response.isSuccessful) {
                        val articles = response.body()?.articles ?: emptyList()
                        binding.recyclerView.adapter = ArticleAdapter(articles)
                    } else {
                        // 응답(성공 외) 처리
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    // 요청 실패 처리
                }
            })
    }
}