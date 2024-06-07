package com.busanit.ch13_login.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.busanit.ch13_login.R
import com.busanit.ch13_login.RetrofitClient
import com.busanit.ch13_login.adapter.ArticleAdapter
import com.busanit.ch13_login.databinding.FragmentArticleBinding
import com.busanit.ch13_login.databinding.FragmentHomeBinding
import com.busanit.ch13_login.model.Article
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleFragment : Fragment() {
    lateinit var binding: FragmentArticleBinding
    lateinit var articleAdapter: ArticleAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // requireContext() : 현재 프래그먼트와 관련된 Context 리턴
        articleAdapter = ArticleAdapter(listOf(), ::updateArticle, ::deleteArticle)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = articleAdapter

        getArticles()
    }

    // 게시글 목록 가져오기
    private fun getArticles() {
        val token = getToken()

        // 레트로핏으로 데이터 가져오기
        RetrofitClient.api.getArticles("Bearer $token").enqueue(object : Callback<List<Article>> {
            override fun onResponse(call: Call<List<Article>>, response: Response<List<Article>>) {
                if (response.isSuccessful) {
                    // 성공 응답일 경우 어댑터에 데이터 갱신
                    val articles = response.body() ?: emptyList()
                    articleAdapter.updateArticles(articles)
                } else {
                    Log.d("mylog", "onResponse: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Article>>, t: Throwable) {
                Log.d("mylog", "onFailure: ${t.message}")
            }
        })
    }

    // 수정 이벤트 핸들러
    private fun updateArticle(article: Article) {

    }

    // 삭제 이벤트 핸들러
    private fun deleteArticle(articleId: Long) {

    }

    // 토큰을 가져오는 메서드
    private fun getToken() :String {
        val sharedPreferences = activity?.getSharedPreferences("app_pref", AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences?.getString("token", null) ?: ""
    }
}