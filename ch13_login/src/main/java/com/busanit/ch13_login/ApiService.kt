package com.busanit.ch13_login

import android.media.session.MediaSession.Token
import com.busanit.ch13_login.model.Article
import com.busanit.ch13_login.model.LoginResponse
import com.busanit.ch13_login.model.Test
import com.busanit.ch13_login.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {
    // 네트워크 테스트용 API
    @GET("/test")
    fun test(): Call<Test>


    // 스프링 Security로 보호되어 있는 자원 테스트용 API
    // JWT
    @GET("/protect")
    fun protect(@Header("Authorization") token: String): Call<Test>

    // 회원 가입 API : 본문에 User 정보를 담아 User 정보 리턴 (백엔드와 요청을 똑같아야 함)
    @POST("/jwt/register")
    fun register(@Body user: User) : Call<User>

    // 로그인 API : 본문에 User 정보를 담아 JWT 토큰 리턴
    @POST("/jwt/auth")
    fun login(@Body user: User) : Call<LoginResponse>

    // 게스글 리스트를 가져오는 API (보안, 토큰 필요)
    @GET("/articles")
    fun getArticles(@Header("Authorization") token: String) : Call<List<Article>>

    // 수정 API
    @PUT("/articles/{id}")
    fun updateArticles(@Header("Authorization") token: String, id: Long, article: Article) : Call<Article>

}