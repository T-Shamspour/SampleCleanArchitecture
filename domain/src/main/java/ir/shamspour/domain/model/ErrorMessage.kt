package ir.shamspour.domain.model

import ir.shamspour.domain.util.HttpErrors


data class ErrorMessage(
    val message: String?,
    val status: HttpErrors,
)