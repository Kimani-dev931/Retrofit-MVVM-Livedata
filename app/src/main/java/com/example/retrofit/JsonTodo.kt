package com.example.retrofit

data class JsonTodo(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)