package com.works.services

import com.works.models.JWTModel
import com.works.models.LoginJWTModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DummyService {

    @POST("auth/login")
    fun login(@Body loginJWTModel: LoginJWTModel): Call<JWTModel>

}