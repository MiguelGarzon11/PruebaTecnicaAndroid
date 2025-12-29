package com.mgarzondev.paymentspt.ui.screens.searchTransactionScreen

import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TransactionDetail(viewModel: SearchViewModel) {

    val transaction = viewModel.transactionResult
    val isLoading = viewModel.isLoading

    when {
        isLoading -> {
            CircularProgressIndicator(
                modifier = Modifier.padding(16.dp)
            )
        }

        transaction == null -> {
            Text(
                text = "No hay transacción para mostrar",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        else -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .background(
                        color = Color.hsl(12f, 0.2f, 0.95f),
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    modifier = Modifier.padding(horizontal = 18.dp),
                    text = "Rec - ${transaction.receiptId}",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    modifier = Modifier.padding(horizontal = 18.dp),
                    text = "RRN - ${transaction.rrn}",
                    style = MaterialTheme.typography.bodyLarge
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$ ${transaction.amount}",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = if (transaction.nullable) "Anulada" else "Autorizada",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 18.sp,
                            color = Color.White
                        ),
                        modifier = Modifier
                            .background(
                                color = if (transaction.nullable) Color.Red else Color.Green,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }

                Text(
                    modifier = Modifier.padding(horizontal = 18.dp),
                    text = "Titular - ${transaction.cardHolder}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    modifier = Modifier.padding(horizontal = 18.dp),
                    text = transaction.card,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { viewModel.loadTransactionById(transaction.receiptId) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = transaction.receiptId.isNotBlank()
                ) {
                    Text("Anular transacción")
                }

            }
        }
    }
}
