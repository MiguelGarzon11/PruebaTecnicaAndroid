package com.mgarzondev.paymentspt.ui.screens.transactionListScreen.dto

data class TransactionData(
    val cardHolder: String,
    val amount: String,
    val receiptId: String,
    val timestamp: Long,
    val nullable: Boolean
)
