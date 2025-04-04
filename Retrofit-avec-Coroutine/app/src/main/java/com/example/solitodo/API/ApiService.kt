package com.example.solitodo.API

import com.example.solitodo.Data.Todo
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("todos/1")
    suspend fun getTodo(): Todo  // ✅ Fonction suspendue
}