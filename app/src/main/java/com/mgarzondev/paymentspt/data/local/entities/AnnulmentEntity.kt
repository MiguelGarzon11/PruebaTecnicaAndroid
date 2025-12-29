package com.mgarzondev.paymentspt.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth_transactions")
data class AnnulmentEntity(
    @PrimaryKey val receiptId: String,
)