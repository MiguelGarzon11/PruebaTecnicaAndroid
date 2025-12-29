package com.mgarzondev.paymentspt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mgarzondev.paymentspt.ui.components.NavigationBottonBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationBottonBar()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavigationBottonBar()
}