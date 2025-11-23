package com.stephen.studentapp.dto.response

import com.stephen.studentapp.entity.Student
import java.time.Instant

data class StudentResponse(
    val id: Long?,
    val fullName: String,
    val username: String,
    val createdAt: Instant?,
    val updatedAt: Instant?,
)

fun Student.toStudentResponse(): StudentResponse {
    return StudentResponse(
        id = this.id,
        fullName = this.fullName,
        username = this.username,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
    )
}
