package ir.shamspour.domain.util

import ir.shamspour.domain.model.ErrorMessage


sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val error: ErrorMessage) : Result<T>()
}