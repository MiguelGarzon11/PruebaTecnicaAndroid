package com.mgarzondev.paymentspt.ui.screens.searchTransactionScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.mgarzondev.paymentspt.data.local.AppDataBase

@Composable
fun SearchScreen() {

    val context = LocalContext.current

    val viewModel = remember {
        val db = Room.databaseBuilder(
            context.applicationContext,
            AppDataBase::class.java,
            "payments_database"
        ).build()

        SearchViewModel(db.authTransactionDao())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Buscar Transacción",
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Ingrese el número de recibo",
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(modifier = Modifier.height(16.dp))

        SearchForm(viewModel)
    }
}
