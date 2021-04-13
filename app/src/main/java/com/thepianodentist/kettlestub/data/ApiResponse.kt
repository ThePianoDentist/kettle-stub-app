package com.thepianodentist.kettlestub.data

data class ApiResponse<T>(
    val Status: String?,
    val Error: String?,
    val Data: T?
)

data class UserResponse (
    val UserId: String,
)