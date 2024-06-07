package com.busanit.ch13_login

import com.busanit.ch13_login.model.LoginResponse
import com.busanit.ch13_login.model.Test
import com.busanit.ch13_login.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    // 네트워크 테스트용 API
    @GET("/test")
    fun test(): Call<Test>


    // 스프링 Security로 보호되어 있는 자원 테스트용 API
    @GET("/protect")
    fun protect(): Call<Test>

    // 회원 가입 API : 본문에 User 정보를 담아 User 정보 리턴 (백엔드와 요청을 똑같아야 함)
    @POST("/jwt/register")
    fun register(@Body user: User) : Call<User>

    // 로그인 API
    @POST("/jwt/auth")
    fun login(@Body user: User) : Call<LoginResponse>

}