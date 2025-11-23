package com.stephen.studentapp.dto.request

import com.stephen.studentapp.entity.Student
import jakarta.validation.constraints.NotBlank
import org.springframework.security.crypto.password.PasswordEncoder

data class CreateStudentRequest(
    @field:NotBlank(message = "Student name cannot be blank")
    val fullName: String,
    @field:NotBlank(message = "Student username cannot be blank")
    val username: String,
    @field:NotBlank(message = "Student password cannot be blank")
    val password: String,
)

fun CreateStudentRequest.toEntity(passwordEncoder: PasswordEncoder): Student {
    return Student(
        fullName = this.fullName,
        username = this.username,
        password = passwordEncoder.encode(this.password)
    )
}
