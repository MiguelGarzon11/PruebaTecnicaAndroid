package com.mgarzondev.paymentspt.data.network.api

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.Response
import com.mgarzondev.paymentspt.data.network.dto.AnnulmentRequest
import com.mgarzondev.paymentspt.data.network.dto.AnnulmentResponse
import com.mgarzondev.paymentspt.data.network.dto.AuthRequest
import com.mgarzondev.paymentspt.data.network.dto.AuthResponse

interface PaymentsApi {
    @POST("api/payments/authorization")
    suspend fun authorize(
        @Header("Authorization") authorization: String,
        @Body request: AuthRequest
    ): Response<AuthResponse>

    @POST("api/payments/annulment")
    suspend fun annulment(
        @Header("Authorization") authorization: String,
        @Body request: AnnulmentRequest
    ): Response<AnnulmentResponse>
}
