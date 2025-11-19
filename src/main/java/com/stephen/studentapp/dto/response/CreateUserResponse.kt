package com.stephen.studentapp.dto.response

import java.time.Instant

data class CreateUserResponse(
    val id: Long,
    val fullName: String,
    val username: String,
    val createdAt: Instant,
    val updatedAt: Instant,
)
