package com.example.caculate_v3

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/users/login")
    fun loginUser(@Body requestBody: LoginRequest): Call<ApiResponse>
}