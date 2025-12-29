package com.mgarzondev.paymentspt.ui.screens.searchTransactionScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgarzondev.paymentspt.data.local.dao.AuthTransactionDao
import com.mgarzondev.paymentspt.data.local.entities.AuthTransactionEntity
import com.mgarzondev.paymentspt.ui.screens.transactionListScreen.dto.TransactionData
import kotlinx.coroutines.launch

class SearchViewModel(private val dao: AuthTransactionDao): ViewModel() {
        var transactionResult by mutableStateOf<AuthTransactionEntity?>(null)
        private set

        var isLoading by mutableStateOf(false)
        private set

        fun loadTransactionById(receiptId: String) {
            viewModelScope.launch {
                isLoading = true
                val result = dao.getTransactionById(receiptId)

                transactionResult = result
                isLoading = false
            }
        }
}