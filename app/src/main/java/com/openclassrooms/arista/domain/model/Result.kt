package com.openclassrooms.arista.domain.model

sealed class Result {
    data object Success : Result()
    data class Error(val exception: Exception) : Result()
}