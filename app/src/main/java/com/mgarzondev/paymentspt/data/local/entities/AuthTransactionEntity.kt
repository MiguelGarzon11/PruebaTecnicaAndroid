package com.mgarzondev.paymentspt.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth_transactions")
data class AuthTransactionEntity(
    @PrimaryKey val id: String,
    val receiptId: String,
    val rrn: String,
    val cardHolder: String? = null,
    val amount: String,
    val card: String,
    val timestamp: Long,
    val nullable: Boolean
)