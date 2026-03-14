package com.example.chatapp

data class Message(
    val sender: String = "",
    val text: String = "",
    val timestamp: Long = 0
)