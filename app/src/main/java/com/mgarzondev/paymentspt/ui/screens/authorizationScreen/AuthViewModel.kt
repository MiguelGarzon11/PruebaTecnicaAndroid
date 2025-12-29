package com.mgarzondev.paymentspt.ui.screens.authorizationScreen

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.mgarzondev.paymentspt.data.network.dto.AuthRequest
import com.mgarzondev.payments.data.repository.HeaderAuth
import com.mgarzondev.paymentspt.data.local.AppDataBase
import com.mgarzondev.paymentspt.data.network.RetrofitClient
import com.mgarzondev.paymentspt.data.repository.PaymentRepository
import com.mgarzondev.paymentspt.ui.screens.authorizationScreen.dto.AuthUiState
import kotlinx.coroutines.launch
import java.util.UUID

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    var uiState by mutableStateOf(AuthUiState())
        private set

    private val repository: PaymentRepository by lazy {
        val db = Room.databaseBuilder(
            getApplication<Application>().applicationContext,
            AppDataBase::class.java,
            "payments_database"
        ).build()

        val dao = db.authTransactionDao()
        val api = RetrofitClient.api

        PaymentRepository(api, dao)
    }

    fun onAmountChange(value: String) {
        uiState = uiState.copy(amount = value)
    }

    fun onCardChange(value: String) {
        uiState = uiState.copy(card = value)
    }

    fun onCardHolderChange(value: String) {
        uiState = uiState.copy(cardHolder = value)
    }

    fun onCommerceCodeChange(value: String) {
        uiState = uiState.copy(commerceCode = value)
    }

    fun onTerminalCodeChange(value: String) {
        uiState = uiState.copy(terminalCode = value)
    }

    fun authorizeTransaction() {
        if (uiState.amount.isBlank() || uiState.card.isBlank() || uiState.cardHolder.isBlank() ||
            uiState.commerceCode.isBlank() || uiState.terminalCode.isBlank()) {
            uiState = uiState.copy(
                error = "Por favor ingresa todos los campos solicitados."
            )
            return
        }

        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)

            try {
                val request = AuthRequest(
                    id = UUID.randomUUID().toString(),
                    commerceCode = HeaderAuth.DEFAULT_COMMERCE_CODE,
                    terminalCode = HeaderAuth.DEFAULT_TERMINAL_CODE,
                    amount = uiState.amount,
                    card = "1234567890123456"
                )

                val result = repository.authorizeTransaction(request)

                result.fold(
                    onSuccess = { authResponse ->
                        uiState = uiState.copy(
                            isLoading = false,
                            success = authResponse,
                            error = null
                        )
                    },
                    onFailure = { exception ->
                        uiState = uiState.copy(
                            isLoading = false,
                            success = null,
                            error = exception.message
                        )
                    }
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}