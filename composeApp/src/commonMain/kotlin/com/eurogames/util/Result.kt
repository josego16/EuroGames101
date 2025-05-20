package com.eurogames.util

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(
        val message: String,
        val cause: Throwable? = null,
        val type: ErrorType = ErrorType.Unknown
    ) : Result<Nothing>()

    enum class ErrorType {
        Network,
        Server,
        Client,
        Timeout,
        Unauthorized,
        NotFound,
        Validation,
        Unknown
    }
}
