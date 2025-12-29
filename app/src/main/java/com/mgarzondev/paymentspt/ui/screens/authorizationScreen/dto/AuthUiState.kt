package com.mgarzondev.paymentspt.ui.screens.authorizationScreen.dto

import com.mgarzondev.paymentspt.data.network.dto.AuthResponse

data class AuthUiState (
    val amount: String = "",
    val card: String = "",
    val cardHolder: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: AuthResponse? = null,
    val commerceCode: String = "",
    val terminalCode: String = ""
)