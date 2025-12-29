package com.mgarzondev.paymentspt.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mgarzondev.paymentspt.data.local.dao.AuthTransactionDao
import com.mgarzondev.paymentspt.data.local.entities.AuthTransactionEntity

@Database(entities = [AuthTransactionEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun authTransactionDao(): AuthTransactionDao
}