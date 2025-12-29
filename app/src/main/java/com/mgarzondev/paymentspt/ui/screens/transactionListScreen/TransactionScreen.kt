package com.mgarzondev.paymentspt.ui.screens.transactionListScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import com.mgarzondev.paymentspt.ui.screens.transactionListScreen.TransactionCard
import com.mgarzondev.paymentspt.data.local.AppDataBase

@Composable
fun TransactionScreen() {
    val context = LocalContext.current

    val viewModel = remember {
        val db = Room.databaseBuilder(
            context.applicationContext,
            AppDataBase::class.java,
            "payments_database"
        ).build()

        TransactionViewModel(db.authTransactionDao())
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Transacciones",
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Todas tus transacciónes registradas",
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(modifier = Modifier.height(16.dp))

        LaunchedEffect(Unit) {
            viewModel.loadTransactions()
        }

        LazyColumn {
            items(viewModel.transactions.value) { transaction ->
                TransactionCard(Detail = transaction)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}