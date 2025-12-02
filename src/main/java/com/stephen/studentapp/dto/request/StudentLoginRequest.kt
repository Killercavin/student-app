package com.stephen.studentapp.dto.request

import jakarta.validation.constraints.NotBlank

data class StudentLoginRequest(
    @field:NotBlank(message = "ID cannot be blank")
    val username: String,
    @field:NotBlank(message = "Password is required")
    val password: String
)
