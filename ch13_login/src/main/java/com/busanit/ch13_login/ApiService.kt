package com.busanit.ch13_login

import com.busanit.ch13_login.model.Test
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/test")
    fun test(): Call<Test>
}