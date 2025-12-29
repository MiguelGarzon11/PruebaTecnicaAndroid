package com.mgarzondev.paymentspt.ui.screens.transactionListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mgarzondev.paymentspt.ui.screens.transactionListScreen.dto.TransactionData

@Composable
fun TransactionCard(Detail: TransactionData) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .background(
                color = Color.hsl(12f, 0.2f, 0.95f),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier.padding(horizontal = 18.dp),
            text = "Rec - ${Detail.receiptId}",
            style = MaterialTheme.typography.titleMedium
            )

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "$ ${Detail.amount}",
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = if(Detail.nullable) "Anulada" else "Autorizada",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 18.sp,
                    color = Color.White
                ),
                modifier = Modifier
                    .background(
                        color = if (Detail.nullable) Color.Red else Color.Green,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = Detail.cardHolder,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

    }
}