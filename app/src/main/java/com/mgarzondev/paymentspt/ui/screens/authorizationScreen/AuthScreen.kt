package com.mgarzondev.paymentspt.ui.screens.authorizationScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AuthScreen() {
    val viewModel: AuthViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Autorizar Transacción",
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Ingrese los datos para autorizar una nueva transacción",
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(modifier = Modifier.height(16.dp))

        AuthForm(viewModel = viewModel)

        Spacer(modifier = Modifier.height(20.dp))

    }
}