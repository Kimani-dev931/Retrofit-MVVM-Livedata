package com.example.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface `Api-interface` {
      @GET("/todos")
      suspend fun getJsonTodos(): Response<List<JsonTodo>>
}