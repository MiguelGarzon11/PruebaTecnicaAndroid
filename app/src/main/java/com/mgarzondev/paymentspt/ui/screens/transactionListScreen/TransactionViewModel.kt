package com.mgarzondev.paymentspt.ui.screens.transactionListScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgarzondev.paymentspt.data.local.dao.AuthTransactionDao
import com.mgarzondev.paymentspt.ui.screens.transactionListScreen.dto.TransactionData
import kotlinx.coroutines.launch

class TransactionViewModel(private val dao: AuthTransactionDao) : ViewModel() {

    var transactions = mutableStateOf<List<TransactionData>>(emptyList())
        private set

    fun loadTransactions() {
        viewModelScope.launch {
            val list = dao.getAllTransactions()
            transactions.value = list.map { entity ->
                TransactionData(
                    cardHolder = entity.cardHolder ?: "Desconocido",
                    amount = entity.amount,
                    receiptId = entity.receiptId,
                    timestamp = entity.timestamp,
                    nullable = false
                )
            }
        }
    }
}