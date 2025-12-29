package com.mgarzondev.paymentspt.data.network.dto

data class AuthResponse (
    val receiptId: String,
    val rrn: String,
    val statusCode: String,
    val statusDescription: String
)