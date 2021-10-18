package io.github.zagori.nytimes.models

data class ApiResponse<T>(
    val status: String,
    val copyright: String,
    val num_results: Int,
    val results: T,
    val response: T,
)
