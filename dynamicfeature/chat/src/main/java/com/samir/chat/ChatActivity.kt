package com.samir.chat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.samir.ecommerceapp.ui.theme.ECommerceModularAppTheme

class ChatActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ECommerceModularAppTheme {
                ChatScreen(onBackClick = { finish() })
            }
        }
    }
}