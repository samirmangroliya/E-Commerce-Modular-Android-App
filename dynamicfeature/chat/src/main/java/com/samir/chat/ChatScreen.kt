// dynamicfeature/chat/src/main/java/com/samir/chat/ChatScreen.kt
package com.samir.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Send
import androidx.compose.ui.draw.clip

data class ChatMessage(val text: String, val isFromUser: Boolean)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(onBackClick: () -> Unit) {
    var messages by remember {
        mutableStateOf(listOf(ChatMessage("Hi! How can we help you today?", isFromUser = false)))
    }
    var inputText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chat Support") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBackIosNew, contentDescription = "Chat")
                    }
                },
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                OutlinedTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Type a message...") },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = Color.Gray
                    )
                )
                Spacer(Modifier.width(8.dp))
                IconButton(
                    onClick = {
                        if (inputText.isNotBlank()) {
                            messages = messages + ChatMessage(inputText, isFromUser = true)
                            inputText = ""
                        }
                    },
                    modifier = Modifier
                        .clip(RoundedCornerShape(100.dp))
                        .background(color = Color.Gray)
                        .padding(5.dp),
                ) {
                    Icon(Icons.Default.Send, contentDescription = "Chat", tint = Color.Black)
                }
            }
        },
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            reverseLayout = true,
        ) {
            items(messages.reversed()) { message ->
                MessageBubble(message)
            }
        }
    }
}

@Composable
private fun MessageBubble(message: ChatMessage) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = if (message.isFromUser) Arrangement.End else Arrangement.Start,
    ) {
        Surface(
            color = if (message.isFromUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
            shape = MaterialTheme.shapes.medium,
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                color = if (message.isFromUser) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Preview
@Composable
fun PreviewChatScreen() {
    ChatScreen {}
}