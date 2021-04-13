package com.thepianodentist.kettlestub.api

import com.thepianodentist.kettlestub.data.ApiResponse
import com.thepianodentist.kettlestub.data.UserResponse
import com.thepianodentist.kettlestub.data.PostUserRequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface Service {

    @Headers("Content-Type: application/json")
    @POST("/register/")
    suspend fun postUser(@Body body: PostUserRequestBody): ApiResponse<UserResponse>

    @Headers("Content-Type: application/json")
    @POST("/confirm/")
    suspend fun postConfirm()


}
