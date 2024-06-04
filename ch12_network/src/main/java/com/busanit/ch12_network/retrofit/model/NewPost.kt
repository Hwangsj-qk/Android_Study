package com.busanit.ch12_network.retrofit.model

// POST 요청, 데이터를 추가하는 요청을 위한 클래스
data class NewPost(
    val userId: Int,
    val title: String,
    val body: String
)
