package com.mgarzondev.paymentspt.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.mgarzondev.paymentspt.data.network.api.PaymentsApi

object RetrofitClient {
    private const val IP_COMPUTADOR: String = "192.168.1.9"
    private const val LOCAL: String = "localhost"

    private const val BASE_URL = "http://$IP_COMPUTADOR:8080/"

    val api: PaymentsApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PaymentsApi::class.java)
    }
}