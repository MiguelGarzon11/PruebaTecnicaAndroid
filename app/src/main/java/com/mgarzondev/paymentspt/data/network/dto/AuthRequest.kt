package com.mgarzondev.paymentspt.data.network.dto

data class AuthRequest (
    val id: String,
    val commerceCode: String,
    val terminalCode: String,
    val amount: String,
    val card: String,
    val cardHolder: String? = null
)
