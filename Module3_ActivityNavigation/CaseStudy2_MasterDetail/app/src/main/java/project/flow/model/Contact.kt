package project.flow.model

import java.io.Serializable

data class Contact(
    val name: String,
    val phone: String,
    val email: String,
    val company: String,
    val address: String
) : Serializable