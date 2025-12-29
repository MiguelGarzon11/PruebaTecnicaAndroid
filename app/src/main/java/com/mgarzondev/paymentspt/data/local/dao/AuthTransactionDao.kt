package com.mgarzondev.paymentspt.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mgarzondev.paymentspt.data.local.entities.AuthTransactionEntity

@Dao
interface AuthTransactionDao {

    @Insert
    suspend fun insert(transaction: AuthTransactionEntity)

    @Query("SELECT * FROM auth_transactions ORDER BY timestamp DESC")
    suspend fun getAllTransactions(): List<AuthTransactionEntity>

    @Query("SELECT * FROM auth_transactions WHERE receiptId = :receiptId")
    suspend fun getTransactionById(receiptId: String): AuthTransactionEntity

    @Query("UPDATE auth_transactions SET nullable = :newValue WHERE 'rrn' = :rrn")
    suspend fun updateAuthorizationStatus(rrn: String, newValue: Boolean?)
}