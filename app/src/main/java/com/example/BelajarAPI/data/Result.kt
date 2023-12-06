package com.example.BelajarAPI.data

sealed class Result<out R> private constructor() {

    data class Success<out T>(val data: T) : com.example.BelajarAPI.data.Result<T>()
    data class Error(val error: String) : com.example.BelajarAPI.data.Result<Nothing>()
    object Loading : com.example.BelajarAPI.data.Result<Nothing>()

}
