package com.stephen.studentapp.dto.request

data class CreateStudentRequest(
    val fullName: String,
    val username: String,
    val password: String,
)
