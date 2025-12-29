package com.mgarzondev.paymentspt.ui.screens.searchTransactionScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchForm(viewModel: SearchViewModel) {

    var receiptId by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        OutlinedTextField(
            value = receiptId,
            onValueChange = { receiptId = it },
            label = { Text("Número de recibo") },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.loadTransactionById(receiptId) },
            modifier = Modifier.fillMaxWidth(),
            enabled = receiptId.isNotBlank()
        ) {
            Text("Buscar transacción")
        }

        Spacer(modifier = Modifier.height(24.dp))

        TransactionDetail(viewModel = viewModel)

    }
}